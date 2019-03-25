package com.hyunwoong.braintraining.model;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordGameModel {

    private Map<Integer, String> Words;
    private int size;
    private int[] WordNumArr;
    private int numOfWords;
    private int RightAnswerNumber;
    private int SubmitNum;

    private ArrayList<String> WordList;
    private ArrayList<TextView> tvList;


    public void init() {

        Words = new HashMap<>();

        size = 200;
        SubmitNum = 20;
        RightAnswerNumber = 0;
        WordList = new ArrayList<>();
        tvList = new ArrayList<>();

        Words.put(1, "하늘");
        Words.put(2, "바다");
        Words.put(3, "음악");
        Words.put(4, "구름");
        Words.put(5, "사랑");
        Words.put(6, "우리");
        Words.put(7, "친구");
        Words.put(8, "엄마");
        Words.put(9, "아빠");
        Words.put(10, "부모");
        Words.put(11, "여름");
        Words.put(12, "겨울");
        Words.put(13, "노래");
        Words.put(14, "가요");
        Words.put(15, "축제");
        Words.put(16, "전공");
        Words.put(17, "화면");
        Words.put(18, "후드");
        Words.put(19, "라면");
        Words.put(20, "음료");
        Words.put(21, "과자");
        Words.put(22, "게임");
        Words.put(23, "글씨");
        Words.put(24, "기타");
        Words.put(25, "고장");
        Words.put(26, "쫄면");
        Words.put(27, "국수");
        Words.put(28, "간장");
        Words.put(29, "소금");
        Words.put(30, "반팔");
        Words.put(31, "신발");
        Words.put(32, "거울");
        Words.put(33, "설탕");
        Words.put(34, "우유");
        Words.put(35, "감자");
        Words.put(36, "기상");
        Words.put(37, "우박");
        Words.put(38, "소풍");
        Words.put(39, "공개");
        Words.put(40, "벌칙");
        Words.put(41, "수박");
        Words.put(42, "참외");
        Words.put(43, "메론");
        Words.put(44, "긴장");
        Words.put(45, "순간");
        Words.put(46, "딸기");
        Words.put(47, "바람");
        Words.put(48, "빌딩");
        Words.put(49, "패션");
        Words.put(50, "고민");
        Words.put(51, "군장");
        Words.put(52, "군대");
        Words.put(53, "남자");
        Words.put(54, "여자");
        Words.put(55, "사람");
        Words.put(56, "시간");
        Words.put(57, "제출");
        Words.put(58, "촉박");
        Words.put(59, "보고");
        Words.put(60, "발표");
        Words.put(61, "견공");
        Words.put(62, "하마");
        Words.put(63, "사자");
        Words.put(64, "기차");
        Words.put(65, "승마");
        Words.put(66, "개관");
        Words.put(67, "배경");
        Words.put(68, "집중");
        Words.put(69, "초월");
        Words.put(70, "시민");
        Words.put(71, "복고");
        Words.put(72, "민주");
        Words.put(73, "주민");
        Words.put(74, "세월");
        Words.put(75, "이민");
        Words.put(76, "정보");
        Words.put(77, "개선");
        Words.put(78, "수반");
        Words.put(79, "바지");
        Words.put(80, "치장");
        Words.put(81, "서울");
        Words.put(82, "대전");
        Words.put(83, "대구");
        Words.put(84, "광주");
        Words.put(85, "인천");
        Words.put(86, "부산");
        Words.put(87, "전주");
        Words.put(88, "경북");
        Words.put(89, "전북");
        Words.put(90, "충남");
        Words.put(91, "충북");
        Words.put(92, "강원");
        Words.put(93, "제주");
        Words.put(94, "백제");
        Words.put(95, "신라");
        Words.put(96, "가야");
        Words.put(97, "고려");
        Words.put(98, "조선");
        Words.put(99, "수도");
        Words.put(100, "도시");
        Words.put(101, "거리");
        Words.put(102, "글꼴");
        Words.put(103, "참조");
        Words.put(104, "편지");
        Words.put(105, "망고");
        Words.put(106, "사과");
        Words.put(107, "학교");
        Words.put(108, "휴지");
        Words.put(109, "물통");
        Words.put(110, "초장");
        Words.put(111, "어묵");
        Words.put(112, "쌈장");
        Words.put(113, "파채");
        Words.put(114, "무쌈");
        Words.put(115, "훈제");
        Words.put(116, "갈비");
        Words.put(117, "얼음");
        Words.put(118, "생채");
        Words.put(119, "상추");
        Words.put(120, "휴지");
        Words.put(121, "축구");
        Words.put(122, "마늘");
        Words.put(123, "연어");
        Words.put(124, "우럭");
        Words.put(125, "메기");
        Words.put(126, "자라");
        Words.put(127, "거북");
        Words.put(128, "용왕");
        Words.put(129, "토끼");
        Words.put(130, "주부");
        Words.put(131, "삼촌");
        Words.put(132, "고모");
        Words.put(133, "양말");
        Words.put(134, "전통");
        Words.put(135, "동화");
        Words.put(136, "기억");
        Words.put(137, "가방");
        Words.put(138, "기침");
        Words.put(139, "다과");
        Words.put(140, "림보");
        Words.put(141, "기성");
        Words.put(142, "대장");
        Words.put(143, "연인");
        Words.put(144, "저녁");
        Words.put(145, "모임");
        Words.put(146, "수비");
        Words.put(147, "선수");
        Words.put(148, "식빵");
        Words.put(149, "떡국");
        Words.put(150, "치즈");
        Words.put(151, "메밀");
        Words.put(152, "식사");
        Words.put(153, "별미");
        Words.put(154, "팥죽");
        Words.put(155, "이사");
        Words.put(156, "이용");
        Words.put(157, "편지");
        Words.put(158, "은행");
        Words.put(159, "정리");
        Words.put(160, "마음");
        Words.put(161, "홍수");
        Words.put(162, "키위");
        Words.put(163, "수육");
        Words.put(164, "냉면");
        Words.put(165, "미국");
        Words.put(166, "유럽");
        Words.put(167, "진심");
        Words.put(168, "정신");
        Words.put(169, "국밥");
        Words.put(170, "젖소");
        Words.put(171, "율무");
        Words.put(172, "울음");
        Words.put(173, "공교");
        Words.put(174, "연결");
        Words.put(175, "오류");
        Words.put(176, "옥상");
        Words.put(177, "다리");
        Words.put(178, "유교");
        Words.put(179, "잔디");
        Words.put(180, "전설");
        Words.put(181, "연애");
        Words.put(182, "육교");
        Words.put(183, "수갑");
        Words.put(184, "경찰");
        Words.put(185, "기본");
        Words.put(186, "숫자");
        Words.put(187, "득점");
        Words.put(188, "투입");
        Words.put(189, "체력");
        Words.put(190, "김치");
        Words.put(191, "가지");
        Words.put(192, "매화");
        Words.put(193, "국고");
        Words.put(194, "광고");
        Words.put(195, "장면");
        Words.put(196, "손해");
        Words.put(197, "절망");
        Words.put(198, "결말");
        Words.put(199, "장미");
        Words.put(200, "국화");
    }

    public Map<Integer, String> getWords() {
        return Words;
    }

    public int getSize() {
        return size;
    }

    public int[] getWordNumArr() {
        return WordNumArr;
    }

    public ArrayList<String> getWordList() {
        return WordList;
    }

    public int getNumOfWords() {
        return numOfWords;
    }

    public int getRightAnswerNumber() {
        return RightAnswerNumber;
    }

    public ArrayList<TextView> getTvList() {
        return tvList;
    }

    public void setWords(Map<Integer, String> words) {
        Words = words;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setWordNumArr(int[] wordNumArr) {
        this.WordNumArr = wordNumArr;
    }

    public void setWordList(ArrayList<String> wordList) {
        WordList = wordList;
    }

    public void setNumOfWords(int numOfWords) {
        this.numOfWords = numOfWords;
    }

    public void setRightAnswerNumber(int rightAnswerNumber) {
        RightAnswerNumber = rightAnswerNumber;
    }

    public int getSubmitNum() {
        return SubmitNum;
    }

    public void setSubmitNum(int submitNum) {
        SubmitNum = submitNum;
    }

    public void setTvList(ArrayList<TextView> tvList) {
        this.tvList = tvList;
    }
}
