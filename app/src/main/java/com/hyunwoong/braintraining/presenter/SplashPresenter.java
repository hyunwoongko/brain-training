package com.hyunwoong.braintraining.presenter;

import android.content.Intent;
import android.os.Handler;

import com.hyunwoong.braintraining.contract.SplashContract;
import com.hyunwoong.braintraining.model.DailyTrainingModel;
import com.hyunwoong.braintraining.model.SplashModel;
import com.hyunwoong.braintraining.view.LoginView;
import com.hyunwoong.braintraining.view.SplashView;

public class SplashPresenter extends BasePresenter implements SplashContract.presenter {

    private SplashView view;
    //View

    private SplashModel model;
    // Model

    @Override
    public void SetView(SplashView view) {
        super.SetView(view);
        this.view = view;
    }

    @Override
    public void ClearView() {
        super.ClearView();
        view = null;
    }

    @Override
    public void SetModel() {
        model = new SplashModel();
    }

    @Override
    public void ClearModel() {
        model = null;
    }

    @Override
    public void ShowSplash() {
        SetModel(); // 데이터 요청
        model.init(); // 데이터 초기화

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(view, LoginView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.startActivity(intent);
            }
        }, model.getSPLASH_TIME_OUT()); // 데이터 사용
        ClearModel(); // 데이터 사용 종료
    }

    @Override
    public void InitDailyMode() {
        DailyTrainingModel.getInstance().setIsTraining(false);
        DailyTrainingModel.getInstance().setGameNumber(0);
    }


}
