package com.hyunwoong.braintraining.presenter;

import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.utils.FirebaseHelper;
import com.hyunwoong.braintraining.view.ScoreView;
import com.hyunwoong.braintraining.contract.ScoreContract;
import com.hyunwoong.braintraining.database.BestScoreDB;
import com.hyunwoong.braintraining.database.UserCurrentDB;
import com.hyunwoong.braintraining.utils.TypeWriterRequester;
import com.hyunwoong.braintraining.utils.TypeWriter;
import com.hyunwoong.braintraining.utils.UserInfo;

public class ScorePresenter extends BasePresenter implements ScoreContract.presenter {

    private TextView nickname;
    private TextView userId;
    private ImageView profileImage;
    private TypeWriter score;
    private ScoreView view;
    // View

    private UserInfo userInfo;
    private TypeWriterRequester typeWriterRequester;
    // Utils

    @Override
    public void SetView(TextView nickname,
                        TextView userId,
                        ImageView profileImage,
                        TypeWriter score,
                        ScoreView view) {

        this.nickname = nickname;
        this.userId = userId;
        this.profileImage = profileImage;
        this.score = score;
        this.view = view;

        super.SetView(view);
    }

    @Override
    public void ClearView() {
        super.ClearView();
    }

    @Override
    public void drawUserInfo() {
        userInfo = new UserInfo(nickname, userId, profileImage, view);
        userInfo.drawUserInfo();
    }

    @Override
    public void upDateScore() {


        typeWriterRequester = new TypeWriterRequester(score);

        typeWriterRequester.Write(UserCurrentDB.getInstance().getNickname() + "님의 등급은 " + view.getIntent().getStringExtra("grade")
                + ", 점수는 " + view.getIntent().getStringExtra("score") + "입니다! " +
                "이 게임의 최고점수는 " + view.getIntent().getStringExtra("bestScore") +"점 이네요!", 0);
    }

    @Override
    public void SavaGameData() {
        FirebaseHelper.getInstance().setValue("BestScoreDB", BestScoreDB.getInstance());
    }

}
