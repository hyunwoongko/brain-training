package com.hyunwoong.braintraining.view;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hyunwoong.braintraining.contract.BaseContract;
import com.hyunwoong.braintraining.presenter.BasePresenter;
import com.hyunwoong.braintraining.service.DynamicReceiverService;
import com.hyunwoong.braintraining.receiver.ReceiverModel;

public abstract class BaseView extends AppCompatActivity implements BaseContract.view {

    private BasePresenter mPresenter;
    //Presenter

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new BasePresenter(); // 프레젠터 생성
        mPresenter.SetView(this); // 뷰 세팅
        mPresenter.FullScreen(); // 풀스크린 모드
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.BGM_On(this);
        mPresenter.setReceiveMode("BASE");
        mPresenter.ReceiveOn(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.BGM_Off(this); // BGM 서비스 정지
        mPresenter.ReceiveOff(getApplicationContext());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {// 모델 , 뷰 , 프레젠터는 생명주기를 함께함.
        super.onDestroy();
        mPresenter = null;
    }
}

