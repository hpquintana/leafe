package com.tupledevelopment.leafe.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.EditText;

import com.amazonaws.mobile.push.PushManager;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.tupledevelopment.leafe.AsyncTasks.LoginTask;
import com.tupledevelopment.leafe.DynamoMaps.AssignedForm;
import com.tupledevelopment.leafe.PushListenerService;
import com.tupledevelopment.leafe.R;
import com.tupledevelopment.leafe.Utils.AwsUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String formId = "123";
    private String employeeId = "12";
    private String jobDate = "4/26/2017";
    private String jobAddress = "12345 Fake Street";
    private EditText userInput;
    private EditText employeeInput;
    private Button viewJobs;

    private static final int REQUEST_GOOGLE_PLAY_SERVICES = 1363;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private PushManager pushManager;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText) findViewById(R.id.editText);
        employeeInput = (EditText) findViewById(R.id.editText2);
        viewJobs = (Button) findViewById(R.id.button3);
        spinner = (ProgressBar) findViewById(R.id.login_progress);

        viewJobs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button3:
                spinner.setVisibility(View.VISIBLE);
                getInput();
                new LoginTask(this).execute(employeeId);
                break;
        }
    }

    public void onLoginNotification(boolean success){
        spinner.setVisibility(View.GONE);
        if (success){
            Intent intent = new Intent(this, ViewJobs.class);
            intent.putExtra("EmployeeID", employeeId);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Incorrect login info.", Toast.LENGTH_SHORT).show();
        }
    }

    private final BroadcastReceiver notificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LOG_TAG, "Received notification from local broadcast. Display it in a dialogo");

            Bundle data = intent.getBundleExtra(PushListenerService.INTENT_SNS_NOTIFICATION_DATA);
            String message = PushListenerService.getMessage(data);

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Push Notification")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        }
    };

    @Override
    protected void onPause(){
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(notificationReceiver);
    }

    public void getInput(){
        if (userInput.getText().toString() != "" && userInput.getText().toString() != "Enter Form ID") {
            formId = userInput.getText().toString();
        }
        if (employeeInput.getText().toString() != "" && employeeInput.getText().toString() != "Enter Employee ID"){
            employeeId = employeeInput.getText().toString();
        }
    }
}
