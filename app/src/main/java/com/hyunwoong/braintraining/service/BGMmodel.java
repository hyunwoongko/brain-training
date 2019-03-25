package com.hyunwoong.braintraining.service;

public class BGMmodel {

    private static volatile BGMmodel mInstance = null;
    private String BgmName;

    private BGMmodel() {
    }

    public static BGMmodel getInstance() {
        if (mInstance == null) {
            synchronized (BGMmodel.class) {
                if (mInstance == null) {
                    mInstance = new BGMmodel();
                }
            }
        }
        return mInstance;
    } // Singleton


    public String getBgmName() {
        return BgmName;
    }

    public void setBgmName(String bgmName) {
        BgmName = bgmName;
    }
}
