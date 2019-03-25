package com.hyunwoong.braintraining.model;

public class MixGameModel {

    private String RightAnswer; // 정답 색깔코드
    private String WrongAnswer1; // 오답1 색깔코드
    private String WrongAnswer2; // 오답2 색깔코드

    private int RightAnswerNum; //맞은 문제수
    private int[] arr; // 랜덤 배열
    private int Qnumber; // 문제 번호

    public void init() {
        RightAnswerNum = 0;
        Qnumber = 0;
        RightAnswer = "";
        WrongAnswer1 = "";
        WrongAnswer2 = "";
    }

    public String getRightAnswer() {
        return RightAnswer;
    }

    public String getWrongAnswer1() {
        return WrongAnswer1;
    }

    public String getWrongAnswer2() {
        return WrongAnswer2;
    }

    public int getRightAnswerNum() {
        return RightAnswerNum;
    }

    public int[] getArr() {
        return arr;
    }

    public int getQnumber() {
        return Qnumber;
    }

    public void setRightAnswer(String rightAnswer) {
        RightAnswer = rightAnswer;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        WrongAnswer1 = wrongAnswer1;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        WrongAnswer2 = wrongAnswer2;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public void setRightAnswerNum(int rightAnswerNum) {
        RightAnswerNum = rightAnswerNum;
    }

    public void setQnumber(int qnumber) {
        Qnumber = qnumber;
    }
}
