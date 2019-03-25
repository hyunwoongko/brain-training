package com.hyunwoong.braintraining.utils;

import android.app.Activity;

import com.hyunwoong.braintraining.database.UserOtherDB;
import com.kakao.sdk.newtoneapi.TextToSpeechClient;
import com.kakao.sdk.newtoneapi.TextToSpeechListener;

public class NewtonTTSApi implements TextToSpeechListener {

    private TTSClient ttsClient;
    private Activity activity;
    private GetRandom random;

    private volatile static NewtonTTSApi mInstance = null;

    private NewtonTTSApi(Activity activity) {
        this.activity = activity;
    }

    public static NewtonTTSApi getInstance(Activity activity) {
        if (mInstance == null) {
            synchronized (UserOtherDB.class) {
                if (mInstance == null) {
                    mInstance = new NewtonTTSApi(activity);

                }
            }
        }
        return mInstance;
    } // Singleton

    public void Init() {
        TTSManager.getInstance().initializeLibrary(activity);
        random = new GetRandom();
        String[] Type = {TextToSpeechClient.VOICE_MAN_READ_CALM,
                TextToSpeechClient.VOICE_WOMAN_READ_CALM,
        };

        int i = random.getRandom_Overlap(2);
        ttsClient = new TTSClient.Builder()
                .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_1)
                .setSpeechSpeed(0.6)
                .setSpeechVoice(Type[i])
                .setListener(this)
                .build();

    }

    public boolean isPlaying(){
       return ttsClient.isPlaying();
    }

    public void playTTS(String s) {
        ttsClient.play(s);
    }

    public void stopTTS() {
        ttsClient.stop();
    }

    public void ClearTTS() {
        TTSManager.getInstance().finalizeLibrary();
    }

    @Override
    public void onFinished() {

    }

    @Override
    public void onError(int code, String message) {

    }
}
