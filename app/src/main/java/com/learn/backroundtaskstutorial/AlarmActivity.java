package com.learn.backroundtaskstutorial;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.learn.receiver.AlarmReceiver;

import java.text.DateFormat;
import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        textView = findViewById(R.id.alarmTextView);
    }

    public void onSetAlarm(View view) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    public void onCancelAlarm(View view) {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        manager.cancel(pendingIntent);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        updateTextView(c);
        setAlarm(c);
    }

    private void updateTextView (Calendar c) {
        textView.setText("Alarm set for : " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE));
    }

    private void setAlarm(Calendar c) {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("hr", c.get(Calendar.HOUR_OF_DAY));
        intent.putExtra("mm", c.get(Calendar.MINUTE));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        manager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
}
