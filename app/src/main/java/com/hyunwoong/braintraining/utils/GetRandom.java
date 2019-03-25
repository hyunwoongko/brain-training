package com.hyunwoong.braintraining.utils;

import java.util.Random;

public class GetRandom {

    // 게임에서 사용할 난수 생성 클래스.
    // 중복을 허용하는 난수생성메소드와 중복을 허용하지 않는 난수생성메소드 정의.

    private Random random = new Random();

    public int getRandom_Overlap(final int size) {

        return (int) (Math.random() * size);
    }

    public int[] getRandom_notOverlap(final int size) {
        //중복을 허용하지 않는 난수 생성 메소드
        int i;
        int[] arr = new int[size];

        for (i = 0; i < size; i++) {
            arr[i] = (random.nextInt(size) + 1); // 난수 생성 (1부터 Size만큼 생성)
            for (int j = 0; j < i; j++) {
                if (arr[i] == arr[j]) i--; // 중복 검사 알고리즘
            }
        }
        i = 0; // i 초기화
        return arr;
    }
}
