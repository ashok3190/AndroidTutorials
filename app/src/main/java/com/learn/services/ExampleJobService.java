package com.learn.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import java.lang.ref.WeakReference;

public class ExampleJobService extends JobService {

    private static final String TAG = "ExampleJobService";
    private boolean jobCanceled = false;
    
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG, "Job Start");
        doBackgroundWorks(params);
        /**
         * true - For Long running operations. Keep the wakelock up.
         * false - not keep the wakelock awake
         * */
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG, "Job Cancelled Before Completion");
        jobCanceled = true;

        /**
         * true - reschedule the job.
         * false - no reschedule required.
         * */
        return true;
    }

    private void doBackgroundWorks(final JobParameters params) {
        Thread thread = new Thread(new ExampleRunnable(this, params));
        thread.start();
    }

    private static class ExampleRunnable implements Runnable {
        private static final String TAG = "ExampleRunnable";
        private WeakReference<ExampleJobService> weakReference;
        private JobParameters params;

        ExampleRunnable(ExampleJobService service, JobParameters params) {
            this.weakReference = new WeakReference<>(service);
            this.params = params;
        }

        @Override
        public void run() {
            ExampleJobService service = this.weakReference.get();
            if(service == null) {
                return;
            }
            for (int i = 0; i < 10; i++) {
                if(service.jobCanceled) return;
                Log.e(TAG, "run: " + i);
                SystemClock.sleep(1000);
            }
            Log.e(TAG, "Job Finished");
            service.jobFinished(this.params, false);
        }
    }
}
