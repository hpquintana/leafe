package com.tupledevelopment.leafe.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.s3.AmazonS3Client;
import com.tupledevelopment.leafe.AsyncTasks.FetchToSTask;
import com.tupledevelopment.leafe.DynamoMaps.AssignedForm;
import com.tupledevelopment.leafe.R;
import com.tupledevelopment.leafe.Utils.AwsUtil;

public class ViewTos extends AppCompatActivity implements View.OnClickListener {
    private Button goToSign;
    private ProgressBar spinner;

    private AmazonS3Client sS3Client;
    private DynamoDBMapper dynamoDBMapper;
    private AssignedForm assignedForm;

    private final String buckUrlPrefix = "https://s3.amazonaws.com/tupledev-s3sample/";
    private String formId;
    private String employeeId;
    private TextView rateTextView;
    private TextView totalTextView;
    private String bucketName = "tupledev-s3sample";
    private String totalText = "Total: $";
    private double rate = 108.00;
    private double hours = 1.5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tos);

        Intent intent= getIntent();
        formId = intent.getStringExtra("FormID");
        employeeId = intent.getStringExtra("EmployeeID");

        sS3Client = AwsUtil.getS3Client(this);
        dynamoDBMapper = AwsUtil.getDynamoDBMapper(this);

        goToSign = (Button) findViewById(R.id.goToSign);
        goToSign.setOnClickListener(this);
        spinner = (ProgressBar) findViewById(R.id.view_tos_progress);
        spinner.setVisibility(View.VISIBLE);
        rateTextView = (TextView) findViewById(R.id.view_tos_grid_rate1);
        totalTextView = (TextView) findViewById(R.id.view_tos_total_text);

        new FetchToSTask(this).execute(formId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goToSign:
                startSignature();
                break;
        }
    }

    public void onLoadNotification(AssignedForm assignedForm){
        this.assignedForm = assignedForm;
        spinner.setVisibility(View.GONE);
        updateUI();
    }

    private void updateUI(){
        if (assignedForm.getHourRate() != 0){ //if not initialized
            rate = assignedForm.getHourRate();
        }
        if (assignedForm.getJobTime() != 0){
            hours = assignedForm.getJobTime();
        }
        double unformattedTotal = rate * hours;
        String formattedTotal = String.format("%.2f", unformattedTotal);

        rateTextView.setText("$" + String.valueOf(rate));
        totalTextView.setText(totalText + formattedTotal);

    }

    public void startSignature(){
        Intent intent = new Intent(this, CaptureSignature.class);
        intent.putExtra("FormID", formId);
        intent.putExtra("EmployeeID", employeeId);
        startActivity(intent);
    }
}
