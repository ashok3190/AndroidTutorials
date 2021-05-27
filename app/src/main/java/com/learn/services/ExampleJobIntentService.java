package com.learn.services;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class ExampleJobIntentService extends JobIntentService {

    private static final String TAG = "ExampleJobIntentService";

    public static void customEnqueueWork(Context context, Intent work) {
        enqueueWork(context, ExampleJobIntentService.class, 1, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.e(TAG, "onHandleWork");

        String msg = intent.getStringExtra("message");
        for (int i = 0; i < 5; i++) {
            Log.e(TAG, msg + " - " + i);
            if(isStopped()) return;
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public boolean onStopCurrentWork() {
        Log.e(TAG, "onStopCurrentWork");
        return super.onStopCurrentWork();
    }
}
