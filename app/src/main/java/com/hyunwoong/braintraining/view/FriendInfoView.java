package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.FriendInfoContract;
import com.hyunwoong.braintraining.presenter.FriendInfoPresenter;

public class FriendInfoView extends BaseView implements FriendInfoContract.view{

    private FriendInfoPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new FriendInfoPresenter();
        setContentView(R.layout.view_friendinfo);
        ImageView FriendProfileImage = findViewById(R.id.FriendProfileImage);
        TextView FriendNickName = findViewById(R.id.FriendNickName);
        TextView FriendCode = findViewById(R.id.FriendCode);
        TextView GameData = findViewById(R.id.FriendGameData);
        EditText editText = findViewById(R.id.SendEditText);
        mPresenter.SetView(FriendProfileImage,FriendNickName,FriendCode,GameData,editText,this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.ShowFriendInfo();
    }
    @Override
    public void SendButtonClicked(View view) {
        mPresenter.setMessage();
    }
}
