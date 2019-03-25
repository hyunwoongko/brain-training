package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.ShellGameContract;
import com.hyunwoong.braintraining.presenter.ShellGamePresenter;
import com.hyunwoong.braintraining.task.CountDownTask;

public class ShellGameView extends GameView implements ShellGameContract.view {
//실제 야바위 게임을 생각하면서 로직을 짜보자!

    private TextView ready;
    private CountDownTask task;
    private ShellGamePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_shell);

        ImageView cup0 = (ImageView) findViewById(R.id.cup0);
        ImageView cup1 = (ImageView) findViewById(R.id.cup1);
        ImageView cup2 = (ImageView) findViewById(R.id.cup2);
        Button swap_R = (Button) findViewById(R.id.swap_right);
        TextView Qnumber = findViewById(R.id.Qnumber);
        TextView RightAnswerNum = findViewById(R.id.RightAnswer);
        ready = (TextView) findViewById(R.id.ready);

        mPresenter = new ShellGamePresenter();
        mPresenter.SetView(cup0, cup1, cup2, swap_R, Qnumber, RightAnswerNum, this);
        mPresenter.Shellgame_init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.Reset();
        task = mPresenter.CountDown(ready, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.StopCountDownTask(task);
        mPresenter.StopTask();
    }
    @Override
    public void onSwapRightButtonClicked(View v) {
        mPresenter.MakeQuestion();
    }
    @Override
    public void cup0Clicked(View v) {
        mPresenter.Cup0Clicked();
    }
    @Override
    public void cup1Clicked(View v) {
        mPresenter.Cup1Clicked();
    }
    @Override
    public void cup2Clicked(View v) {
        mPresenter.Cup2Clicked();
    }

}

