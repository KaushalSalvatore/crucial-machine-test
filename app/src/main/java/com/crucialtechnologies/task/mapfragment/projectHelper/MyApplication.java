package com.crucialtechnologies.task.mapfragment.projectHelper;


import android.app.Application;

import com.google.android.gms.common.api.GoogleApiClient;


public class MyApplication extends Application {

    private static MyApplication mInstance;
    private GoogleApiClient googleApiClient;

    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;


    }
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }



}
