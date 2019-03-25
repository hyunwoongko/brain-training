package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.CalculateGameContract;
import com.hyunwoong.braintraining.presenter.CalculateGamePresenter;
import com.hyunwoong.braintraining.task.CountDownTask;
import com.hyunwoong.braintraining.utils.TypeWriter;
import com.myscript.atk.scw.SingleCharWidget;
import com.myscript.atk.scw.SingleCharWidgetApi;

public class CalculateGameView extends GameView implements CalculateGameContract.view{

    private Chronometer chronometer;
    private TextView ready;
    // View

    private CalculateGamePresenter mPresenter;
    // Presenter

    private CountDownTask task;
    private long offset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_calculate);

        mPresenter = new CalculateGamePresenter();

        chronometer = (Chronometer) findViewById(R.id.CalculateGame_chronometer);
        ready = (TextView) findViewById(R.id.ready);
        TypeWriter mQuestionField = (TypeWriter) findViewById(R.id.mQuestionField);
        TextView mTextField = (TextView) findViewById(R.id.mTextField);
        TextView mCorrectField = (TextView) findViewById(R.id.mCorrectField);
        SingleCharWidgetApi widget = (SingleCharWidget) findViewById(R.id.widget);

        mPresenter.SetView(chronometer, mQuestionField, mTextField, mCorrectField, widget, this);
        mPresenter.MyScriptBuild();
        mPresenter.MakeQuestion();


    }

    @Override
    public void onDeleteButtonClicked(View view) {
        mPresenter.Delete();

    }
    @Override
    public void onSubmittedButtonClicked(View view) {
        mPresenter.Submit();
    }

    @Override
    protected void onPause() {
        chronometer.stop();
        offset = SystemClock.elapsedRealtime() - chronometer.getBase();
        super.onPause();
        mPresenter.StopCountDownTask(task);
    }

    @Override
    protected void onResume() {
        super.onResume();
        task = mPresenter.CountDown(chronometer, ready, this , offset);
    }

    @Override
    protected void onDestroy() {
        mPresenter = null;
        chronometer.setBase(SystemClock.elapsedRealtime());
        offset = 0;
        super.onDestroy();
    }
}