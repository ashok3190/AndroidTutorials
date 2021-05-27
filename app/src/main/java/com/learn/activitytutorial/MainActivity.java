package com.learn.activitytutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.learn.models.Person;

public class MainActivity extends Activity {

    public static final int REQUEST_CODE_MAIN_ACTIVITY = 1;
    public static final int SUCCESS = 1;
    public static final int FAILED = 0;

    private static final String TAG = "MainActivity";
    private EditText nameEditText;
    private EditText ageEditText;
    private EditText addressEditText;
    private EditText pinCodeEditText;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            Log.e(TAG, "onCreate : " + savedInstanceState.getString("TEST"));
        } else {
            Log.e(TAG, "onCreate is null");
        }
        Log.e(TAG, "onCreate()");
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.name);
        ageEditText = findViewById(R.id.age);
        addressEditText = findViewById(R.id.address);
        pinCodeEditText = findViewById(R.id.pinCode);
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("TEST", "TESTING");
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState()");
        if(savedInstanceState != null) {
            Log.e(TAG, "onRestoreInstanceState : " + savedInstanceState.getString("TEST"));
        } else {
            Log.e(TAG, "onRestoreInstanceState is null");
        }
    }

    public void onSecondActivity(View view) {
        String name = nameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String pin = pinCodeEditText.getText().toString();
        int ageInt = age.equals("") ? 0 : Integer.parseInt(age);
        int pinInt = pin.equals("") ? 0 : Integer.parseInt(pin);

        if(name.equals("")) {
            name = "NA";
        }
        if(address.equals("")) {
            address = "NA";
        }
        /*Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putInt("age", ageInt);
        bundle.putString("address", address);
        bundle.putInt("pin", pinInt);*/

        Person person = new Person();
        person.setName(name);
        person.setAddress(address);
        person.setAge(ageInt);
        person.setPincode(pinInt);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("personInfo", person);
        //intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE_MAIN_ACTIVITY);
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_MAIN_ACTIVITY) {
            switch (resultCode) {
                case SUCCESS:
                    Log.e(TAG,  "SUCCESS : " + data.getStringExtra("status"));
                    Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show();
                    break;
                case FAILED:
                    Log.e(TAG, "FAILED : " + data.getStringExtra("status"));
                    Toast.makeText(this, "Failed to save data", Toast.LENGTH_LONG).show();
                    break;
                default:
                    Log.e(TAG, "Invalid : " + data.getStringExtra("status"));
                    Toast.makeText(this, "Invalid Response", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
