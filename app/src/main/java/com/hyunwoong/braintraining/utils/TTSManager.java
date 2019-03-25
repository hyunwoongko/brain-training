package com.hyunwoong.braintraining.utils;


import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.kakao.sdk.newtoneapi.impl.util.PermissionUtils;

public final class TTSManager {
    private static volatile TTSManager instance;
    private static final String TAG = "TextToSpeechManager";

    private Context appContext;
    private boolean initialized;


    public static TTSManager getInstance() {
        if(instance == null) {
            synchronized (TTSManager.class) {
                if(instance == null) {
                    TTSManager temp = new TTSManager();
                    instance = temp;
                }
            }
        }
        return instance;
    }


    public boolean initializeLibrary(Context context) throws IllegalArgumentException {
        if(context == null) {
            throw new IllegalArgumentException("context must not be null!");
        }

        if (initialized) {
            Log.d(TAG, "already initialized the library.");
            return true;
        }

        if(context instanceof Application) {
            appContext = context;
        }
        else {
            appContext = context.getApplicationContext();
        }

        com.dialoid.speech.tts.TextToSpeech.initializeLibrary(context);

        if (!PermissionUtils.checkMandatoryPermission(context, Manifest.permission.INTERNET)) {
            return false;
        }
        if (!PermissionUtils.checkMandatoryPermission(context, Manifest.permission.RECORD_AUDIO)) {
            return false;
        }
        if (!PermissionUtils.checkMandatoryPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            return false;
        }

        Log.d(TAG, "[textToSpeech] version : " + getVersion());
        initialized = true;

        return true;
    }

    public void finalizeLibrary() {
        com.dialoid.speech.tts.TextToSpeech.finalizeLibrary();
        initialized = false;
    }

    public Context getApplicationContext() {
        return appContext;
    }


    public boolean isInitialized() {
        return initialized;
    }

    public String getVersion() {
        return "5.0";
    }
}
