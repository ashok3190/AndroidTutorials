package com.learn.backroundtaskstutorial;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

public class ThreadRunnableActivity extends Activity {

    private static final String TAG = "FirstActivity";
    private Button startThreadButton;
    private Handler uiHandler = new Handler();
    private volatile boolean stopThread = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_runnable);
        startThreadButton = findViewById(R.id.startThreadBtn);
    }

    public void onStartThread(View view) {
        stopThread = false;
        new ExampleThread(this, 10).start();
        //new Thread(new ExampleRunnable(this, 10));
    }

    public void onStopThread(View view) {
        stopThread = true;
    }

    class ExampleThread extends Thread {
        private static final String TAG = "ExampleThread";
        private int seconds;
        private ThreadRunnableActivity activity;

        ExampleThread(ThreadRunnableActivity activity, int seconds) {
            this.activity = activity;
            this.seconds = seconds;
        }

        @Override
        public void run() {
            for (int i = 0; i < seconds; i++) {
                if(stopThread)
                    return;
                Log.e(TAG, "run: " + i);
                if(i == 5) {
                    uiHandler.post(new UpdateUI(this.activity, "Global Handler 50%"));
                    SystemClock.sleep(2000);
                    new Handler(Looper.getMainLooper()).post(new UpdateUI(this.activity, "Local Handler 50%"));
                    SystemClock.sleep(2000);
                    startThreadButton.post(new UpdateUI(this.activity, "Widget 50%"));
                    SystemClock.sleep(2000);
                    runOnUiThread(new UpdateUI(this.activity, "UI Thread 50%"));
                }
                SystemClock.sleep(1000);
            }
            startThreadButton.post(new UpdateUI(this.activity, "Start Thread"));
        }
    }

    class ExampleRunnable implements Runnable {

        private static final String TAG = "ExampleRunnable";
        private int seconds;
        private ThreadRunnableActivity activity;

        ExampleRunnable(ThreadRunnableActivity activity, int seconds) {
            this.activity = activity;
            this.seconds = seconds;
        }

        @Override
        public void run() {
            for (int i = 0; i < seconds; i++) {
                if(stopThread)
                    return;
                Log.e(TAG, "run: " + i);
                if(i == 5) {
                    uiHandler.post(new UpdateUI(this.activity, "Global Handler 50%"));
                    SystemClock.sleep(2000);
                    new Handler(Looper.getMainLooper()).post(new UpdateUI(this.activity, "Local Handler 50%"));
                    SystemClock.sleep(2000);
                    startThreadButton.post(new UpdateUI(this.activity, "Widget 50%"));
                    SystemClock.sleep(2000);
                    runOnUiThread(new UpdateUI(this.activity, "UI Thread 50%"));
                }
                SystemClock.sleep(1000);
            }
            startThreadButton.post(new UpdateUI(this.activity, "Start Thread"));
        }
    }

    static class UpdateUI implements Runnable {

        private WeakReference<ThreadRunnableActivity> weakReference;
        private String message;

        UpdateUI(ThreadRunnableActivity activity, String message) {
            this.weakReference = new WeakReference<>(activity);
            this.message = message;
        }

        @Override
        public void run() {
            ThreadRunnableActivity activity = this.weakReference.get();
            if(activity == null || activity.isFinishing()) {
                return;
            }
            activity.startThreadButton.setText(message);
        }
    }
}
