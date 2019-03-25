package com.hyunwoong.braintraining.contract;

import android.view.View;
import android.widget.Button;

import com.hyunwoong.braintraining.view.TutorialView;
import com.hyunwoong.braintraining.utils.TypeWriter;

public interface TutorialContract extends BaseContract {

    interface view {
        void onTutorialDialogueClicked(View v);
        void onStartGameInTutorialClicked(View v);
    }

    interface presenter {
        void SetView(TypeWriter typeWriter, Button button, TutorialView view);
        void ClearView();
        void Dialog();
        void NoMoreFirstUser();
    }
}
