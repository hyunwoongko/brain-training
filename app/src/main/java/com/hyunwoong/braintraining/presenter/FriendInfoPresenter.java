package com.hyunwoong.braintraining.presenter;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.FriendInfoContract;
import com.hyunwoong.braintraining.database.OtherBestScoreDB;
import com.hyunwoong.braintraining.database.UserCurrentDB;
import com.hyunwoong.braintraining.database.UserOtherDB;
import com.hyunwoong.braintraining.model.FriendInfoModel;
import com.hyunwoong.braintraining.utils.Callback;
import com.hyunwoong.braintraining.utils.FcmHelper;
import com.hyunwoong.braintraining.utils.FirebaseHelper;
import com.hyunwoong.braintraining.view.FriendInfoView;

public class FriendInfoPresenter extends BasePresenter implements FriendInfoContract.presenter {

    private ImageView FriendProfileImage;
    private TextView FriendNickName;
    private TextView FriendCode;
    private TextView GameData;
    private EditText editText;
    private FriendInfoView view;
    private FriendInfoModel model;

    @Override
    public void SetView(ImageView FriendProfileImage,
                        TextView FriendNickName,
                        TextView FriendCode,
                        TextView GameData,
                        EditText editText,
                        FriendInfoView view) {

        this.FriendProfileImage = FriendProfileImage;
        this.FriendNickName = FriendNickName;
        this.FriendCode = FriendCode;
        this.GameData = GameData;
        this.editText = editText;
        this.view = view;
        super.SetView(view);
    }

    @Override
    public void ClearView() {
        super.ClearView();
    }

    @Override
    public void SetModel() {
        model = new FriendInfoModel();
        super.SetModel();
    }

    @Override
    public void ClearModel() {
        super.ClearModel();
    }

    @Override
    public void ShowFriendInfo() {
        SetModel();
        Intent intent = view.getIntent();
        model.setUID(intent.getStringExtra("UID"));

        FirebaseHelper.getInstance().getUserOtherValue(model.getUID(), false, new Callback.UserOtherCallback() {
            @Override
            public void onUserOtherCallback(UserOtherDB userOtherDB) {
                UserOtherDB.setInstance(userOtherDB);
                model.setNickname(UserOtherDB.getInstance().getNickname());
                model.setProfileURL(UserOtherDB.getInstance().getPhotoUrl());
                FriendCode.setText(model.getUID());
                FriendNickName.setText(model.getNickname());
                if (model.getProfileURL() != null) {
                    Glide.with(view)
                            .load(Uri.parse(model.getProfileURL()))
                            .into(FriendProfileImage);
                } else {
                    Log.d("URI ERROR", "URI ERROR");
                }
            }
        });

        FirebaseHelper.getInstance().getUserOtherScoreValue(model.getUID(), false, new Callback.UserOtherScoreCallback() {
            @Override
            public void onUserOtherScoreCallback(OtherBestScoreDB otherBestScoreDB) {
                OtherBestScoreDB.setInstance(otherBestScoreDB);
                model.setCal(getGameData(OtherBestScoreDB.getInstance().getCalculateGameBest(), model.getCal()));
                model.setCou(getGameData(OtherBestScoreDB.getInstance().getCountGameBest(), model.getCou()));
                model.setMom(getGameData(OtherBestScoreDB.getInstance().getMomentGameBest(), model.getMom()));
                model.setCol(getGameData(OtherBestScoreDB.getInstance().getColorGameBest(), model.getCol()));
                model.setMix(getGameData(OtherBestScoreDB.getInstance().getMixGameBest(), model.getMix()));
                model.setShe(getGameData(OtherBestScoreDB.getInstance().getShellGameBest(), model.getShe()));
                model.setWor(getGameData(OtherBestScoreDB.getInstance().getWordGameBest(), model.getWor()));
                model.setLab(getGameData(OtherBestScoreDB.getInstance().getLabialGameBest(), model.getLab()));
                setGameDataText();
            }
        });

    }

    @Override
    public void setGameDataText() {
        GameData.setText(
                getString(R.string.CalculateGame) + " : " + model.getCal() + "\n" + "\n" +
                        getString(R.string.CountGame) + " : " + model.getCou() + "\n" + "\n" +
                        getString(R.string.MomentGame) + " : " + model.getMom() + "\n" + "\n" +
                        getString(R.string.ColorGame) + " : " + model.getCol() + "\n" + "\n" +
                        getString(R.string.MixGame) + " : " + model.getMix() + "\n" + "\n" +
                        getString(R.string.ShellGame) + " : " + model.getShe() + "\n" + "\n" +
                        getString(R.string.WordGame) + " : " + model.getWor() + "\n" + "\n" +
                        getString(R.string.LabialGame) + " : " + model.getLab() + "\n" + "\n");
    }

    @Override
    public String getGameData(Integer Data, String DataName) {
        if (Data != null) {
            DataName = Data.toString();

        } else {
            DataName = "플레이 기록 없음";
        }

        return DataName;
    }

    @Override
    public void setMessage() {
        SetModel();
        Intent intent = view.getIntent();
        model.setUID(intent.getStringExtra("UID"));

        FirebaseHelper.getInstance().getUserOtherValue(model.getUID(), false, new Callback.UserOtherCallback() {
            @Override
            public void onUserOtherCallback(UserOtherDB userOtherDB) {
                UserOtherDB.setInstance(userOtherDB);
                FcmHelper.getInstance().SendMessage(UserCurrentDB.getInstance().getNickname(), /* 타이틀 */
                        /*텍스트*/ UserCurrentDB.getInstance().getNickname() + " : " + editText.getText().toString());
                Toast(UserOtherDB.getInstance().getNickname() + "님에게 메시지를 보냈습니다.");
            }
        });
    }
}
