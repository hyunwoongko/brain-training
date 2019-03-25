package com.hyunwoong.braintraining.presenter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.utils.PreferenceHelper;
import com.hyunwoong.braintraining.view.SelectView;
import com.hyunwoong.braintraining.contract.SelectContract;
import com.hyunwoong.braintraining.model.SelectModel;
import com.hyunwoong.braintraining.database.UserCurrentDB;
import com.hyunwoong.braintraining.utils.TypeWriter;
import com.hyunwoong.braintraining.utils.TypeWriterRequester;
import com.hyunwoong.braintraining.utils.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class SelectPresenter extends BasePresenter implements SelectContract.presenter {

    private TextView nickname;
    private TextView userId;
    private ImageView profileImage;
    private TypeWriter typeWriter;
    private FrameLayout dialogFramelayout;
    private FrameLayout buttonFramelayout;
    private SelectView view;
    // View

    private TypeWriterRequester typeWriterRequester;
    private UserInfo userInfo;
    // Utils

    private SelectModel model;
    // Model

    @Override
    public void SetView(TextView nickname,
                        TextView userId,
                        ImageView profileImage,
                        TypeWriter typeWriter,
                        FrameLayout dialogFramelayout,
                        FrameLayout buttonFramelayout,
                        SelectView view) {

        this.nickname = nickname;
        this.userId = userId;
        this.profileImage = profileImage;
        this.typeWriter = typeWriter;
        this.dialogFramelayout = dialogFramelayout;
        this.buttonFramelayout = buttonFramelayout;
        this.view = view;
        super.SetView(view);
    }

    @Override
    public void ClearView() {
        this.nickname = null;
        this.userId = null;
        this.profileImage = null;
        this.typeWriter = null;
        this.dialogFramelayout = null;
        this.buttonFramelayout = null;
        this.view = null;
        super.ClearView();
    }

    @Override
    public void SetModel() {
        model = new SelectModel();
    }

    @Override
    public void InitModel() {

    }

    @Override
    public void Dialog() {

        if (model == null) {
            SetModel(); // 데이터 요청
            model.init();
        }

        typeWriterRequester = new TypeWriterRequester(typeWriter); // 유틸 요청

        switch (model.getDialogNum()) { // 데이터 사용

            case 1:
                typeWriterRequester.Write(UserCurrentDB.getInstance().getNickname() // 유틸사용
                        + "님을 위한 8개의 게임이 준비 되어 있습니다.", 10);
                model.setDialogNum(model.getDialogNum() + 1);// 데이터 사용
                break;

            case 2:
                typeWriterRequester.Write("플레이 하실 게임을 선택해주세요!", 10); // 유틸사용
                model.setDialogNum(model.getDialogNum() + 1);// 데이터 사용
                break;

            case 3:
                dialogFramelayout.setVisibility(View.GONE);
                buttonFramelayout.setVisibility(View.VISIBLE);
                ClearModel();
                typeWriterRequester = null;
                break;
        }
    }

    @Override
    public void DrawUserInfo() {
        userInfo = new UserInfo(nickname, userId, profileImage, view);
        userInfo.drawUserInfo();
    }

    @Override
    public void InfoDestory() {
        userInfo = null;
    }

    @Override
    public void AddToFavorite(String gameName) {
        List<String> items = PreferenceHelper.getInstance(view).getItem("favorite");

        if (items == null) {
            items = new ArrayList<>();
        }

        if (!items.contains(gameName)) {
            items.add(gameName);
            PreferenceHelper.getInstance(view).setItem("favorite", items);
            Toast("즐겨찾기에 추가되었습니다.");
        } // 중복검사
        else{
            Toast("이미 존재하는 게임입니다.");
        }
    }
}
