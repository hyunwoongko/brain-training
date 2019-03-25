package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.BaseContract;
import com.hyunwoong.braintraining.contract.MixGameContract;
import com.hyunwoong.braintraining.presenter.MixGamePresenter;
import com.hyunwoong.braintraining.task.CountDownTask;

public class MixGameView extends GameView implements MixGameContract.view{

    private TextView ready;
    private MixGamePresenter mPresenter;
    private CountDownTask task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_mix);

        ImageView mixquestion1 = (ImageView) findViewById(R.id.mixquestion1);
        ImageView mixquestion2 = (ImageView) findViewById(R.id.mixquestion2);
        ImageView mixanswer1 = (ImageView) findViewById(R.id.mixanswer1);
        ImageView mixanswer2 = (ImageView) findViewById(R.id.mixanswer2);
        ImageView mixanswer3 = (ImageView) findViewById(R.id.mixanswer3);
        TextView RightAnswerNumber = (TextView) findViewById(R.id.RightAnswer);
        TextView Qnumber = (TextView) findViewById(R.id.Qnumber);
        ready = (TextView) findViewById(R.id.ready);

        mPresenter = new MixGamePresenter();
        mPresenter.SetView(mixquestion1, mixquestion2, mixanswer1, mixanswer2, mixanswer3,
                RightAnswerNumber, Qnumber, this);
        mPresenter.MakeQuestion();
    }


    @Override
    protected void onResume() {
        task = mPresenter.CountDown(ready, this);
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.StopCountDownTask(task);
    }

    @Override
    public void mix1Clicked(View v) {
        mPresenter.CheckAnswer(1);
    }

    @Override
    public void mix2Clicked(View v) {
        mPresenter.CheckAnswer(2);
    }

    @Override
    public void mix3Clicked(View v) {
        mPresenter.CheckAnswer(3);
    }

}
