package com.learn.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.learn.backroundtaskstutorial.ForegroundServiceActivity;
import com.learn.backroundtaskstutorial.R;
import com.learn.receiver.DynamicExplicitBroadcastReceiver;

import static com.learn.utility.App.CHANNEL_ID;

public class ForegroundServices extends Service {

    private DynamicExplicitBroadcastReceiver receiver;

    @Override
    public void onCreate() {
        super.onCreate();
        receiver = new DynamicExplicitBroadcastReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notificationIntent = new Intent(this, ForegroundServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText(intent.getStringExtra("message"))
                .setSmallIcon(R.drawable.ic_texture)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
