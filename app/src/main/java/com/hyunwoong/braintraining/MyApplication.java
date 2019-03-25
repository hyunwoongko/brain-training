package com.hyunwoong.braintraining;


import android.app.Activity;
import android.app.Application;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import com.hyunwoong.braintraining.adapter.KakaoSDKAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.kakao.auth.KakaoSDK;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.android.volley.VolleyLog.TAG;

public class MyApplication extends Application {
    private static volatile MyApplication instance = null;
    private static volatile Activity currentActivity = null;
    FirebaseDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        KakaoSDK.init(new KakaoSDKAdapter());
        database = FirebaseDatabase.getInstance();

    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        MyApplication.currentActivity = currentActivity;
    }

    /**
     * singleton 애플리케이션 객체를 얻는다.
     *
     * @return singleton 애플리케이션 객체
     */
    public static MyApplication getGlobalApplicationContext() {
        if (instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    /**
     * 애플리케이션 종료시 singleton 어플리케이션 객체 초기화한다.
     */
    @Override
    public void onTerminate() {

        super.onTerminate();
        instance = null;
    }


}