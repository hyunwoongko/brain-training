package com.hyunwoong.braintraining.contract;

import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.hyunwoong.braintraining.view.WordGameView;
import com.myscript.atk.scw.SingleCharWidgetApi;

public interface WordGameContract extends BaseContract{
    
    interface view{
        void onDeleteButtonClicked(View view);
        void onSubmittedButtonClicked(View view);
    }
    
    interface presenter{
        void SetView(GridLayout gridLayout,
                     SingleCharWidgetApi widgetApi,
                     TextView mTextfield,
                     TextView readygame,
                     TextView word,
                     TextView SubmitNum,
                     TextView RightAnswerNumber,
                     WordGameView view);

        void initModel();
        void Build();
        void MakeTextField();
        void Hide();
        void Delete();
        void Submit();
        void WordgameCountdown();
        void StopTask();
    }
}
