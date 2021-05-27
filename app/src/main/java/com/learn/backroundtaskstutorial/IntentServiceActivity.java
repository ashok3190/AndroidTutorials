package com.learn.backroundtaskstutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.learn.services.ExampleIntentService;

public class IntentServiceActivity extends Activity {

    private EditText editText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        editText = findViewById(R.id.editTextIntent);
    }

    public void startIntentService(View view) {
        Intent serviceIntent = new Intent(this, ExampleIntentService.class);
        serviceIntent.putExtra("message", editText.getText().toString());
        ContextCompat.startForegroundService(this, serviceIntent);
    }
}
