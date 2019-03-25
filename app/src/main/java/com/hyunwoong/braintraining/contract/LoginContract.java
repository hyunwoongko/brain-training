package com.hyunwoong.braintraining.contract;

import android.content.Context;
import android.content.Intent;

import com.hyunwoong.braintraining.view.LoginView;
import com.google.android.gms.tasks.Task;
import com.kakao.usermgmt.LoginButton;

public interface LoginContract extends BaseContract {

    interface view {
        Task<String> getFirebaseJwt(final String kakaoAccessToken,String url);
    }

    interface presenter {
        void SetView(LoginView view);
        void SetSession(int requestCode, int resultCode, Intent data);
        String[] getPERMISSION();
        void setPERMISSIO1N(String...str);
        boolean hasPermissions(Context context, String... permissions);

    }
}
