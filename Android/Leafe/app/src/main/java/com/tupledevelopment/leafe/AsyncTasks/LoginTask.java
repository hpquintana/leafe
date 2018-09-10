package com.tupledevelopment.leafe.AsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.tupledevelopment.leafe.Activities.MainActivity;
import com.tupledevelopment.leafe.DynamoMaps.AssignedForm;

public class LoginTask extends AsyncTask<String, Void, Boolean> {
    private MainActivity activity;
    private String employeeID;

    public LoginTask( MainActivity activity){
        this.activity = activity;
    }
    @Override
    protected Boolean doInBackground(String... params) {
        employeeID = params[0];
        try {
            Thread.sleep(2000); //Wait for 2 seconds
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean success){
        if(isCancelled()){
            //Task was cancelled
        } else if(activity != null){ //Did not exit the activity
            activity.onLoginNotification(success);
        }
    }
}
