package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.CountGameContract;
import com.hyunwoong.braintraining.presenter.CountGamePresenter;
import com.hyunwoong.braintraining.task.CountDownTask;

public class CountGameView extends GameView implements CountGameContract.view {

    private TextView ready;
    private Chronometer chronometer;
    // View

    private long offset = 0;
    private CountGamePresenter mPresenter;
    private CountDownTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_count);
        mPresenter = new CountGamePresenter();

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridview);
        gridLayout.removeAllViews();
        TextView remaining_number = (TextView) findViewById(R.id.CountGame_remaining_number);
        ready = (TextView) findViewById(R.id.ready);
        chronometer = (Chronometer) findViewById(R.id.CountGame_chronometer);

        mPresenter.SetView(gridLayout, remaining_number, chronometer, this);
        mPresenter.MakeButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        task = mPresenter.CountDown(chronometer, ready, this , offset);
    }

    @Override
    protected void onPause() {
        super.onPause();
        chronometer.stop();
        offset = SystemClock.elapsedRealtime() - chronometer.getBase();
        mPresenter.StopCountDownTask(task);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        chronometer.setBase(SystemClock.elapsedRealtime());
        offset = 0;
        mPresenter = null;
    }

}