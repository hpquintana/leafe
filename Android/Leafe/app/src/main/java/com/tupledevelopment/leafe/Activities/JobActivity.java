package com.tupledevelopment.leafe.Activities;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobile.push.PushManager;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.tupledevelopment.leafe.Adapters.ViewJobDescriptionAdapter;
import com.tupledevelopment.leafe.AsyncTasks.FetchJobDescriptionTask;
import com.tupledevelopment.leafe.CustomViews.ObservableRecyclerView;
import com.tupledevelopment.leafe.DynamoMaps.AssignedForm;
import com.tupledevelopment.leafe.R;
import com.tupledevelopment.leafe.Utils.AwsUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JobActivity extends AppCompatActivity{
    private String employeeId;
    private String formId;

    private DynamoDBMapper dynamoDBMapper;
    private AssignedForm assignedForm;
    private AmazonDynamoDBClient dynamoDBClient;

    private ObservableRecyclerView recyclerView;
    private ArrayList<AssignedForm> queryResult;
    private ViewJobDescriptionAdapter adapter;
    private GridLayoutManager layoutManager;
    private TextView emptyView;
    private ProgressBar spinner;
    private boolean enabled = true;
    private PushManager pushManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
//        pushManager = AWSMobileClient.defaultMobileClient().getPushManager();
//        registerDevice(enabled);

        Intent intent = getIntent();
        employeeId = intent.getStringExtra("EmployeeID");
        formId = intent.getStringExtra("FormID");

        dynamoDBMapper = AwsUtil.getDynamoDBMapper(this);
        dynamoDBClient = AwsUtil.getDynamoDBClient(this);

        recyclerView = (ObservableRecyclerView) findViewById(R.id.recycler_view);

        ArrayList<AssignedForm> queryResult = new ArrayList<>();
        adapter = new ViewJobDescriptionAdapter(this, queryResult, this);
        recyclerView.setAdapter(adapter);

        new FetchJobDescriptionTask(this).execute(employeeId);
    }

    private void registerDevice(final boolean enabled){
        pushManager.registerDevice();

        if(pushManager.isRegistered()){
            pushManager.setPushEnabled(enabled);

            if(enabled){
                pushManager.subscribeToTopic(pushManager.getDefaultTopic());
            }
        }
    }
}
