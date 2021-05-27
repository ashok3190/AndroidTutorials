package com.learn.backroundtaskstutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.learn.services.ForegroundServices;

public class ForegroundServiceActivity extends Activity {

    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fground_service);

        editText = findViewById(R.id.editText);
    }

    public void startForegroundService(View view) {
        Intent intent = new Intent(this, ForegroundServices.class);
        intent.putExtra("message", editText.getText().toString());
        ContextCompat.startForegroundService(this, intent);
    }

    public void stopForegroundService(View view) {
        stopService(new Intent(this, ForegroundServices.class));
    }
}
