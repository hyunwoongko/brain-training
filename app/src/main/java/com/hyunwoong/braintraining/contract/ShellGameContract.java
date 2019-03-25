package com.hyunwoong.braintraining.contract;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.view.ShellGameView;

public interface ShellGameContract {

    interface view{
        void onSwapRightButtonClicked(View v);
        void cup0Clicked(View v);
        void cup1Clicked(View v);
        void cup2Clicked(View v);
    }

    interface presenter{
        void SetView(ImageView cup0,
                     ImageView cup1,
                     ImageView cup2,
                     Button swap_R,
                     TextView Qnumber,
                     TextView RightAnswerNumber,
                     ShellGameView view);
        void Cup0();
        void Cup1();
        void Cup2();
        void CheckGameClear();
        void Shellgame_init();
        void Reset();
        void ShuffleLeft();
        void ShuffleRight();
        void SwapRight();
        void SwapLeft();
        void MakeQuestion();
        void Cup0Clicked();
        void Cup1Clicked();
        void Cup2Clicked();
        int TaskTimes();
        void StopTask();
    }
}
