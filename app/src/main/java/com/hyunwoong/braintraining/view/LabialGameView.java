package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.LabialGameContract;
import com.hyunwoong.braintraining.presenter.LabialGamePresenter;
import com.hyunwoong.braintraining.task.CountDownTask;
import com.hyunwoong.braintraining.utils.NewtonTTSApi;
import com.hyunwoong.braintraining.utils.TypeWriter;
import com.myscript.atk.scw.SingleCharWidgetApi;

public class LabialGameView extends GameView implements LabialGameContract.view {

    private TextView textInput;
    private TextView ready;
    private LabialGamePresenter mPresenter;
    private CountDownTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_labial);

        mPresenter = new LabialGamePresenter();

        SingleCharWidgetApi widgetApi = findViewById(R.id.widget);
        TypeWriter typeWriter = findViewById(R.id.typewriter);
        ImageButton buttonStartStop = (ImageButton) findViewById(R.id.imageButtonStart);
        TextView Qnumber = findViewById(R.id.Qnumber);
        TextView RightAnswerNumber = findViewById(R.id.RightAnswer);
        textInput = findViewById(R.id.textInPut);
        ready = findViewById(R.id.ready);
        mPresenter.SetView(typeWriter, textInput, widgetApi, Qnumber, RightAnswerNumber, this);
        mPresenter.init();
        widgetApi.setOnTextChangedListener(new SingleCharWidgetApi.OnTextChangedListener() {
            @Override
            public void onTextChanged(SingleCharWidgetApi singleCharWidgetApi, String s, boolean b) {
                textInput.setText(s);
            }
        });

        buttonStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NewtonTTSApi.getInstance(LabialGameView.this).isPlaying()) {
                    mPresenter.MakeQuestion();
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.StopTTS();
        mPresenter.StopCountDownTask(task);
    }

    @Override
    public void onDeleteButtonClicked(View view) {
        mPresenter.Delete();

    }

    @Override
    public void onSubmittedButtonClicked(View view) {
        if (!mPresenter.isPlaying()) {
            mPresenter.Submit();
        } else {
            mPresenter.avoidSubmit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.BGM_Off(this);
        task = mPresenter.CountDown(ready, this);
    }

}
