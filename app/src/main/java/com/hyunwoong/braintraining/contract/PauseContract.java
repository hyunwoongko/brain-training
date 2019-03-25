package com.hyunwoong.braintraining.contract;

import android.view.View;

public interface PauseContract extends BaseContract{

    interface view{
        void onResumeClicked(View view);
    }
}
