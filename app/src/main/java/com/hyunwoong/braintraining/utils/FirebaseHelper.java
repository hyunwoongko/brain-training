package com.hyunwoong.braintraining.utils;


import com.hyunwoong.braintraining.database.BestScoreDB;
import com.hyunwoong.braintraining.database.OtherBestScoreDB;
import com.hyunwoong.braintraining.database.UserCurrentDB;
import com.hyunwoong.braintraining.database.UserFriendDB;
import com.hyunwoong.braintraining.database.UserOtherDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {
    // Firebase는 비동기식 (Asynchronous) 데이터 베이스.
    // onDataChange가 가장 나중에 실행되서 그냥 접근하면 Null값밖에 못불러옴.
    // 콜백 인터페이스 활용해서 getValue 메소드 구현해야 값을 제대로 불러옴.
    // 자주 쓰기 편하게 데이터 입력 , 출력메소드를 Helper로 구성.

    private static FirebaseHelper mInstance = null;
    ArrayList<String> uList = new ArrayList<>();

    private FirebaseHelper() {
        return;
    }
    /////////////////////객체 생성 방지/////////////////

    public static synchronized FirebaseHelper getInstance() {
        if (mInstance == null) {
        }
        try {
            if (mInstance == null)
                mInstance = new FirebaseHelper();
            return mInstance;
        } finally {
        }
    } // Singleton


    public void setValue(String DBname, Object object) {
        FirebaseDatabase.getInstance().getReference("AllUserDB").child(UserCurrentDB.getInstance().getUserId())
                .child(DBname).setValue(object);
    } // SetValue

    public ArrayList<String> getuList() {
        return uList;
    }

    public void setuList(ArrayList<String> uList) {
        this.uList = uList;
    }


    public void getUserCurrentValue(final Callback.UserCurrentCallback please_make_new_callback) {

        FirebaseDatabase.getInstance().getReference().child("AllUserDB")
                .child(UserCurrentDB.getInstance().getUserId())
                .child("UserCurrentDB")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        please_make_new_callback.onUserCurrentCallback(dataSnapshot.getValue(UserCurrentDB.class));
                    }// 콜백으로 보냄.

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }// 커런트 유저

    public void getGameScoreValue(final Callback.BestScoreCallback please_make_new_callback) {

        FirebaseDatabase.getInstance().getReference().child("AllUserDB").child(UserCurrentDB.getInstance().getUserId()).child("BestScoreDB")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        please_make_new_callback.onBestScoreCallback(dataSnapshot.getValue(BestScoreDB.class));
                    } // 콜백으로 보냄.

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    } // 게임 스코어

    public void getUserFriendValue(final Callback.UserFriendCallback please_make_new_callback) {

        FirebaseDatabase.getInstance().getReference().child("AllUserDB").child(UserCurrentDB.getInstance().getUserId()).child("UserFriendDB")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (uList != null) {
                            uList.clear();
                        }//uList를 Clear
                        if (UserFriendDB.getInstance().getFriendList() != null) {
                            UserFriendDB.getInstance().ClearFriendList();
                        }
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            please_make_new_callback.onUserFriendCallback(snapshot.getValue());
                        } // 포이치문. 칠드런들의 스냅샷 = snapshot
                        //snapshot의 key를 콜백으로 넘김.
                        //포문 한번 돌때마다 콜백 한번씩 실행.
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });// 전체 유저목록.
    }// @DataConnection
    //    Helper메소드 -> 콜백메소드 -> 모델의 String에 저장
    // -> Helper내부의 리스트에 모델의 String을 차례로 담음
    // -> Helper내부의 리스트를 모델의 리스트에 저장.

    // Requester
    public void getUserOtherValue(String UID,  boolean onlynumber ,final Callback.UserOtherCallback please_make_new_callback) {

        if(onlynumber) {
            FirebaseDatabase.getInstance().getReference().child("AllUserDB").child("kakao:" + UID).child("UserCurrentDB")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            please_make_new_callback.onUserOtherCallback(dataSnapshot.getValue(UserOtherDB.class));
                        }// 콜백으로 보냄.

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
        }
        else{
            FirebaseDatabase.getInstance().getReference().child("AllUserDB").child(UID).child("UserCurrentDB")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            please_make_new_callback.onUserOtherCallback(dataSnapshot.getValue(UserOtherDB.class));
                        }// 콜백으로 보냄.

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
        }
    }// 검색된 유저의 정보를 불러옴.

    public void getUserOtherScoreValue(String UID,  boolean onlynumber ,final Callback.UserOtherScoreCallback please_make_new_callback) {

        if(onlynumber) {
            FirebaseDatabase.getInstance().getReference().child("AllUserDB").child("kakao:" + UID).child("BestScoreDB")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            please_make_new_callback.onUserOtherScoreCallback(dataSnapshot.getValue(OtherBestScoreDB.class));
                        }// 콜백으로 보냄.

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
        }
        else{
            FirebaseDatabase.getInstance().getReference().child("AllUserDB").child(UID).child("BestScoreDB")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            please_make_new_callback.onUserOtherScoreCallback(dataSnapshot.getValue(OtherBestScoreDB.class));
                        }// 콜백으로 보냄.

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
        }
    }// 검색된 유저의 정보를 불러옴.
}