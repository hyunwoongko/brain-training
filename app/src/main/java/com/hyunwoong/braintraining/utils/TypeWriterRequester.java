package com.hyunwoong.braintraining.utils;

public class TypeWriterRequester{
    // TypeWriter를 활용해 실제적으로 다이얼로그를 표출하기 위한 메소드인 Dialog를 정의.
    // TypeWriter의 메소드를 활용해 새로운 메소드 정의 -> 위임 (delegation)

    private TypeWriter typeWriter;

    public TypeWriterRequester(TypeWriter typeWriter) {
        this.typeWriter = typeWriter;
    } // 생성자 설계

    public void Write(String s , int speed) {

        typeWriter.setText(""); // 글자를 지워줌
        typeWriter.setCharacterDelay(speed); // 딜레이값 설정
        typeWriter.animateText(s); // 글씨를 한글자씩 적어주는 메소드
    }
}