package com.hyunwoong.braintraining.utils;

import android.widget.Chronometer;

public class Scorer {

    Chronometer chronometer;
    private int rightAnswer;

    public Scorer(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

    public Scorer(Chronometer chronometer, int rightAnswer) {
        this.chronometer = chronometer;
        this.rightAnswer = rightAnswer;
    }

    public Scorer(int rightAnswer){
        this.rightAnswer = rightAnswer;
    }

    public Integer ScoreTime() {

        int second_1 = Integer.valueOf(String.valueOf(chronometer.getText().toString().charAt(4)));
        int second_10 = Integer.valueOf(String.valueOf(chronometer.getText().toString().charAt(3)));
        int minutes_1 = Integer.valueOf(String.valueOf(chronometer.getText().toString().charAt(1)));
        int minutes_10 = Integer.valueOf(String.valueOf(chronometer.getText().toString().charAt(0)));

        Integer score = second_1 + (second_10 * 10) + (minutes_1 * 60) + (minutes_10 * 600);
        // 크로노미터에 저장된 숫자들을 초단위로 변환
        return score;
        // 클리어하는데 총 몇 초 걸렸는지
    }   // 시간초로 게임 스코어를 계산하는 경우

    public int ScoreAnswer() {
        return rightAnswer;
    }

}
