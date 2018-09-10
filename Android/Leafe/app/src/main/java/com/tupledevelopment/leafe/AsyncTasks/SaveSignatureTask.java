package com.tupledevelopment.leafe.AsyncTasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.tupledevelopment.leafe.Activities.CaptureSignature;
import com.tupledevelopment.leafe.DynamoMaps.AssignedForm;
import com.tupledevelopment.leafe.Utils.AwsUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class SaveSignatureTask extends AsyncTask<String, Void, AssignedForm> {
    private CaptureSignature activity;
    private DynamoDBMapper dynamoDBMapper;
    private AssignedForm assignedForm;
    private Bitmap signatureBitmap;
    private AmazonS3Client sS3Client;

    private final String buckUrlPrefix = "https://s3.amazonaws.com/tupledev-s3sample/";
    private String bucketName = "tupledev-s3sample";
    private String signatureKey;
    private String employeeId;

    public SaveSignatureTask(CaptureSignature activity, Bitmap signatureBitmap, String employeeId){
        this.activity = activity;
        this.signatureBitmap = signatureBitmap;
        this.employeeId = employeeId;
    }

    @Override
    protected void onPreExecute(){
        sS3Client = AwsUtil.getS3Client(activity);
        dynamoDBMapper = AwsUtil.getDynamoDBMapper(activity);
    }

    @Override
    protected AssignedForm doInBackground(String... formIds) {
        signatureKey = formIds[0].concat("signature1.jpg");

        saveSignature(formIds[0]);

        assignedForm = dynamoDBMapper.load(AssignedForm.class, formIds[0]);
        return assignedForm;
    }

    @Override
    protected void onPostExecute(AssignedForm form){
        if(isCancelled()){
            //Task was cancelled
        } else if(activity != null){ //Did not exit the activity
            activity.onSaveNotification(form);
        }
    }

    public void saveSignature(String formId){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        signatureBitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();
        ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);

//        sS3Client.putObject("tupledev-s3sample", signatureKey, bs, null);
//                        beginUpload(path);
        sS3Client.putObject(
                new PutObjectRequest(bucketName, signatureKey, bs, null)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

        assignedForm = dynamoDBMapper.load(AssignedForm.class, formId);

        if(assignedForm != null){ //Assigned Form Exists
            assignedForm.setSignatureUrl(buckUrlPrefix.concat(signatureKey));
        } else { //Assigned Form does not exist
            assignedForm = new AssignedForm();
            assignedForm.setFormID(formId);
            assignedForm.setSignatureUrl(buckUrlPrefix.concat(signatureKey));
        }
        assignedForm.setEmployeeID("-1");
        assignedForm.setCompletedBy(employeeId);
        dynamoDBMapper.save(assignedForm);
    }
}
