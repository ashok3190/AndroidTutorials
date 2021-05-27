package com.learn.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.learn.backroundtaskstutorial.R;

import static com.learn.utility.App.CHANNEL_ID;

public class AlarmReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        int hr = intent.getIntExtra("hr", 12);
        int mm = intent.getIntExtra("mm", 00);
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Alarm Manager")
                .setContentText("Alarm is set for => " + hr + ":" + mm)
                .setSmallIcon(R.drawable.ic_texture)
                .build();
        NotificationManager manager = context.getSystemService(NotificationManager.class);
        manager.notify(1, notification);
    }
}
