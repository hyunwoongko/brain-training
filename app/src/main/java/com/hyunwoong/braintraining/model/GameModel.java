package com.hyunwoong.braintraining.model;

public class GameModel {

    private long DURATION_TIME;
    private long prevPressTime;

    public void init() {
        DURATION_TIME = 2000L;
        prevPressTime = 0L;
    }

    public long getDurationTime() {
        return DURATION_TIME;
    }
    public long getPrevPressTime() {
        return prevPressTime;
    }
    public void setDURATION_TIME(long DURATION_TIME) {
        this.DURATION_TIME = DURATION_TIME;
    }
    public void setPrevPressTime(long prevPressTime) {
        this.prevPressTime = prevPressTime;
    }
}
