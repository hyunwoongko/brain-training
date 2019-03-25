package com.hyunwoong.braintraining.model;

import android.widget.TextView;

import java.util.ArrayList;

public class MomentGameModel {

    private int size;
    private int numberOfNumber;
    private int toPress = 1;
    private int Qnumber;
    private int RightAnswerNum;
    private boolean ButtonStatus;

    private ArrayList<TextView> textList;

    public void init() {
        Qnumber = 0;
        RightAnswerNum = 0;
        ButtonStatus = false;
        textList = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public int getNumberOfNumber() {
        return numberOfNumber;
    }

    public int getToPress() {
        return toPress;
    }

    public int getQnumber() {
        return Qnumber;
    }

    public ArrayList<TextView> getTextList() {
        return textList;
    }

    public int getRightAnswerNum() {
        return RightAnswerNum;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setNumberOfNumber(int numberOfNumber) {
        this.numberOfNumber = numberOfNumber;
    }

    public void setToPress(int toPress) {
        this.toPress = toPress;
    }

    public void setQnumber(int qnumber) {
        Qnumber = qnumber;
    }

    public void setTextList(ArrayList<TextView> textList) {
        this.textList = textList;
    }

    public void setRightAnswerNum(int rightAnswerNum) {
        RightAnswerNum = rightAnswerNum;
    }
}
