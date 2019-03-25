package com.hyunwoong.braintraining.contract;

import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.hyunwoong.braintraining.view.CalculateGameView;
import com.hyunwoong.braintraining.utils.TypeWriter;
import com.myscript.atk.scw.SingleCharWidgetApi;

public interface CalculateGameContract extends GameContract {

    interface view {
        void onDeleteButtonClicked(View view);
        void onSubmittedButtonClicked(View view);
    }

    interface presenter {
        void SetView(Chronometer chronometer,
                            TypeWriter mQuestionField,
                            TextView mTextField,
                            TextView mCorrectField,
                            SingleCharWidgetApi widget,
                            CalculateGameView view);

        void MyScriptBuild();
        void MakeQuestion();
        void Delete();
        void Submit();
    }
}
