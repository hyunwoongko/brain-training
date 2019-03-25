package com.hyunwoong.braintraining.utils;

import com.hyunwoong.braintraining.database.BestScoreDB;
import com.hyunwoong.braintraining.database.OtherBestScoreDB;
import com.hyunwoong.braintraining.database.UserCurrentDB;
import com.hyunwoong.braintraining.database.UserFriendDB;
import com.hyunwoong.braintraining.database.UserOtherDB;

public interface Callback {

    interface BestScoreCallback {
        void onBestScoreCallback(BestScoreDB bestScoreDB);
    }

    interface UserCurrentCallback {
        void onUserCurrentCallback(UserCurrentDB userCurrentDB);
    }

    interface UserFriendCallback {
        void onUserFriendCallback(Object Value);
    }

    interface UserOtherCallback {
        void onUserOtherCallback(UserOtherDB userOtherDB);
    }

    interface UserOtherScoreCallback {
        void onUserOtherScoreCallback(OtherBestScoreDB otherBestScoreDB);
    }
}
