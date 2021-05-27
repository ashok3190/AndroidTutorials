package com.learn.activitytutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.learn.models.Person;

import static com.learn.activitytutorial.MainActivity.REQUEST_CODE_MAIN_ACTIVITY;
import static com.learn.activitytutorial.MainActivity.SUCCESS;

public class SecondActivity extends Activity {

    private static final String TAG = "SecondActivity";
    //private Bundle firstActivityData;
    private TextView nameTextView;
    private TextView ageTextView;
    private TextView addressTextView;
    private TextView pinCodeTextView;
    private Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate()");
        setContentView(R.layout.activity_second);

        nameTextView = findViewById(R.id.nameTextView);
        ageTextView = findViewById(R.id.ageTextView);
        addressTextView = findViewById(R.id.addressTextView);
        pinCodeTextView = findViewById(R.id.pinCodeTextView);
        //firstActivityData = getIntent().getExtras();
        person = getIntent().getParcelableExtra("personInfo");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()");
//        nameTextView.setText("Name : " + firstActivityData.getString("name"));
//        ageTextView.setText("Age : " + firstActivityData.getInt("age"));
//        addressTextView.setText("Address : " + firstActivityData.getString("address"));
//        pinCodeTextView.setText("Pincode : " + firstActivityData.getInt("pin"));

        nameTextView.setText("Name : " + person.getName());
        ageTextView.setText("Age : " + person.getAge());
        addressTextView.setText("Address : " + person.getAddress());
        pinCodeTextView.setText("Pincode : " + person.getPincode());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
    }

    public void onBackPressed(View view) {
        Intent intent = new Intent();
        intent.putExtra("status", "Kam ho gaya!!!");
        setResult(SUCCESS, intent);
        finish();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
    }
}