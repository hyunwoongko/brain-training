package com.hyunwoong.braintraining.presenter;

import android.view.View;
import android.widget.Button;

import com.hyunwoong.braintraining.utils.PreferenceHelper;
import com.hyunwoong.braintraining.view.TutorialView;
import com.hyunwoong.braintraining.contract.TutorialContract;
import com.hyunwoong.braintraining.model.TutorialModel;
import com.hyunwoong.braintraining.database.UserCurrentDB;
import com.hyunwoong.braintraining.utils.TypeWriterRequester;
import com.hyunwoong.braintraining.utils.TypeWriter;

public class TutorialPresenter extends BasePresenter implements TutorialContract.presenter {

    private TypeWriter typeWriter;
    private Button startButton;
    private TutorialView view;
    // View

    private TypeWriterRequester typeWriterRequester;
    // Utills

    private TutorialModel model;
    // Model

    @Override
    public void SetView(TypeWriter typeWriter,
                        Button startButton,
                        TutorialView view) {

        this.typeWriter = typeWriter;
        this.startButton = startButton;
        this.view = view;
        super.SetView(view);
    }

    @Override
    public void ClearView() {
        this.typeWriter = null;
        this.startButton = null;
        this.view = null;
        super.ClearView();
    }

    @Override
    public void SetModel() {
        model = new TutorialModel();
    }

    @Override
    public void ClearModel() {
        model = null;
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
                typeWriterRequester.Write("안녕하세요." // 유틸 사용
                        + UserCurrentDB.getInstance().getNickname()
                        + "님 처음 오셨군요!" , 10);
                model.setDialogNum(model.getDialogNum() + 1); // 데이터 사용
                break;

            case 2:
                typeWriterRequester.Write("두뇌훈련을 위한 게임, 말랑말랑 브레인 트레이닝에 오신 것을 환영합니다." , 10); // 유틸 사용
                model.setDialogNum(model.getDialogNum() + 1);// 데이터 사용
                break;

            case 3:
                typeWriterRequester.Write("저는 여러분의 브레인 트레이너 Brainee라고 합니다." , 10); // 유틸 사용
                model.setDialogNum(model.getDialogNum() + 1); // 데이터 사용
                break;

            case 4:
                typeWriterRequester.Write("말랑말랑 브레인 트레이닝은 두뇌 개발 게임 애플리케이션입니다." , 10); // 유틸 사용
                model.setDialogNum(model.getDialogNum() + 1); // 데이터 사용
                break;

            case 5:
                typeWriterRequester.Write("게임시작을 통해 8종류의 게임을 각각 플레이 해볼 수 있습니다." , 10); // 유틸 사용
                model.setDialogNum(model.getDialogNum() + 1); // 데이터 사용
                break;

            case 6:
                typeWriterRequester.Write("그럼 이제 게임을 시작해 볼까요?" ,10); // 유틸 사용
                startButton.setVisibility(View.VISIBLE);
                ClearModel();
                break;
        }
    }


    @Override
    public void NoMoreFirstUser() {
        PreferenceHelper.getInstance(view.getApplicationContext()).setBoolean("FirstUser", false);
        // 한번이라도 이 곳에 들어오면 퍼스트유저 = false가 되어 더이상 들어올 수 없음.
    }

}
