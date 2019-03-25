package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.MomentGameContract;
import com.hyunwoong.braintraining.presenter.MomentGamePresenter;
import com.hyunwoong.braintraining.task.CountDownTask;

public class MomentGameView extends GameView implements MomentGameContract.view {

    private MomentGamePresenter mPresenter;
    private TextView ready;
    private TextView t1, t2, t3, t4, t5, t6, t7, t8, t9;
    private CountDownTask task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_moment);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridview);
        TextView readygame = (TextView) findViewById(R.id.readygame);
        TextView moment = findViewById(R.id.moment);
        TextView Qnumber = findViewById(R.id.Qnumber);
        TextView RightAnswerNumber = findViewById(R.id.RightAnswer);
        ready = (TextView) findViewById(R.id.ready);

        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
        t3 = (TextView) findViewById(R.id.text3);
        t4 = (TextView) findViewById(R.id.text4);
        t5 = (TextView) findViewById(R.id.text5);
        t6 = (TextView) findViewById(R.id.text6);
        t7 = (TextView) findViewById(R.id.text7);
        t8 = (TextView) findViewById(R.id.text8);
        t9 = (TextView) findViewById(R.id.text9);

        mPresenter = new MomentGamePresenter();
        mPresenter.SetView(gridLayout, readygame, moment, Qnumber, RightAnswerNumber, this);
        mPresenter.SetTextButton(t1, t2, t3, t4, t5, t6, t7, t8, t9);
    }

    @Override
    public void text1Clicked(View v) {
        mPresenter.TextClicked(t1);
    }

    @Override
    public void text2Clicked(View v) {
        mPresenter.TextClicked(t2);
    }

    @Override
    public void text3Clicked(View v) {
        mPresenter.TextClicked(t3);
    }

    @Override
    public void text4Clicked(View v) {
        mPresenter.TextClicked(t4);
    }

    @Override
    public void text5Clicked(View v) {
        mPresenter.TextClicked(t5);
    }

    @Override
    public void text6Clicked(View v) {
        mPresenter.TextClicked(t6);
    }

    @Override
    public void text7Clicked(View v) {
        mPresenter.TextClicked(t7);
    }

    @Override
    public void text8Clicked(View v) {
        mPresenter.TextClicked(t8);
    }

    @Override
    public void text9Clicked(View v) {
        mPresenter.TextClicked(t9);
    }

    @Override
    protected void onResume() {
        super.onResume();
        task = mPresenter.CountDown(ready, this);
        mPresenter.gameCountDown();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.StopTask();
        mPresenter.StopCountDownTask(task);
    }
}
