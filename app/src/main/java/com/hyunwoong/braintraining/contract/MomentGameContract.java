package com.hyunwoong.braintraining.contract;

import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.hyunwoong.braintraining.view.MomentGameView;

public interface MomentGameContract {

    interface view {
        void text1Clicked(View v);

        void text2Clicked(View v);

        void text3Clicked(View v);

        void text4Clicked(View v);

        void text5Clicked(View v);

        void text6Clicked(View v);

        void text7Clicked(View v);

        void text8Clicked(View v);

        void text9Clicked(View v);
    }

    interface presenter {
        void SetView(GridLayout gridLayout,
                     TextView readygame,
                     TextView moment,
                     TextView Qnumber,
                     TextView RightAnswerNumber,
                     MomentGameView view);

        void SetTextButton(TextView t1, TextView t2, TextView t3,
                           TextView t4, TextView t5, TextView t6,
                           TextView t7, TextView t8, TextView t9);

        void gameCountDown();
        void MakeButton();
        void TextClicked(TextView textView);
        void CheckClear();
        void Hide();
        void StopTask();
    }
}
