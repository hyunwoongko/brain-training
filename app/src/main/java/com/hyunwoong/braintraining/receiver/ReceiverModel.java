package com.hyunwoong.braintraining.receiver;

public class ReceiverModel {

    private static volatile ReceiverModel mInstance = null;
    private String RECEIVE_MODE;
    private String CHANNEL_ID = "com.example.user";
    private String CHANNEL_NAME = "BrainTraining Channel";

    private ReceiverModel() {
    }

    public static ReceiverModel getInstance() {
        if (mInstance == null) {
            synchronized (ReceiverModel.class) {
                if (mInstance == null) {
                    mInstance = new ReceiverModel();
                }
            }
        }
        return mInstance;
    } // Singleton


    public String getRECEIVE_MODE() {
        return RECEIVE_MODE;
    }

    public void setRECEIVE_MODE(String RECEIVE_MODE) {
        this.RECEIVE_MODE = RECEIVE_MODE;
    }

    public String getCHANNEL_ID() {
        return CHANNEL_ID;
    }

    public String getCHANNEL_NAME() {
        return CHANNEL_NAME;
    }

    public void setCHANNEL_ID(String CHANNEL_ID) {
        this.CHANNEL_ID = CHANNEL_ID;
    }

    public void setCHANNEL_NAME(String CHANNEL_NAME) {
        this.CHANNEL_NAME = CHANNEL_NAME;
    }
}
