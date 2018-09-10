package com.tupledevelopment.leafe;

import android.util.Log;
import android.support.multidex.MultiDexApplication;

import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobile.push.PushManager;

public class Application extends MultiDexApplication {
    private final static String LOG_TAG = Application.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "Application.onCreate - Initializing application...");
        super.onCreate();
        initializeApplication();
        Log.d(LOG_TAG, "Application.onCreate - Application initialized OK");
    }

    private void initializeApplication() {

        // Initialize the AWS Mobile Client
        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());

        // ... Put any application-specific initialization logic here ...
        PushManager.setPushStateListener(new PushManager.PushStateListener(){

            @Override
            public void onPushStateChange(PushManager pushManager, boolean isEnabled) {
                Log.d(LOG_TAG, "push Notifications Enabled = " + isEnabled);
            }

        });

    }
}
