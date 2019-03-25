package com.hyunwoong.braintraining.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.hyunwoong.braintraining.contract.LoginContract;
import com.hyunwoong.braintraining.model.LoginModel;
import com.hyunwoong.braintraining.view.BaseView;
import com.hyunwoong.braintraining.view.LoginView;
import com.kakao.auth.Session;

public class LoginPresenter extends BasePresenter implements LoginContract.presenter {


    private LoginModel model;
    private LoginView view;
    // Model


    @Override
    public void SetView(LoginView view) {
        this.view = view;
        super.SetView(view);
    }

    @Override
    public void SetModel() {
        super.SetModel();
        model = new LoginModel();
    }

    @Override
    public void ClearModel() {
        super.ClearModel();
        model = null;
    }

    @Override
    public void InitModel() {
        model.init();
    }

    @Override
    public void SetSession(int requestCode, int resultCode, Intent data) {
        Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data);
    }

    @Override
    public String[] getPERMISSION() {
        return model.getPERMISSION();
    }

    @Override
    public void setPERMISSIO1N(String... str) {
        SetModel();
        model.setPERMISSION(str);
    }

    @Override
    public boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }  // multiple permission
}
