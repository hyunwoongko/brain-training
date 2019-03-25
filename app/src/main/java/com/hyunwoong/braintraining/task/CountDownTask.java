package com.hyunwoong.braintraining.task;


import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.hyunwoong.braintraining.utils.Effect;

public class CountDownTask extends AsyncTask<String, Integer, Void> {
    private TextView textView;
    private Context context;
    private Chronometer chronometer;


    private long pauseoffset;

    private Thread thread = new Thread();

    public CountDownTask(TextView textView, Context context) {

        this.textView = textView;
        this.context = context;
    }

    public CountDownTask(Chronometer chronometer, TextView textView, Context context , long pauseoffset) {

        this.chronometer = chronometer;
        this.textView = textView;
        this.context = context;
        this.pauseoffset = pauseoffset;
    }

    @Override
    protected Void doInBackground(String... params) {

        for (int i = 3; i >= 1; i--) {
            if (!isCancelled()) {
                publishProgress(i);
                synchronized (thread) {
                    try {
                        thread.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (isCancelled()) {
                        break;
                    }
                }
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Effect.getInstance().CountDown_Beep(context);
        textView.setText(String.valueOf(values[0]));
        // 카운트다운 텍스트
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        textView.setVisibility(View.GONE);
        //카운트다운 텍스트뷰 제거
        if(chronometer!=null) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseoffset);
            chronometer.start();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    public Chronometer getChronometer() {
        return chronometer;
    }

    public void setChronometer(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

    public long getPauseoffset() {
        return pauseoffset;
    }

    public void setPauseoffset(long pauseoffset) {
        this.pauseoffset = pauseoffset;
    }
}
