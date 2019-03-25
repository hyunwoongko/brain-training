package com.hyunwoong.braintraining.contract;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.view.ScoreView;
import com.hyunwoong.braintraining.utils.TypeWriter;

public interface ScoreContract extends BaseContract{


    interface view {
        void onBackButtonToSelectGameClicked(View v);
    }

    interface presenter {
        void SetView(TextView nickname,
                     TextView userId,
                     ImageView profileImage,
                     TypeWriter score,
                     ScoreView view);

        void drawUserInfo();

        void upDateScore();

        void SavaGameData();


    }


}
