package com.hyunwoong.braintraining.model;

public class SplashModel {

    private int SPLASH_TIME_OUT;

    public void init(){
        SPLASH_TIME_OUT = 3000;
    }

    public int getSPLASH_TIME_OUT() {
        return SPLASH_TIME_OUT;
    }

    public void setSPLASH_TIME_OUT(int SPLASH_TIME_OUT) {
        this.SPLASH_TIME_OUT = SPLASH_TIME_OUT;
    }
}
