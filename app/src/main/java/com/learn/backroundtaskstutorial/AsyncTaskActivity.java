package com.learn.backroundtaskstutorial;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

public class AsyncTaskActivity extends Activity {
    private static final String TAG = "FourthActivity";

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        textView = findViewById(R.id.textarea);
    }

    public void startAsyncTask(View view) {
        new ExampleAsyncTask(this).execute("Hello");
        Log.e(TAG, "Async Task Executed");
    }

    private static class ExampleAsyncTask extends AsyncTask<String, Void, Boolean> {

        private WeakReference<AsyncTaskActivity> fourthActivityWeakReference;

        ExampleAsyncTask(AsyncTaskActivity activity) {
            this.fourthActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected void onPreExecute() {
            AsyncTaskActivity fourthActivity = this.fourthActivityWeakReference.get();
            if(fourthActivity == null || fourthActivity.isFinishing()) {
                return;
            }
            fourthActivity.textView.setText("Async Task Started");
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            AsyncTaskActivity fourthActivity = this.fourthActivityWeakReference.get();
            if(fourthActivity == null || fourthActivity.isFinishing()) {
                return false;
            }
            for (int i = 0; i < 10; i++) {
                int progress = (i * 100) / 10 ;
                fourthActivity.textView.setText("Progress : " + progress + "%");
                SystemClock.sleep(2000);
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            AsyncTaskActivity fourthActivity = this.fourthActivityWeakReference.get();
            if(fourthActivity == null || fourthActivity.isFinishing()) {
                return;
            }
            fourthActivity.textView.setText(aBoolean ? "Task Completed" : "Task Incomplete");
        }
    }
}
