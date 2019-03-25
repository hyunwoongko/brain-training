package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.SplashContract;
import com.hyunwoong.braintraining.model.ServerModel;
import com.hyunwoong.braintraining.presenter.SplashPresenter;

public class SplashView extends BaseView implements SplashContract.view {

    private SplashPresenter mPresenter;
    //Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_splash);

        /* Init Action */
        mPresenter = new SplashPresenter();
        mPresenter.SetView(this);
        mPresenter.FCM_AutoInit();
        mPresenter.ShowSplash();
        mPresenter.InitDailyMode();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.SetBGM("SplashBGM");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

}
