package com.hyunwoong.braintraining.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.hyunwoong.braintraining.contract.SettingContract;
import com.hyunwoong.braintraining.database.UserFriendDB;
import com.hyunwoong.braintraining.receiver.AlarmReceiver;
import com.hyunwoong.braintraining.utils.Action;
import com.hyunwoong.braintraining.utils.DialogBuilder;
import com.hyunwoong.braintraining.utils.FirebaseHelper;
import com.hyunwoong.braintraining.utils.PreferenceHelper;
import com.hyunwoong.braintraining.view.LoginView;
import com.hyunwoong.braintraining.view.SettingView;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.kakao.util.helper.log.Logger;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class SettingPresenter extends BasePresenter implements SettingContract.presenter {

    private SettingView view;

    @Override
    public void SetView(SettingView view) {
        this.view = view;
        super.SetView(view);
    }

    @Override
    public void ClearView() {
        super.ClearView();
    }

    @Override
    public void SetModel() {
        super.SetModel();
    }

    @Override
    public void ClearModel() {
        super.ClearModel();
    }

    @Override
    public void RemoveFavorite() {

        DialogBuilder.getInstance().MakeDialog("즐겨찾기 초기화", "즐겨찾기를 초기화 하시겠습니까?", view, new Action() {
            @Override
            public void UserAction() {
                PreferenceHelper.getInstance(view).setPreferenceRemove("favorite");
                Toast("즐겨찾기를 초기화 했습니다.");
                view.recreate();
            }
        });

    }

    @Override
    public void RemoveFreind() {
        DialogBuilder.getInstance().MakeDialog("친구 초기화", "친구 목록을 초기화 하시겠습니까?", view, new Action() {
            @Override
            public void UserAction() {
                ArrayList<String> items = UserFriendDB.getInstance().getFriendList();
                if (items == null) {
                    items = new ArrayList<>();
                }
                items.clear();
                UserFriendDB.getInstance().setFriendList(items);
                FirebaseHelper.getInstance().setValue("UserFriendDB", UserFriendDB.getInstance().getFriendList());
                Toast("친구 목록을 초기화 했습니다.");
                view.recreate();
            }
        });
    }

    @Override
    public void SecessionClose() {

        DialogBuilder.getInstance().MakeDialog("회원 탈퇴", "회원 탈퇴 하시겠습니까?", view, new Action() {
            @Override
            public void UserAction() {
                UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Logger.e(errorResult.toString());
                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        Exit();
                        MoveTo(LoginView.class);
                    }

                    @Override
                    public void onNotSignedUp() {
                        Exit();
                        MoveTo(LoginView.class);
                    }

                    @Override
                    public void onSuccess(Long userId) {
                        Exit();
                        MoveTo(LoginView.class);
                    }
                });
            }
        });

    }

    @Override
    public void AlarmDailyTraining() {
        if (PreferenceHelper.getInstance(view).getString("PUSH", "").equals("ON")) {
            Intent intent = new Intent(view, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(view,
                    100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.HOUR_OF_DAY, 13);
            calendar1.set(Calendar.MINUTE, 00);
            calendar1.set(Calendar.SECOND, 00);

            AlarmManager alarmManager = (AlarmManager) view.getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    @Override
    public void SetBgm(String s) {
        PreferenceHelper.getInstance(view).setString("BGM", s);
    }

    @Override
    public void SetPush(String s) {
        PreferenceHelper.getInstance(view).setString("PUSH", s);
    }
}
