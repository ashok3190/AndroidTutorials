package com.learn.backroundtaskstutorial;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HandlerThreadActivity extends Activity {
    private static final String TAG = "ThirdActivity";
    private ExampleHandlerThread handlerThread = new ExampleHandlerThread();
    private ExampleRunnable1 exp1;
    private ExampleRunnable2 exp2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        handlerThread.start();
    }

    public void onDoWork(View view) {
        Log.e(TAG, "onDoWork()");
        Message msg = Message.obtain();
        msg.what = 1;
        msg.arg1 = 21;
        msg.arg2 = 22;
        msg.obj = "Hello";
        Bundle bundle = new Bundle();
        bundle.putString("name", "ashok");
        bundle.putInt("age", 31);
        msg.setData(bundle);

        exp1 = new ExampleRunnable1(4);
        exp2 = new ExampleRunnable2(4);

        handlerThread.getHandler().post(exp1);
        handlerThread.getHandler().post(exp2);
        handlerThread.getHandler().sendMessage(msg);
    }

    public void onRemoveMessages(View view) {
        handlerThread.getHandler().removeMessages(1);
        handlerThread.getHandler().removeCallbacks(exp2);
    }

    class ExampleHandlerThread extends HandlerThread {

        private Handler handler;

        ExampleHandlerThread() {
            super("ExampleHandlerThread", Process.THREAD_PRIORITY_URGENT_AUDIO);
        }

        @Override
        protected void onLooperPrepared() {
            handler = new ExampleHandler();
        }

        Handler getHandler() {
            return handler;
        }
    }

    static class ExampleHandler extends Handler {
        private static final String TAG = "ExampleHandler";

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e(TAG, "arg1 : " + msg.arg1);
                    Log.e(TAG, "arg2 : " + msg.arg2);
                    Log.e(TAG, "obj : " + msg.obj);
                    Bundle bundle = msg.getData();
                    Log.e(TAG, "bundle name : " + bundle.getString("name"));
                    Log.e(TAG, "bundle age : " + bundle.getInt("age"));
                    break;
                default:
                    break;
            }
        }
    }

    static class ExampleRunnable1 implements Runnable {
        private static final String TAG = "ExampleRunnable";

        private int sleep;

        ExampleRunnable1(int sleep) {
            this.sleep = sleep;
        }

        @Override
        public void run() {
            for (int i = 0; i < sleep; i++) {
                Log.e(TAG, "run : " + i);
                SystemClock.sleep(1000);
            }
        }
    }

    static class ExampleRunnable2 implements Runnable {
        private static final String TAG = "ExampleRunnable2";

        private int sleep;

        ExampleRunnable2(int sleep) {
            this.sleep = sleep;
        }

        @Override
        public void run() {
            for (int i = 0; i < sleep; i++) {
                Log.e(TAG, "run : " + i);
                SystemClock.sleep(1000);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }
}
