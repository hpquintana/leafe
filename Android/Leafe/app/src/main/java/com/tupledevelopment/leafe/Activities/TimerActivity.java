package com.tupledevelopment.leafe.Activities;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;
import android.os.StrictMode;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.tupledevelopment.leafe.DynamoMaps.AssignedForm;
import com.tupledevelopment.leafe.R;
import com.tupledevelopment.leafe.Utils.AwsUtil;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private DynamoDBMapper dynamoDBMapper;
    private AssignedForm assignedForm;

    private String formId;
    private String employeeId;

    private Button startButton;
    private Button stopButton;

    private TextView timer;
    private String stringTimer;

    private static final String TAG = "TimerActivity";

    private long startTime = 0L;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timerCounter = 0L;
    long updatedTime = 0L;

    double elapsedHours = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Intent intent = getIntent();
        formId = intent.getStringExtra("FormID");
        employeeId = intent.getStringExtra("EmployeeID");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        timer = (TextView) findViewById(R.id.timer);
        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);

        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        dynamoDBMapper = AwsUtil.getDynamoDBMapper(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.startButton:
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
                break;
            case R.id.stopButton:
                timerCounter += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
                saveTime(stringTimer);
                break;
        }
    }

    public void saveTime(String stringTimer){
        assignedForm = dynamoDBMapper.load(AssignedForm.class, formId);
        //TODO verify algorithm
        if ( (timerCounter / 1000) < 60) {//less than 1 minute?
            elapsedHours = 0.479;
        } else {
            elapsedHours = ((timerCounter / (1000 * 60 * 60)));
        }
        if(assignedForm != null) {
            assignedForm.setCompletedBy(stringTimer);
            assignedForm.setJobTime(elapsedHours);
            assignedForm.setFormState("NeedSig");
        } else{ //TODO get rid of this, only for testing
            assignedForm = new AssignedForm();
            assignedForm.setEmployeeID(employeeId);
            assignedForm.setFormID(formId);
            assignedForm.setCompletedBy(stringTimer);
            assignedForm.setJobTime(elapsedHours);
            assignedForm.setFormState("NeedSig");
        }
        dynamoDBMapper.save(assignedForm);
        Toast.makeText(TimerActivity.this, "Time Saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ViewTos.class);
        intent.putExtra("FormID", formId);
        intent.putExtra("EmployeeID", employeeId);
        startActivity(intent);
    }



    private Runnable updateTimerThread = new Runnable(){
        public void run(){
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timerCounter + timeInMilliseconds;

            int sec = (int)(updatedTime / 1000);
            int mins = sec / 60;
            sec = sec % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timer.setText("" + mins + ":"
                + String.format("%02d", sec) + ":"
                + String.format("%01d", milliseconds));
            stringTimer = timer.getText().toString();
            customHandler.postDelayed(this, 0);
        }
    };
}
