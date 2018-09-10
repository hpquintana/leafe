package com.tupledevelopment.leafe.AsyncTasks;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.tupledevelopment.leafe.DynamoMaps.AssignedForm;
import com.tupledevelopment.leafe.Activities.JobActivity;
import com.tupledevelopment.leafe.Utils.AwsUtil;

import java.util.ArrayList;


/**
 * Created by Alex Higareda on 10/27/2016.
 */

public class FetchJobDescriptionTask extends AsyncTask<String, Void, ArrayList<AssignedForm>> {
    private JobActivity activity;

    private DynamoDBMapper dynamoDBMapper;
    private PaginatedQueryList<AssignedForm> result;
    private String formId = "20";

    public FetchJobDescriptionTask(JobActivity activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute(){

    }
    @Override
    protected ArrayList<AssignedForm> doInBackground(String... employeeIds){
        dynamoDBMapper = AwsUtil.getDynamoDBMapper(activity);

        AssignedForm assignedForm = dynamoDBMapper.load(AssignedForm.class, formId);


        long time = System.currentTimeMillis();

        Condition rangedKeyCondition = new Condition()
                .withComparisonOperator(ComparisonOperator.GT.toString())
                .withAttributeValueList(new AttributeValue().withN(String.valueOf(time)));

        DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                .withHashKeyValues(assignedForm)
                .withRangeKeyCondition("JobDate", rangedKeyCondition)
                .withConsistentRead(false);
        result = dynamoDBMapper.query(AssignedForm.class, queryExpression);



        return new ArrayList<>(result);


    }
}
