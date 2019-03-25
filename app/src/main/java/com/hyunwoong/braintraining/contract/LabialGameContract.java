package com.hyunwoong.braintraining.contract;

import android.view.View;

public interface LabialGameContract extends BaseContract {

    interface view{
        void onDeleteButtonClicked(View view);
        void onSubmittedButtonClicked(View view);
    }

    interface presenter{
        void init();
        void StopTTS();
        void MakeQuestion();
        void Delete();
        void Submit();
        void avoidSubmit();
        boolean isPlaying();

    }
}
