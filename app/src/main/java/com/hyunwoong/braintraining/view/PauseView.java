package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.PauseContract;
import com.hyunwoong.braintraining.presenter.BasePresenter;

public class PauseView extends BaseView implements PauseContract.view{

    private BasePresenter bPresenter;
    private TextView pause;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pause);
        bPresenter = new BasePresenter();
        pause = findViewById(R.id.pause);
        bPresenter.SetView(this);

    }
    @Override
    public void onResumeClicked(View view) {
        pause.setVisibility(View.GONE);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bPresenter.BGM_Off(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
