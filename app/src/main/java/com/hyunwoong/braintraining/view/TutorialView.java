package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.TutorialContract;
import com.hyunwoong.braintraining.presenter.TutorialPresenter;
import com.hyunwoong.braintraining.utils.TypeWriter;

public class TutorialView extends BaseView implements TutorialContract.view {

    private TutorialPresenter mPresenter;
    // Presenter

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tutorial);

        TypeWriter typeWriter = (TypeWriter) findViewById(R.id.tutorial_dialogue);
        Button StartButton = (Button) findViewById(R.id.EnterButton);
        mPresenter = new TutorialPresenter();
        mPresenter.SetView(typeWriter, StartButton, this);
        mPresenter.NoMoreFirstUser();

    }

    @Override
    public void onTutorialDialogueClicked(View v) {
        mPresenter.Button_Beep();
        mPresenter.Dialog();
    }

    @Override
    public void onStartGameInTutorialClicked(View v) {
        mPresenter.Button_Beep();
        mPresenter.MoveTo(SelectView.class);
    }

    @Override
    protected void onResume() {
        mPresenter.SetBGM("SelectBGM");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mPresenter = null;
        super.onDestroy();
    }
}