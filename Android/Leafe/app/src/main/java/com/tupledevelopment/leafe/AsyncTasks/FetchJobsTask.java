package com.tupledevelopment.leafe.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.tupledevelopment.leafe.Activities.ViewJobs;
import com.tupledevelopment.leafe.DynamoMaps.AssignedForm;
import com.tupledevelopment.leafe.Utils.AwsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class FetchJobsTask extends AsyncTask<String, Void, ArrayList<AssignedForm>>{
    private ViewJobs activity;
//    private NotificationInteractionListener listener;
    private DynamoDBMapper dynamoDBMapper;
    private PaginatedQueryList<AssignedForm> result;

    public FetchJobsTask(ViewJobs activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute(){

    }

    @Override
    protected ArrayList<AssignedForm> doInBackground(String... employeeIds) {
        dynamoDBMapper = AwsUtil.getDynamoDBMapper(activity);

        AssignedForm assignedForm = new AssignedForm();
        assignedForm.setEmployeeID(employeeIds[0]);

        long time= System.currentTimeMillis();

        Condition rangeKeyCondition = new Condition()
                .withComparisonOperator(ComparisonOperator.GT.toString())
                .withAttributeValueList(new AttributeValue().withN(String.valueOf(time)));

        DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                .withHashKeyValues(assignedForm)
                .withRangeKeyCondition("JobDate", rangeKeyCondition)
                .withConsistentRead(false);
        result = dynamoDBMapper.query(AssignedForm.class, queryExpression);

        Calendar c = Calendar.getInstance();
        long timeInMilli = c.getTimeInMillis();

        List<String> list = Arrays.asList("sup1", "sup2", "sup3");

        AssignedForm newForm = new AssignedForm();
        newForm.setEmployeeID(employeeIds[0]);

        final Random rand = new Random();
        int diceRoll = rand.nextInt(1000) + 100;

        newForm.setFormID(String.valueOf(diceRoll));
        newForm.setDate(timeInMilli);
        newForm.setTestArray(list);
        dynamoDBMapper.save(newForm);

        return new ArrayList<>(result);
    }

    @Override
    protected void onPostExecute(ArrayList<AssignedForm> jobs){
        if(isCancelled()){
            //Task was cancelled
        } else if(activity != null){ //Did not exit the activity
            activity.onJobsLoadNotification(jobs);
        }
    }

//    public interface NotificationInteractionListener {
//
//    }
}
