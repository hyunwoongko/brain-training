package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hyunwoong.braintraining.contract.GameContract;
import com.hyunwoong.braintraining.presenter.BasePresenter;
import com.hyunwoong.braintraining.receiver.ReceiverModel;

public abstract class GameView extends BaseView implements GameContract.veiw {

    private BasePresenter bPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bPresenter = new BasePresenter();
        bPresenter.SetView(this);
    }

    @Override
    public void onBackPressed() {
        bPresenter.GameBackButtonAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bPresenter.SetBGM("GameBGM");
        bPresenter.setReceiveMode("GAME");
        bPresenter.ReceiveOn(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
