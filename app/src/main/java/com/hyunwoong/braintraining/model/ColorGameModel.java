package com.hyunwoong.braintraining.model;

import android.content.Context;

import com.hyunwoong.braintraining.R;

public class ColorGameModel {

    private Context context;
    private String[] colorText;
    private int[] colorNum;
    private int rightAnswer;

    private int red;
    private int black;
    private int blue;
    private int yellow;
    private int green;
    private int orange;
    private int purple;
    private int pink;
    private int skyblue;
    //color

    public ColorGameModel(Context context) {
        this.context = context;
    }

    public void init() {
        red = context.getResources().getColor(R.color.red);
        black = context.getResources().getColor(R.color.black);
        blue = context.getResources().getColor(R.color.blue);
        yellow = context.getResources().getColor(R.color.yellow);
        green = context.getResources().getColor(R.color.green);
        orange = context.getResources().getColor(R.color.orange);
        purple = context.getResources().getColor(R.color.purple);
        pink = context.getResources().getColor(R.color.pink);
        skyblue = context.getResources().getColor(R.color.skyblue);

        colorText = new String[]{"빨강", "검정", "파랑", "노랑", "초록", "주황", "보라" , "분홍" , "하늘"};
        colorNum = new int[]{red, black, blue, yellow, green, orange, purple , pink , skyblue};
        rightAnswer = 0;
    }

    public int[] getColorNum() {
        return colorNum;
    }

    public String[] getColorText() {
        return colorText;
    }

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setColorNum(int[] colorNum) {
        this.colorNum = colorNum;
    }

    public void setColorText(String[] colorText) {
        this.colorText = colorText;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}

