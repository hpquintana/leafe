package com.tupledevelopment.leafe.Activities;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.tupledevelopment.leafe.Adapters.ViewJobsAdapter;
import com.tupledevelopment.leafe.AsyncTasks.FetchJobsTask;
import com.tupledevelopment.leafe.Callbacks.ObservableScrollViewCallbacks;
import com.tupledevelopment.leafe.CustomJava.EndlessRecyclerOnScrollListener;
import com.tupledevelopment.leafe.CustomViews.ObservableRecyclerView;
import com.tupledevelopment.leafe.DynamoMaps.AssignedForm;
import com.tupledevelopment.leafe.Enums.ScrollState;
import com.tupledevelopment.leafe.R;
import com.tupledevelopment.leafe.Utils.AwsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ViewJobs extends AppCompatActivity implements ViewJobsAdapter.JobOnClickListener, ObservableScrollViewCallbacks {
    private String employeeId;
    private String formId;

    private DynamoDBMapper dynamoDBMapper;
    private AssignedForm assignedForm;
    private AmazonDynamoDBClient dynamoDBClient;

    private ArrayList<AssignedForm> queryResults;
    private ViewJobsAdapter adapter;

    private ObservableRecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private TextView emptyView;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);

        Intent intent= getIntent();
        employeeId = intent.getStringExtra("EmployeeID");
        formId = intent.getStringExtra("FormID");

        dynamoDBMapper = AwsUtil.getDynamoDBMapper(this);
        dynamoDBClient = AwsUtil.getDynamoDBClient(this);

        initRecycler();

        new FetchJobsTask(this).execute(employeeId);
    }

    private void initRecycler(){
        recyclerView = (ObservableRecyclerView) findViewById(R.id.recycler_view);
        emptyView = (TextView) findViewById(R.id.empty_view);
        spinner = (ProgressBar) findViewById(R.id.view_jobs_progress);

        spinner.setVisibility(View.VISIBLE);

        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setScrollViewCallbacks(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            public void onLoadMore(int current_page, int totalItemCount) {
                //Load more jobs?
            }
        });

        ArrayList<AssignedForm> queryResults = new ArrayList<>();
        adapter = new ViewJobsAdapter(this, queryResults, this);
        recyclerView.setAdapter(adapter);
    }

    public void onJobsLoadNotification(ArrayList<AssignedForm> queryResults) {
        this.queryResults = queryResults;

        spinner.setVisibility(View.GONE);

        if (queryResults.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            adapter.addJobs(queryResults);
        }



    }

    @Override
    public void onEventSelected(String eventId) {

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
