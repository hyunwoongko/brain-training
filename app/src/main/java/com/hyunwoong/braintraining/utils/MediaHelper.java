package com.hyunwoong.braintraining.utils;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaHelper {

    // 미디어 플레이어 객체의 초기화와 재생을 담당하는 Helper
    // 미디어 플레이어 클래스의 메소드를 사용해 또 다른 메소드를 만들어냄 -> 위임 (delegation)
    // 향후 다른 프로젝트 진행할때도 코드를 재사용 할 수 있게 모듈화 시켜봄.

    private MediaPlayer mediaPlayer;

    public MediaHelper(MediaPlayer mediaPlayer){
        // 실제 미디어 플레이어 객체가 생성되는 곳에서 MpHelper를 생성 할 것 !
        this.mediaPlayer = mediaPlayer;
    }

    public void MediaPlayerClear() {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        } // 버튼음같은 효과음들은 버튼을 빠르게 클릭하면 너무나 많은 미디어 플레이어 객체가 생성됨.
    }     // 때문에 버튼을 빠르게 누르면 MediaPlayer에 에러가 발생해 객체가 죽어버리는 현상이 발생함.
          // MediaPlayerClear 메소드는 재생하기 전 기존에 존재하던 MediaPlayer를 정지하고 객체를 소멸시킴
          // 항상 한개의 MediaPlayer객체만이 존재하게 됨.

    public void MediaPlayerStart(Context context , int resid) {
        mediaPlayer = MediaPlayer.create(context, resid);
        mediaPlayer.start();
    }

    public void MediaPlayerStop(){
        if(mediaPlayer!=null) {
            mediaPlayer.stop();
        }
    }

    public void MediaPlayerLoop(boolean loop){
        if(mediaPlayer!=null) {
            mediaPlayer.setLooping(loop);
        }
    }
}