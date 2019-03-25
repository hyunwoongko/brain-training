package com.hyunwoong.braintraining.contract;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.view.MixGameView;

public interface MixGameContract extends BaseContract{

    interface view{
        void mix1Clicked(View v);
        void mix2Clicked(View v);
        void mix3Clicked(View v);
    }

    interface presenter{
        void SetView(ImageView mixquestion1,
                     ImageView mixquestion2,
                     ImageView mixanswer1,
                     ImageView mixanswer2,
                     ImageView mixanswer3,
                     TextView RightAnswerNumber,
                     TextView Qnumber,
                     MixGameView view);
        void MakeQuestion();
        String AssignColor(int ColorNum, ImageView imageView);
        String ColorMixer(String First, String Second);
        String RandomColorMaker();
        void CheckAnswer(int num);
    }
}
