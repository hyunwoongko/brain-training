package com.hyunwoong.braintraining.model;

import android.widget.Chronometer;
import android.widget.TextView;

public class CountDownModel {

    private volatile static CountDownModel mInstance = null;

    private Chronometer chronometer;
    private TextView ready;

    private CountDownModel() {
    }

    public static CountDownModel getInstance() {
        if (mInstance == null) {
            synchronized (CountDownModel.class) {
                if (mInstance == null) {
                    mInstance = new CountDownModel();

                }
            }
        }
        return mInstance;
    } // Singleton

    public TextView getReady() {
        return ready;
    }

    public void setReady(TextView ready) {
        this.ready = ready;
    }

    public Chronometer getChronometer() {
        return chronometer;
    }

    public void setChronometer(Chronometer chronometer) {
        this.chronometer = chronometer;
    }
}
