package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.WordGameContract;
import com.hyunwoong.braintraining.presenter.WordGamePresenter;
import com.hyunwoong.braintraining.task.CountDownTask;
import com.myscript.atk.scw.SingleCharWidgetApi;

public class WordGameView extends GameView implements WordGameContract.view{

    private TextView ready;
    private CountDownTask task;
    private WordGamePresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_word);

        ready = findViewById(R.id.ready);
        // Global view Init

        final TextView mTextfield = findViewById(R.id.mTextField);
        GridLayout gridLayout = findViewById(R.id.gridview);
        TextView readygame =  findViewById(R.id.readygame);
        TextView word = findViewById(R.id.word);
        TextView SubmitNum = findViewById(R.id.SubmitNum);
        TextView RightAnswerNumber = findViewById(R.id.RightAnswer);
        SingleCharWidgetApi widgetApi = findViewById(R.id.widget);
        widgetApi.setOnTextChangedListener(new SingleCharWidgetApi.OnTextChangedListener() {
            @Override
            public void onTextChanged(SingleCharWidgetApi singleCharWidgetApi, String s, boolean b) {
                mTextfield.setText(s);
            }
        });// Local view Init

        /* Init Action */
        mPresenter = new WordGamePresenter();
        mPresenter.SetView(gridLayout, widgetApi, mTextfield, readygame, word,
                SubmitNum, RightAnswerNumber, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        task = mPresenter.CountDown(ready, this);
        mPresenter.WordgameCountdown();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.StopTask();
        mPresenter.StopCountDownTask(task);
    }
    @Override
    public void onDeleteButtonClicked(View view) {
        mPresenter.Delete();
    }
    @Override
    public void onSubmittedButtonClicked(View view) {
        mPresenter.Submit();
    }
}
