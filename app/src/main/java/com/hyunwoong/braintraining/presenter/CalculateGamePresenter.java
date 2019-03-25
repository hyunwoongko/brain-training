package com.hyunwoong.braintraining.presenter;

import android.widget.Chronometer;
import android.widget.TextView;

import com.hyunwoong.braintraining.contract.CalculateGameContract;
import com.hyunwoong.braintraining.database.BestScoreDB;
import com.hyunwoong.braintraining.model.CalculateGameModel;
import com.hyunwoong.braintraining.model.DailyTrainingModel;
import com.hyunwoong.braintraining.utils.Effect;
import com.hyunwoong.braintraining.utils.GetRandom;
import com.hyunwoong.braintraining.utils.Grader;
import com.hyunwoong.braintraining.utils.MyScriptBuilder;
import com.hyunwoong.braintraining.utils.Scorer;
import com.hyunwoong.braintraining.utils.TypeWriter;
import com.hyunwoong.braintraining.utils.TypeWriterRequester;
import com.hyunwoong.braintraining.view.CalculateGameView;
import com.hyunwoong.braintraining.view.DailyTrainingView;
import com.myscript.atk.scw.SingleCharWidgetApi;

public class CalculateGamePresenter extends BasePresenter implements CalculateGameContract.presenter,
        SingleCharWidgetApi.OnTextChangedListener {

    private Chronometer chronometer;
    private TypeWriter mQuestionField;
    private TextView mTextField;
    private TextView mCorrectField;
    private SingleCharWidgetApi widget;
    private CalculateGameView view;
    // View

    private GetRandom random;
    private Scorer scorer;
    private TypeWriterRequester typeWriterRequester;
    // Utils

    private CalculateGameModel model;
    // Model

    @Override
    public void SetView(Chronometer chronometer,
                        TypeWriter mQuestionField,
                        TextView mTextField,
                        TextView mCorrectField,
                        SingleCharWidgetApi widget,
                        CalculateGameView view) {

        this.chronometer = chronometer;
        this.mQuestionField = mQuestionField;
        this.mTextField = mTextField;
        this.mCorrectField = mCorrectField;
        this.widget = widget;
        this.view = view;
        widget.setOnTextChangedListener(this);
        super.SetView(view);
    }

    @Override
    public void ClearView() {
        this.chronometer = null;
        this.mTextField = null;
        this.widget = null;
        this.view = null;
        super.ClearView();
    }

    @Override
    public void SetModel() {
        model = new CalculateGameModel();
    }

    @Override
    public void ClearModel() {
        model = null;
    }

    @Override
    public void MyScriptBuild() {
        MyScriptBuilder builder = new MyScriptBuilder(widget, view);
        builder.Build();
    }

    @Override
    public void MakeQuestion() {

        if (model == null) {
            SetModel(); // 데이터 요청
            model.init();
            random = new GetRandom();
            typeWriterRequester = new TypeWriterRequester(mQuestionField);
        }

        // 문제 낼때마다 두 숫자와 정답은 초기화 되어야함.
        model.setFirstNum(random.getRandom_Overlap(20));
        model.setSecondNum(random.getRandom_Overlap(20));
        // 계산에 필요한 첫번째 숫자와 두번째 숫자를 랜덤으로 만들어냄.
        model.setOperator(random.getRandom_Overlap(4));
        // 계산에 필요한 연산자를 랜덤으로 만들어냄

        if (model.getOperator() == 0) {
            model.setRightAnswer(model.getFirstNum() + model.getSecondNum());
            typeWriterRequester.Write(model.getFirstNum() + " + " + model.getSecondNum() + " = ", 120);
        } // 덧셈 연산
        else if (model.getOperator() == 1 && (model.getFirstNum() >= model.getSecondNum())) {
            model.setRightAnswer(model.getFirstNum() - model.getSecondNum());
            typeWriterRequester.Write(model.getFirstNum() + " - " + model.getSecondNum() + " = ", 120);
        }// 뺄셈 연산
        else if (model.getOperator() == 2) {
            model.setRightAnswer(model.getFirstNum() * model.getSecondNum());
            typeWriterRequester.Write(model.getFirstNum() + " x " + model.getSecondNum() + " = ", 120);
        }// 곱셈 연산
        else if (model.getOperator() == 3 && (model.getFirstNum() % (model.getSecondNum() + 1) == 0)) {
            model.setRightAnswer(model.getFirstNum() / (model.getSecondNum() + 1));
            typeWriterRequester.Write(model.getFirstNum() + " ÷ " + (model.getSecondNum() + 1 + " = "), 120);
        }// 나눗셈 연산 (나누어 떨어질때만, 나누는 값에 +1을 해서 항상 0이 아님)
        else {

            MakeQuestion();
        } // 네가지 경우의 수가 아닌경우 (예를들어 나누었는데 안떨어지는경우)
        //문제를 새로 출제한다.

    }

    @Override
    public void Delete() {
        mTextField.setText("");
        widget.clear();
    }

    @Override
    public void Submit() {
        if (mTextField.getText().toString().
                equals((Integer.valueOf(model.getRightAnswer())).toString())) {
            model.setCorrect(model.getCorrect() + 1);
            mCorrectField.setText("맞은 문제 : " + model.getCorrect());
            Effect.getInstance().RightAnswer_Beep(view);
            MakeQuestion();
            widget.clear();
        } else if (!(mTextField.getText().toString().
                equals((Integer.valueOf(model.getRightAnswer())).toString()))) {
            Effect.getInstance().WrongAnswer_Beep(view);
            MakeQuestion();
            widget.clear();
        }
        if (model.getCorrect() == 20) {
            GameClear();
        }
    }

    @Override
    public void GameClear() {
        chronometer.stop();
        scorer = new Scorer(chronometer);
        Grader grader= new Grader(scorer);

        String score = String.valueOf(scorer.ScoreTime());
        String grade = grader.getGrade(true, 30, 60, 90, 120, 150);
        String bestScore = BestScore().toString();

        if (DailyTrainingModel.getInstance().getIsTraining()) {
            DailyTrainingModel.getInstance().setGameNumber(1); // 다음 게임 번호
            MoveTo(DailyTrainingView.class, "TrainingResult", grade, 0);
        } else {
            MoveToScore(score, grade, bestScore, view);
        }
        if (model != null) {
            model = null;
        }
    }

    @Override
    public Integer BestScore() {
        scorer = new Scorer(chronometer);
        if (BestScoreDB.getInstance().getCalculateGameBest() == null) {
            BestScoreDB.getInstance().setCalculateGameBest(scorer.ScoreTime());
            return BestScoreDB.getInstance().getCalculateGameBest();
        }
        // 게임을 처음실행하는 (최고기록이 null인 유저)
        // 현재스코어가 최고점수가 됨.

        else {
            if (BestScoreDB.getInstance().getCalculateGameBest() > scorer.ScoreTime()) {
                return scorer.ScoreTime();
            } else {
                return BestScoreDB.getInstance().getCalculateGameBest();
            }

        }
        // 최초 실행 유저가 아닌.경우 (최고기록이 존재하는 유저)
        // 현재 스코어와 최고 점수를 비교해 더 빨리 클리어한 시간을 기록.
    }

    @Override
    public void onTextChanged(SingleCharWidgetApi singleCharWidgetApi, String s, boolean b) {
        mTextField.setText(s);
    }

}
