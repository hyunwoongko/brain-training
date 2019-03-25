package com.hyunwoong.braintraining.model;

public class CalculateGameModel {

    private int FirstNum; // 첫번째 숫자
    private int SecondNum; // 두번째 숫자
    private int Operator; // 연산자
    private int RightAnswer; // 정답
    private int correct; // 맞춘 문제 수

    public void init() {
        FirstNum = 0;
        SecondNum = 0;
        RightAnswer = 0;
        correct = 0;
    }

    public int getFirstNum() {
        return FirstNum;
    }

    public int getSecondNum() {
        return SecondNum;
    }

    public int getOperator() {
        return Operator;
    }

    public int getRightAnswer() {
        return RightAnswer;
    }

    public int getCorrect() {
        return correct;
    }

    public void setFirstNum(int firstNum) {
        FirstNum = firstNum;
    }

    public void setSecondNum(int secondNum) {
        SecondNum = secondNum;
    }

    public void setOperator(int operator) {
        Operator = operator;
    }

    public void setRightAnswer(int rightAnswer) {
        RightAnswer = rightAnswer;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
