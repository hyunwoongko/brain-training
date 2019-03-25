package com.hyunwoong.braintraining.contract;

import android.widget.Chronometer;
import android.widget.TextView;

import com.hyunwoong.braintraining.view.ColorGameView;

public interface ColorGameContract extends GameContract {

    interface view {

    }

    interface presenter {
        void SetView(
                     TextView txtSpeechInput,
                     TextView question,
                     TextView color_text_game_right_answer,
                     Chronometer chronometer,
                     ColorGameView view);

        void ClearView();
        void SetModel();
        void ClearModel();
        void MakeQuestion();
        void NextQuestion();
        void SaveData();
        void CheckAnswer(String result1, String result2, String result3, String result4, String result5);
        //Game

        void STTinit();
        void startSTT();
        void stopSTT();
        void ClearSTT();
        //STT


    }
}
