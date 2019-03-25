package com.hyunwoong.braintraining.presenter;

import android.os.AsyncTask;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.WordGameContract;
import com.hyunwoong.braintraining.database.BestScoreDB;
import com.hyunwoong.braintraining.model.DailyTrainingModel;
import com.hyunwoong.braintraining.model.WordGameModel;
import com.hyunwoong.braintraining.task.WordGameTask;
import com.hyunwoong.braintraining.utils.Effect;
import com.hyunwoong.braintraining.utils.GetRandom;
import com.hyunwoong.braintraining.utils.Grader;
import com.hyunwoong.braintraining.utils.MyScriptBuilder;
import com.hyunwoong.braintraining.utils.Scorer;
import com.hyunwoong.braintraining.view.DailyTrainingView;
import com.hyunwoong.braintraining.view.WordGameView;
import com.myscript.atk.scw.SingleCharWidgetApi;

public class WordGamePresenter extends BasePresenter implements WordGameContract.presenter{

    private GetRandom random;
    private MyScriptBuilder builder;
    private WordGameTask task;
    private Scorer scorer;
    private Grader grader;

    private GridLayout gridLayout;
    private WordGameView view;
    private SingleCharWidgetApi widgetApi;
    private TextView mTextfield;
    private TextView readygame;
    private TextView word;
    private TextView SubmitNum;
    private TextView RightAnswerNumber;
    private WordGameModel model;

    @Override
    public void SetView(GridLayout gridLayout,
                        SingleCharWidgetApi widgetApi,
                        TextView mTextfield,
                        TextView readygame,
                        TextView word,
                        TextView SubmitNum,
                        TextView RightAnswerNumber,
                        WordGameView view) {
        this.gridLayout = gridLayout;
        this.widgetApi = widgetApi;
        this.mTextfield = mTextfield;
        this.SubmitNum = SubmitNum;
        this.readygame = readygame;
        this.word = word;
        this.RightAnswerNumber = RightAnswerNumber;

        this.view = view;
        super.SetView(view);
    }

    @Override
    public void SetModel() {
        super.SetModel();
        model = new WordGameModel();
    }

    @Override
    public void InitModel() {
        model.init();
    }

    @Override
    public void ClearModel() {
        model = null;
    }

    @Override
    public void initModel() {
        SetModel(); // 데이터 요청
        InitModel();
        random = new GetRandom(); // 유틸 요청
    }

    @Override
    public void Build() {
        builder = new MyScriptBuilder(widgetApi, view);
        builder.Build();
    }

    @Override
    public void MakeTextField() {

        model.setNumOfWords(0);
        // 20개의 중복 되지 않는 숫자들을 배열에 담음.

        int[] arr = (random.getRandom_notOverlap(model.getSize()));
        for (int rowCounter = 0; rowCounter < 5; rowCounter++)
            for (int columnCounter = 0; columnCounter < 4; columnCounter++) {
                // 4 x 5 그리드 레이아웃을 만듬.
                model.setWordNumArr(arr);
                model.setNumOfWords(model.getNumOfWords() + 1);
                // 포문 한번 돌때마다, 단어 번호가 1씩 올라감.

                TextView Words = new TextView(view);
                // 포문 돌때마다 텍스트뷰를 하나씩 생성.

                Words.setText(" " + model.getWords().get(model.getWordNumArr()[model.getNumOfWords() - 1]) + " ");
                // 텍스트뷰에 랜덤으로 생성한 20개의 단어번호에 해당하는 단어를 한개씩 세트함.
                Words.setTextColor(getColor(R.color.black));
                Words.setTextSize(25);
                Words.setGravity(View.TEXT_ALIGNMENT_CENTER);
                model.getWordList().add(model.getWords().get(model.getWordNumArr()[model.getNumOfWords() - 1]));
                model.getTvList().add(Words);
                // 텍스트뷰를 생성하고 리스트에 넣어서 밖에서도 사용할 수 있게 만듬.


                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.setMargins(0, 0, 0, 0);
                Words.setLayoutParams(params);
                // 그리드 레이아웃 세팅

                gridLayout.addView(Words);
                //그리드 레이아웃에 텍스트뷰 추가.
            }
    }

    @Override
    public void Hide() {
        for (int i = 1; i <= 20; i++) {
            TextView textView = model.getTvList().get(i - 1);
            textView.setTextColor(getColor(R.color.gray));

        }
    }

    @Override
    public void Delete() {
        if (builder != null) {
            mTextfield.setText("");
            widgetApi.clear();
        }
    }

    @Override
    public void Submit() {
        if (builder != null) {
            model.setSubmitNum(model.getSubmitNum() - 1); // 제출기회 감소
            SubmitNum.setText("제출 기회 : " + model.getSubmitNum());
            if (model.getSubmitNum() == 0) {
                GameClear();
            }
            for (int i = 1; i <= 20; i++) {
                if (mTextfield.getText().toString().equals(model.getWordList().get(i - 1))) {
                    model.getTvList().get(i - 1).setTextColor(getColor(R.color.black));
                    model.setRightAnswerNumber(model.getRightAnswerNumber() + 1);
                    Effect.getInstance().RightAnswer_Beep(view);
                    RightAnswerNumber.setText("맞은 단어 : " + String.valueOf(model.getRightAnswerNumber())
                            + " / 20");
                }
            }
            mTextfield.setText("");
            widgetApi.clear();
        }
    }

    @Override
    public void WordgameCountdown() {
        if (model == null) {
            initModel();
        }
        task = new WordGameTask(readygame, word, this);
        task.execute();
    }

    @Override
    public void GameClear() {

        scorer = new Scorer(model.getRightAnswerNumber());
        grader = new Grader(scorer);

        String score = String.valueOf(scorer.ScoreAnswer());
        String grade = grader.getGrade(false, 18, 15, 13, 10, 5);
        String bestScore = BestScore().toString();

        if (DailyTrainingModel.getInstance().getIsTraining()) {
            DailyTrainingModel.getInstance().setGameNumber(7); // 다음 게임 번호
            MoveTo(DailyTrainingView.class, "TrainingResult", grade,6);
        } else {
            MoveToScore(score, grade, bestScore, view);
        }
    }

    @Override
    public Integer BestScore() {

        scorer = new Scorer(model.getRightAnswerNumber());

        if (BestScoreDB.getInstance().getWordGameBest() == null) {
            BestScoreDB.getInstance().setWordGameBest(scorer.ScoreAnswer());
            return BestScoreDB.getInstance().getWordGameBest();
        } else {
            if (BestScoreDB.getInstance().getWordGameBest() < scorer.ScoreAnswer()) {
                return scorer.ScoreAnswer();
            } else
                return BestScoreDB.getInstance().getWordGameBest();
        }
    }

    @Override
    public void StopTask() {
        if (task != null) {
            task.cancel(true);
        }
    }
}
