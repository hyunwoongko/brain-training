package com.hyunwoong.braintraining.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.utils.MediaHelper;


public class BGMService extends Service {

    private MediaPlayer mediaPlayer;
    private MediaHelper helper = new MediaHelper(mediaPlayer);

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public void onStart(Intent intent, int startId) {
        PlayBGM("MainBGM", R.raw.main, true);
        PlayBGM("SelectBGM", R.raw.select, true);
        PlayBGM("GameBGM", R.raw.game, true);
        PlayBGM("ScoreBGM", R.raw.score, true);
        PlayBGM("FriendBGM", R.raw.friend, true);
        PlayBGM("DailyBGM",R.raw.dailytraining,true);
        PlayBGM("SplashBGM", R.raw.splash, false);
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        helper.MediaPlayerStop();
    }

    public void PlayBGM(String name, int resid, boolean repeat) {
        if (BGMmodel.getInstance().getBgmName() == name) {
            helper.MediaPlayerClear();
            helper.MediaPlayerStart(this, resid);
            helper.MediaPlayerLoop(repeat);
        }
    }

}