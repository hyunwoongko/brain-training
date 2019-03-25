package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.ScoreContract;
import com.hyunwoong.braintraining.presenter.ScorePresenter;
import com.hyunwoong.braintraining.utils.TypeWriter;

public class ScoreView extends BaseView implements ScoreContract.view {

    private ScorePresenter mPresenter;
    // Presenter

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_score);
        mPresenter = new ScorePresenter();

        TextView nickname = (TextView) findViewById(R.id.score_nickname);
        TextView userId = (TextView) findViewById(R.id.score_userid);
        ImageView profileImage = (ImageView) findViewById(R.id.score_profile_image);
        TypeWriter score = (TypeWriter) findViewById(R.id.score);

        mPresenter.SetView(nickname,userId,profileImage,score,this);
        mPresenter.drawUserInfo();
        mPresenter.upDateScore();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.SetBGM("ScoreBGM");
        mPresenter.SavaGameData();
    }

    @Override
    public void onBackButtonToSelectGameClicked(View v) {
        mPresenter.Button_Beep();
        mPresenter.MoveTo(MainView.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }
    @Override
    public void onBackPressed() {
    }
}
