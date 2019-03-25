package com.hyunwoong.braintraining.contract;

import android.view.View;

import com.hyunwoong.braintraining.presenter.SettingPresenter;
import com.hyunwoong.braintraining.view.SettingView;

public interface SettingContract extends BaseContract {

    interface view {
        void bgmOn(View view);
        void bgmOff(View view);
        void pushOn(View view);
        void pushOff(View view);
        void onRemoveFavoriteClicked(View view);
        void onRemoveFriendButtonClicked(View view);
        void onSecessionButtonClicked(View view);
    }

    interface presenter {
        void SetView(SettingView view);
        void RemoveFavorite();
        void RemoveFreind();
        void SecessionClose();
        void AlarmDailyTraining();
        void SetPush(String s);
        void SetBgm(String s);
    }
}
