package com.learn.backroundtaskstutorial;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LooperHandlerActivity extends Activity {
    private static final String TAG = "SecondActivity";
    private ExampleLooperThread looperThread = new ExampleLooperThread();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper_handler);
    }

    public void onStart(View view) {
        looperThread.start();
        Log.e(TAG, "Thread Started");
    }

    public void onStop(View view) {
        looperThread.handler.getLooper().quit();
        Log.e(TAG, "Close The Looper");
    }

    public void taskA(View view) {
        Message msg = Message.obtain();
        msg.what = 1;
        looperThread.handler.sendMessage(msg);
    }

    public void taskB(View view) {
        Message msg = Message.obtain();
        msg.what = 2;
        looperThread.handler.sendMessage(msg);
    }

    class ExampleLooperThread extends Thread {
        Handler handler;

        @Override
        public void run() {
            Looper.prepare();
            handler = new ExampleHandler();
            Looper.loop();
        }
    }

    class ExampleHandler extends Handler {
        private static final String TAG = "ExampleHandler";
        static final int TASK_A = 1;
        static final int TASK_B = 2;

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case TASK_A:
                    Log.e(TAG, "TASK A");
                    break;
                case TASK_B:
                    Log.e(TAG, "TASK B");
                    break;
                default:
                    Log.e(TAG, "Unexpected Message Came");
                    break;
            }
        }
    }
}
