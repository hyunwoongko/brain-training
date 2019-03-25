package com.hyunwoong.braintraining.contract;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.view.MainView;

public interface MainContract extends BaseContract {

    interface view {
        void onStartButtonClicked(View v);
        void onLogoutButtonClicked(View view);
        void onFriendButtonClicked(View v);
        void onFavoriteButtonClicked(View v);
        void onDailyTrainingButtonClicked(View v);
        void onSettingButtonClicked(View view);
    }

    interface presenter {
        void SetView(TextView nickname,
                     TextView userId,
                     ImageView profileImage,
                     MainView view);

        void DrawUserInfo();
        boolean CheckFirstUser();
        void InfoDestory();
        void CleanView();
        void Logout();
    }
}
