package com.tupledevelopment.leafe.AsyncTasks;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.tupledevelopment.leafe.Activities.ViewTos;
import com.tupledevelopment.leafe.DynamoMaps.AssignedForm;
import com.tupledevelopment.leafe.Utils.AwsUtil;

public class FetchToSTask extends AsyncTask<String, Void, AssignedForm> {
    private ViewTos activity;
    private DynamoDBMapper dynamoDBMapper;
    private AssignedForm assignedForm;

    public FetchToSTask(ViewTos activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute(){

    }

    @Override
    protected AssignedForm doInBackground(String... formIds) {
        dynamoDBMapper = AwsUtil.getDynamoDBMapper(activity);

        assignedForm = dynamoDBMapper.load(AssignedForm.class, formIds[0]);
        return assignedForm;
    }

    @Override
    protected void onPostExecute(AssignedForm form){
        if(isCancelled()){
            //Task was cancelled
        } else if(activity != null){ //Did not exit the activity
            activity.onLoadNotification(form);
        }
    }
}
