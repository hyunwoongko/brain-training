package com.hyunwoong.braintraining.presenter;

import android.util.Log;
import android.widget.GridLayout;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.MomentGameContract;
import com.hyunwoong.braintraining.database.BestScoreDB;
import com.hyunwoong.braintraining.model.DailyTrainingModel;
import com.hyunwoong.braintraining.model.MomentGameModel;
import com.hyunwoong.braintraining.task.MomentGameTask;
import com.hyunwoong.braintraining.utils.Effect;
import com.hyunwoong.braintraining.utils.GetRandom;
import com.hyunwoong.braintraining.utils.Grader;
import com.hyunwoong.braintraining.utils.Scorer;
import com.hyunwoong.braintraining.view.DailyTrainingView;
import com.hyunwoong.braintraining.view.MomentGameView;

import static android.view.View.TEXT_ALIGNMENT_GRAVITY;

public class MomentGamePresenter extends BasePresenter implements MomentGameContract.presenter {

    private TextView readygame;
    private GridLayout gridLayout;
    private MomentGameView view;
    private TextView moment;
    private TextView Qnumber;
    private TextView RightAnswerNumber;
    private TextView t1, t2, t3, t4, t5, t6, t7, t8, t9;

    private GetRandom random = new GetRandom();
    private MomentGameTask task;
    private Scorer scorer;
    private Grader grader;
    private MomentGameModel model;

    @Override
    public void SetView(GridLayout gridLayout,
                        TextView readygame,
                        TextView moment,
                        TextView Qnumber,
                        TextView RightAnswerNumber,
                        MomentGameView view) {
        this.gridLayout = gridLayout;
        this.readygame = readygame;
        this.moment = moment;
        this.Qnumber = Qnumber;
        this.RightAnswerNumber = RightAnswerNumber;
        this.view = view;
        super.SetView(view);

    }

    @Override
    public void SetModel() {
        model = new MomentGameModel();
        super.SetModel();
    }

    @Override
    public void InitModel() {
        model.init();
    }

    @Override
    public void SetTextButton(TextView t1, TextView t2, TextView t3,
                              TextView t4, TextView t5, TextView t6,
                              TextView t7, TextView t8, TextView t9) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.t6 = t6;
        this.t7 = t7;
        this.t8 = t8;
        this.t9 = t9;
    }

    @Override
    public void gameCountDown() {
        task = new MomentGameTask(readygame, moment, this);
        task.execute();
    }

    @Override
    public void MakeButton() {

        if (model == null) {
            SetModel();
            InitModel();
        }
        model.getTextList().add(t1);
        model.getTextList().add(t2);
        model.getTextList().add(t3);
        model.getTextList().add(t4);
        model.getTextList().add(t5);
        model.getTextList().add(t6);
        model.getTextList().add(t7);
        model.getTextList().add(t8);
        model.getTextList().add(t9);

        model.setSize(9);
        model.setNumberOfNumber(0);
        model.setQnumber(model.getQnumber() + 1);
        if (model.getQnumber() > 10) {
            GameClear();
        }
        Log.d("DDDDDD", String.valueOf(model.getQnumber()));
        Qnumber.setText("현재 문제 : " + model.getQnumber());
        model.setToPress(1);
        random = new GetRandom(); // 유틸 요청
        final int arr[] = random.getRandom_notOverlap(model.getSize());
        for (int rowCounter = 0; rowCounter < 3; rowCounter++)
            for (int columnCounter = 0; columnCounter < 3; columnCounter++) {
                model.setNumberOfNumber(model.getNumberOfNumber() + 1);
                // 버튼의 숫자를 한개씩 올림

                model.getTextList().get(model.getNumberOfNumber() - 1).setTextColor(getColor(R.color.black));
                model.getTextList().get(model.getNumberOfNumber() - 1).setText(String.valueOf(arr[model.getNumberOfNumber() - 1]));
                model.getTextList().get(model.getNumberOfNumber() - 1).setTextSize(75);
                model.getTextList().get(model.getNumberOfNumber() - 1).setGravity(TEXT_ALIGNMENT_GRAVITY);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.setMargins(0, 0, 0, 0);
                model.getTextList().get(model.getNumberOfNumber() - 1).setLayoutParams(params);
                // 그리드 레이아웃 세팅
            }
    }

    @Override
    public void TextClicked(TextView textView) {
        if (readygame.getText().toString().equals("Start !")) {
            if (String.valueOf(model.getToPress()).equals(textView.getText().toString())) {
                Effect.getInstance().RightAnswer_Beep(view);
                textView.setTextColor(view.getResources().getColor(R.color.skyblue));
                model.setToPress(model.getToPress() + 1);
                CheckClear();
            } else {
                Effect.getInstance().WrongAnswer_Beep(view);
                gameCountDown();
            }
        }
    }

    @Override
    public void CheckClear() {
        if (model.getToPress() > 9) {
            model.setToPress(1);
            model.setRightAnswerNum(model.getRightAnswerNum() + 1);
            RightAnswerNumber.setText("맞은 문제 : " + model.getRightAnswerNum() + " / 10");
            gameCountDown();
        }
    }

    @Override
    public void Hide() {
        for (int i = 0; i <= 8; i++)
            model.getTextList().get(i).setTextColor(getColor(R.color.white));
    }

    @Override
    public void GameClear() {

        scorer = new Scorer(model.getRightAnswerNum());
        grader = new Grader(scorer);

        String score = String.valueOf(scorer.ScoreAnswer());
        String grade = grader.getGrade(false, 18, 15, 13, 10, 5);
        String bestScore = BestScore().toString();
        if (DailyTrainingModel.getInstance().getIsTraining()) {
            DailyTrainingModel.getInstance().setGameNumber(3); // 다음 게임 번호
            MoveTo(DailyTrainingView.class, "TrainingResult", grade, 2);
        } else {
            MoveToScore(score, grade, bestScore, view);
        }
    }

    @Override
    public Integer BestScore() {

        scorer = new Scorer(model.getRightAnswerNum());

        if (BestScoreDB.getInstance().getMomentGameBest() == null) {
            BestScoreDB.getInstance().setMomentGameBest(scorer.ScoreAnswer());
            return BestScoreDB.getInstance().getMomentGameBest();
        } else {
            if (BestScoreDB.getInstance().getMomentGameBest() < scorer.ScoreAnswer()) {
                return scorer.ScoreAnswer();
            } else
                return BestScoreDB.getInstance().getMomentGameBest();
        }
    }

    @Override
    public void StopTask() {
        if (task != null) {
            task.cancel(true);
        }
    }
}
