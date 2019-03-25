package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.SettingContract;
import com.hyunwoong.braintraining.presenter.SettingPresenter;

public class SettingView extends BaseView implements SettingContract.view {

    private SettingPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_setting);
        mPresenter = new SettingPresenter();
        mPresenter.SetView(this);
    }

    @Override
    public void bgmOn(View view) {
        mPresenter.SetBgm("ON");
        mPresenter.Toast("배경음악을 켰습니다.");
        mPresenter.BGM_On(getApplicationContext());
    }

    @Override
    public void bgmOff(View view) {
        mPresenter.SetBgm("OFF");
        mPresenter.Toast("배경음악을 껐습니다.");
        mPresenter.BGM_Off(getApplicationContext());
    }

    @Override
    public void pushOn(View view) {
        mPresenter.SetPush("ON");
        mPresenter.Toast("푸시 알림을 켰습니다.");
        mPresenter.AlarmDailyTraining();
    }

    @Override
    public void pushOff(View view) {
        mPresenter.SetPush("OFF");
        mPresenter.Toast("푸시 알림을 껐습니다.");
    }

    @Override
    public void onRemoveFavoriteClicked(View view) {
        mPresenter.RemoveFavorite();
    }

    @Override
    public void onRemoveFriendButtonClicked(View view) {
        mPresenter.RemoveFreind();
    }

    @Override
    public void onSecessionButtonClicked(View view) {
        mPresenter.SecessionClose();
    }
}
