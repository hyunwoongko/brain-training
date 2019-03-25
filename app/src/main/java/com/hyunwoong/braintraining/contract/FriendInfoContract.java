package com.hyunwoong.braintraining.contract;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.view.FriendInfoView;

public interface FriendInfoContract {

    interface view{
        void SendButtonClicked(View view);
    }

    interface presenter{
        void SetView(ImageView FriendProfileImage,
                     TextView FriendNickName,
                     TextView FriendCode,
                     TextView GameData,
                     EditText editText,
                     FriendInfoView view);
        void ShowFriendInfo();
        void setGameDataText();
        String getGameData(Integer Data, String DataName);
        void setMessage();


    }
}
