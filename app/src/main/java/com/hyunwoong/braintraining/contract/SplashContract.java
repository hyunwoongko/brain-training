package com.hyunwoong.braintraining.contract;

import com.hyunwoong.braintraining.view.SplashView;

public interface SplashContract extends BaseContract{

    interface view {
    }

    interface presenter {
        void SetView(SplashView view);
        void ClearView();
        // View

        void SetModel();
        void ClearModel();
        // Model

        void ShowSplash();
        void InitDailyMode();
        // Splash
    }

}
