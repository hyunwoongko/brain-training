package com.hyunwoong.braintraining.model;

public class LoginModel {

    private long DURATION_TIME;
    private long prevPressTime;
    private String[] PERMISSION;


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

    public long getDURATION_TIME() {
        return DURATION_TIME;
    }

    public String[] getPERMISSION() {
        return PERMISSION;
    }

    public void setPERMISSION(String[] PERMISSION) {
        this.PERMISSION = PERMISSION;
    }
}
