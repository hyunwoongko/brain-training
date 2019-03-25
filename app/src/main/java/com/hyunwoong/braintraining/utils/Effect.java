package com.hyunwoong.braintraining.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.hyunwoong.braintraining.R;

public class Effect {

    // 효과음을 담당하는 클래스임. 버튼음과 카운트다운음을 담당.
    // MpHelper를 호출하여 Clear , Start 등의 메소드를 사용.

    private static Effect mInstance = null;

    private Effect() {
    }

    public static synchronized Effect getInstance() {
        if (mInstance == null) {
        }
        try {
            if (mInstance == null)
                mInstance = new Effect();
            return mInstance;
        } finally {
        }
    }


    private MediaPlayer mediaPlayer;
    private MediaHelper helper = new MediaHelper(mediaPlayer);

    public void Button_Beep(Context context) {

        helper.MediaPlayerClear();
        helper.MediaPlayerStart(context, R.raw.button);
    }

    public void CountDown_Beep(Context context) {

        helper.MediaPlayerClear();
        helper.MediaPlayerStart(context, R.raw.countdown);
    }

    public void RightAnswer_Beep(Context context) {

        helper.MediaPlayerClear();
        helper.MediaPlayerStart(context, R.raw.dingdong);
    }

    public void Shuffle(Context context) {

        helper.MediaPlayerClear();
        helper.MediaPlayerStart(context, R.raw.shuffle);
    }

    public void WrongAnswer_Beep(Context context) {

        helper.MediaPlayerClear();
        helper.MediaPlayerStart(context, R.raw.dang);
    }

    public void StopEffect(){
        helper.MediaPlayerStop();
    }
}