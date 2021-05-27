package com.learn.backroundtaskstutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.learn.receiver.DynamicExplicitBroadcastReceiver;

public class DynamicBroadcastReceiverActivity extends AppCompatActivity {
    private static final String TAG = "BroadcastReceiverActivity";
    private DynamicExplicitBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_reciver);
        receiver = new DynamicExplicitBroadcastReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }
}
