package com.hyunwoong.braintraining.presenter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.utils.PreferenceHelper;
import com.hyunwoong.braintraining.view.MainView;
import com.hyunwoong.braintraining.contract.MainContract;
import com.hyunwoong.braintraining.utils.UserInfo;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class MainPresenter extends BasePresenter implements MainContract.presenter {

    private TextView nickname;
    private TextView userId;
    private ImageView profileImage;
    private MainView view;
    // View

    private UserInfo userInfo;
    // Utils

    @Override
    public void SetView(TextView nickname,
                        TextView userId,
                        ImageView profileImage,
                        MainView view) {

        this.nickname = nickname;
        this.userId = userId;
        this.profileImage = profileImage;
        this.view = view;
        super.SetView(view);
    }
    @Override
    public void CleanView() {
        this.nickname = null;
        this.userId = null;
        this.profileImage = null;
        this.view = null;
        super.ClearView();
    }

    @Override
    public void DrawUserInfo() {
        userInfo = new UserInfo(nickname, userId, profileImage, view);
        userInfo.drawUserInfo();
    }


    @Override
    public boolean CheckFirstUser() {
        if (PreferenceHelper.getInstance(view.getApplicationContext()).getBoolean("FirstUser", true))
            return true;
        else return false;
    }
    @Override
    public void Logout(){
        new AlertDialog.Builder(view)
                .setTitle("로그아웃")
                .setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                            @Override
                            public void onCompleteLogout() {
                                Exit();

                            }
                        });
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setCancelable(true)
                .show();
    }

    @Override
    public void InfoDestory() {
        userInfo = null;
    }

    @Override
    public void InitModel() {

    }
}
