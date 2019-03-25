package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.ColorGameContract;
import com.hyunwoong.braintraining.presenter.ColorGamePresenter;
import com.hyunwoong.braintraining.task.CountDownTask;

public class ColorGameView extends GameView implements ColorGameContract.view {
//반드시 휴대폰으로 테스트

    private TextView ready;
    private Chronometer chronometer;
    // View

    private ColorGamePresenter mPresenter;
    //  Presenter

    private CountDownTask task;
    private long offset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_color);

        mPresenter = new ColorGamePresenter();
        //Presenter

        TextView  txtSpeechInput = (TextView) findViewById(R.id.atext);
        TextView question = (TextView) findViewById(R.id.colorquestion);
        TextView rightAnswer = (TextView) findViewById(R.id.RightAnswer);
        chronometer = (Chronometer) findViewById(R.id.ColorGame_chronometer);
        ready = (TextView) findViewById(R.id.ready);
        // View

        mPresenter.SetView(txtSpeechInput, question, rightAnswer, chronometer, this);
        mPresenter.STTinit();
        mPresenter.MakeQuestion();
        mPresenter.BGM_Off(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        task = mPresenter.CountDown(chronometer, ready, this , offset);
        mPresenter.startSTT();
        mPresenter.BGM_Off(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        chronometer.stop();
        offset = SystemClock.elapsedRealtime() - chronometer.getBase();
        mPresenter.StopCountDownTask(task);
        mPresenter.BGM_Off(this);
        mPresenter.stopSTT();
        mPresenter.ClearSTT();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.ClearSTT();
        chronometer.setBase(SystemClock.elapsedRealtime());
        offset = 0;
        mPresenter = null;
    }

}