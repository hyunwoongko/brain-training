package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.MainContract;
import com.hyunwoong.braintraining.presenter.MainPresenter;

public class MainView extends BaseView implements MainContract.view {

    private MainPresenter mPresenter;
    // Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
        mPresenter = new MainPresenter();

        TextView nickname = (TextView) findViewById(R.id.main_nickname);
        TextView userId = (TextView) findViewById(R.id.main_userid);
        ImageView profileImage = (ImageView) findViewById(R.id.main_profile_image);

        mPresenter.SetView(nickname, userId, profileImage, this);
        mPresenter.DrawUserInfo();
    }

    @Override
    public void onStartButtonClicked(View v) {
        mPresenter.Button_Beep();

        if (mPresenter.CheckFirstUser())
            mPresenter.MoveTo(TutorialView.class);
        else
            mPresenter.MoveTo(SelectView.class);
    }

    @Override
    public void onFriendButtonClicked(View v) {
        mPresenter.Button_Beep();
        mPresenter.MoveTo(FriendView.class);
    }

    @Override
    public void onFavoriteButtonClicked(View v) {
        mPresenter.Button_Beep();
        mPresenter.MoveTo(FavoriteView.class);
    }

    @Override
    public void onLogoutButtonClicked(View view) {
        mPresenter.Button_Beep();
        mPresenter.Logout();
    }

    @Override
    public void onBackPressed() {
        mPresenter.Toast("게임을 종료하시려면 먼저 로그아웃 해주시길 바랍니다.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.SetBGM("MainBGM");
    }

    @Override
    public void onDailyTrainingButtonClicked(View v) {
        mPresenter.Button_Beep();
        mPresenter.MoveTo(DailyTrainingView.class);
    }

    @Override
    public void onSettingButtonClicked(View view) {
        mPresenter.Button_Beep();
        mPresenter.MoveTo(SettingView.class);
    }

    @Override
    protected void onDestroy() {
        mPresenter.InfoDestory();
        mPresenter = null;
        super.onDestroy();
    }
}