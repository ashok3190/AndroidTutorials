package com.learn.services;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.learn.backroundtaskstutorial.R;

import static com.learn.utility.App.CHANNEL_ID;

public class ExampleIntentService extends IntentService {

    private static final String TAG = "ExampleIntentService";
    private PowerManager.WakeLock wakeLock;

    public ExampleIntentService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e(TAG, "onCreate()");

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "BackgroundTaskTutorial:WakeLock");
        wakeLock.acquire();
        Log.e(TAG, "Wakelock Acquire");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Example Intent Service")
                    .setContentText("Running...")
                    .setSmallIcon(R.drawable.ic_texture)
                    .build();
            startForeground(1, notification);
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e(TAG, "onHandleIntent");

        String msg = intent.getStringExtra("message");
        Log.e(TAG, "Message : " + msg);

        for (int i = 0; i < 10; i++) {
            Log.e(TAG, msg + " - " + i);
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
        wakeLock.release();
        Log.e(TAG, "Wakelock Release");
    }
}
