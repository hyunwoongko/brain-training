package com.hyunwoong.braintraining.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.database.UserCurrentDB;

public class UserInfo {
    // 유저 정보가 표시되는 Main, GameSelect, GameScore등의 액티비티에서 중복되어 사용되는 코드들을 묶음.
    // 위 액티비티들에서 메소드 한줄로 유저 정보를 표시 가능.

    private TextView nickname, userId;
    private ImageView profileImage;
    private Context context;
    private UriParser uri_parser;


    public UserInfo(TextView nickname, TextView userId, ImageView profileImage, Context context) {
        this.nickname = nickname;
        this.userId = userId;
        this.profileImage = profileImage;
        this.context = context;
        uri_parser = new UriParser(profileImage, this.context);
    } // 생성자 설계

    public void drawUserInfo() {
        nickname.setText(UserCurrentDB.getInstance().getNickname());
        userId.setText(UserCurrentDB.getInstance().getUserId()+"     ");
        uri_parser.ParseURI();
    }
}

