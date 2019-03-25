package com.hyunwoong.braintraining.model;

public class CountGameModel {

    private int size; // 생성할 버튼 갯수
    private int numberOfButton; // 현재 버튼 갯수
    private int buttonToPress; // 눌러야할 버튼 번호
    private int remainingButton;

    public void init() {
        size = 35;
        numberOfButton = 0;
        buttonToPress = 1;

    }

    public int getSize() {
        return size;
    }

    public int getNumberOfButton() {
        return numberOfButton;
    }

    public int getButtonToPress() {
        return buttonToPress;
    }

    public int getRemainingButton() {
        return remainingButton;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setNumberOfButton(int numberOfButton) {
        this.numberOfButton = numberOfButton;
    }

    public void setButtonToPress(int buttonToPress) {
        this.buttonToPress = buttonToPress;
    }

    public void setRemainingButton(int remainingButton) {
        this.remainingButton = remainingButton;
    }
}
