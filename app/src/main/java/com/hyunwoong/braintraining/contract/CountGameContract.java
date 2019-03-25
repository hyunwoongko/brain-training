package com.hyunwoong.braintraining.contract;

import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.TextView;

import com.hyunwoong.braintraining.view.CountGameView;

public interface CountGameContract extends GameContract {

    interface view {

    }

    interface presenter {
        void SetView(GridLayout gridLayout,
                     TextView remaining_number,
                     Chronometer chronometer,
                     CountGameView view);

        void MakeButton();
        void SaveData();
        void GameClear();
        Integer BestScore();


    }
}
