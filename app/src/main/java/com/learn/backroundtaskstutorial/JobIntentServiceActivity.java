package com.learn.backroundtaskstutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.learn.services.ExampleJobIntentService;

public class JobIntentServiceActivity extends Activity {

    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_intent_service);
        editText = findViewById(R.id.editTextJobIntent);
    }

    public void onEnqueueWork(View view) {
        Intent intent = new Intent(this, ExampleJobIntentService.class);
        intent.putExtra("message", editText.getText().toString());
        ExampleJobIntentService.customEnqueueWork(this, intent);
    }
}
