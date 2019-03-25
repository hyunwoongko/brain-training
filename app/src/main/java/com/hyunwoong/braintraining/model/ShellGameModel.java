package com.hyunwoong.braintraining.model;

import java.util.ArrayList;
import java.util.List;

public class ShellGameModel {
    private List<String> list;
    private int Qnumber;
    private int RightAnswerNumber;

    public void init() {
        list = new ArrayList<>();
        Qnumber = 0;
    }

    public List<String> getList() {
        return list;
    }

    public int getQnumber() {
        return Qnumber;
    }

    public int getRightAnswerNumber() {
        return RightAnswerNumber;
    }


    public void setList(List<String> list) {
        this.list = list;
    }

    public void setQnumber(int Qnumber) {
        this.Qnumber = Qnumber;
    }

    public void setRightAnswerNumber(int rightAnswerNumber) {
        RightAnswerNumber = rightAnswerNumber;
    }

    public void setCorrectionMode(boolean isCorrectionMode) {
        isCorrectionMode = isCorrectionMode;
    }
}
