package com.hyunwoong.braintraining.utils;

import android.app.Activity;

import com.hyunwoong.braintraining.database.UserOtherDB;
import com.kakao.sdk.newtoneapi.SpeechRecognizerClient;
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager;

public class NewtonSTTApi {

    private Activity activity;
    private SpeechRecognizerClient.Builder builder;
    private SpeechRecognizerClient client;

    private volatile static NewtonSTTApi mInstance = null;

    private NewtonSTTApi(Activity activity) {
    this.activity = activity;
    }

    public static NewtonSTTApi getInstance(Activity activity) {
        if (mInstance == null) {
            synchronized (UserOtherDB.class) {
                if (mInstance == null) {
                    mInstance = new NewtonSTTApi(activity);

                }
            }
        }
        return mInstance;
    } // Singleton


    public void init() {
        SpeechRecognizerManager.getInstance().initializeLibrary(activity);

        // 클라이언트 생성
        builder = new SpeechRecognizerClient.Builder().
                setServiceType(SpeechRecognizerClient.SERVICE_TYPE_WEB);

        client = builder.build();
    }

    public SpeechRecognizerClient.Builder getBuilder() {
        return builder;
    }

    public SpeechRecognizerClient getClient() {
        return client;
    }

    public void setBuilder(SpeechRecognizerClient.Builder builder) {
        this.builder = builder;
    }

    public void setClient(SpeechRecognizerClient client) {
        this.client = client;
    }

    public void StartSTT(){
        if(client!=null) {
            client.startRecording(false);
        }
        else {
           ClearSTT();
        }
    }

    public void StopSTT(){
            client.stopRecording();
            client.cancelRecording();

    }

    public void ClearSTT(){
        SpeechRecognizerManager.getInstance().finalizeLibrary();
    }
}
