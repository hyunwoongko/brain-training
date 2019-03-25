package com.hyunwoong.braintraining.presenter;

import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.TextView;

import com.hyunwoong.braintraining.model.DailyTrainingModel;
import com.hyunwoong.braintraining.view.CountGameView;
import com.hyunwoong.braintraining.contract.CountGameContract;
import com.hyunwoong.braintraining.model.CountGameModel;
import com.hyunwoong.braintraining.database.BestScoreDB;
import com.hyunwoong.braintraining.utils.Effect;
import com.hyunwoong.braintraining.utils.GetRandom;
import com.hyunwoong.braintraining.utils.Grader;
import com.hyunwoong.braintraining.utils.Scorer;
import com.hyunwoong.braintraining.view.DailyTrainingView;

public class CountGamePresenter extends BasePresenter implements CountGameContract.presenter {

    private GridLayout gridLayout;
    private TextView remaining_number;
    private Chronometer chronometer;
    private CountGameView view;
    // View

    private GetRandom random;
    private Grader grader;
    private Scorer scorer;
    // Utils

    CountGameModel model;
    //Model

    @Override
    public void SetView(GridLayout gridLayout,
                        TextView remaining_number,
                        Chronometer chronometer,
                        CountGameView view) {

        this.gridLayout = gridLayout;
        this.remaining_number = remaining_number;
        this.chronometer = chronometer;
        this.view = view;
        super.SetView(view);
    }

    @Override
    public void ClearView() {
        super.ClearView();
    }

    @Override
    public void SetModel() {
        model = new CountGameModel();
        super.SetModel();
    }

    @Override
    public void ClearModel() {
        model = null;
        super.ClearModel();
    }

    @Override
    public void MakeButton() {
        SetModel(); // 데이터 요청
        model.init(); // 데이터 초기화
        random = new GetRandom(); // 유틸 요청
        int arr[] = random.getRandom_notOverlap(model.getSize());
        for (int rowCounter = 0; rowCounter < 7; rowCounter++)
            for (int columnCounter = 0; columnCounter < 5; columnCounter++) {
                model.setNumberOfButton(model.getNumberOfButton() + 1);
                // 버튼의 숫자를 한개씩 올림
                final Button button = new Button(view);
                button.setText(String.valueOf(arr[model.getNumberOfButton() - 1]));
                button.setHeight(150);
                // 버튼 세팅
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.setMargins(0, 0, 0, 0);
                button.setLayoutParams(params);
                // 그리드 레이아웃 세팅
                gridLayout.addView(button);
                //그리드 레이아웃에 버튼 추가.


                arr[model.getNumberOfButton() - 1] = model.getButtonToPress();
                {
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) { // 버튼이 눌렸을 때
                            if (model.getButtonToPress() == Integer.valueOf(button.getText().toString())) {
                                //눌러야할 차례의 번호가 눌렸을 때만
                                Button_Beep();
                                button.setVisibility(View.INVISIBLE); // 버튼 제거
                                ButtonPress();
                            }else{
                                Effect.getInstance().WrongAnswer_Beep(view);
                            }

                            if (model.getButtonToPress() == model.getSize() + 1) {
                                SaveData();
                                GameClear();

                                // AppData -> Model
                            }
                        }
                    });
                }
            }
    }

    private void ButtonPress() {
        model.setButtonToPress(model.getButtonToPress() + 1);
        model.setRemainingButton((35 - model.getButtonToPress()) + 1);
        //눌러야할 버튼 번호를 1증가시키고, 남은 버튼 갯수를 하나 줄임.

        remaining_number.setText("남은 숫자 갯수 : " + model.getRemainingButton());
        //남은 버튼 갯수 출력
    }

    @Override
    public void SaveData() {
        BestScoreDB.getInstance().setCountGameBest(BestScore());
        // AppData -> Model
    }

    @Override
    public void GameClear() {

        chronometer.stop();
        scorer = new Scorer(chronometer);
        grader = new Grader(scorer);

        String score = String.valueOf(scorer.ScoreTime());
        String grade = grader.getGrade(true, 30, 60, 90, 120, 150);
        String bestScore = BestScore().toString();

        if (DailyTrainingModel.getInstance().getIsTraining()) {
            DailyTrainingModel.getInstance().setGameNumber(2); // 다음 게임번호
            MoveTo(DailyTrainingView.class, "TrainingResult", grade , 1);
        } else {
            MoveToScore(score, grade, bestScore, view);
        }
    }

    @Override
    public Integer BestScore() {

        scorer = new Scorer(chronometer);

        if (BestScoreDB.getInstance().getCountGameBest() == null) {
            BestScoreDB.getInstance().setCountGameBest(scorer.ScoreTime());
            return BestScoreDB.getInstance().getCountGameBest();
        }
        // 게임을 처음실행하는 (최고기록이 null인 유저)
        // 현재스코어가 최고점수가 됨.

        else {
            if (BestScoreDB.getInstance().getCountGameBest() > scorer.ScoreTime()) {
                return scorer.ScoreTime();
            } else {
                return BestScoreDB.getInstance().getCountGameBest();
            }

        }
        // 최초 실행 유저가 아닌.경우 (최고기록이 존재하는 유저)
        // 현재 스코어와 최고 점수를 비교해 더 빨리 클리어한 시간을 기록.
    }
}
