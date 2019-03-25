package com.hyunwoong.braintraining.model;

public class SelectModel {

    private int dialogNum;
    private String gameName;

    public void init() {
        dialogNum = 1;
    }

    public int getDialogNum() {
        return dialogNum;
    }

    public String getGameName() {
        return gameName;
    }

    public void setDialogNum(int dialogNum) {
        this.dialogNum = dialogNum;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
