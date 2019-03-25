package com.hyunwoong.braintraining.presenter;

import android.os.Bundle;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.TextView;

import com.hyunwoong.braintraining.contract.ColorGameContract;
import com.hyunwoong.braintraining.database.BestScoreDB;
import com.hyunwoong.braintraining.model.ColorGameModel;
import com.hyunwoong.braintraining.model.DailyTrainingModel;
import com.hyunwoong.braintraining.utils.GetRandom;
import com.hyunwoong.braintraining.utils.Grader;
import com.hyunwoong.braintraining.utils.NewtonSTTApi;
import com.hyunwoong.braintraining.utils.Scorer;
import com.hyunwoong.braintraining.view.ColorGameView;
import com.hyunwoong.braintraining.view.DailyTrainingView;
import com.kakao.sdk.newtoneapi.SpeechRecognizeListener;
import com.kakao.sdk.newtoneapi.SpeechRecognizerClient;

import java.util.ArrayList;

public class ColorGamePresenter extends BasePresenter implements ColorGameContract.presenter {

    private TextView txtSpeechInput;
    private TextView question;
    private TextView rightAnswer;
    private Chronometer chronometer;
    private ColorGameView view;
    // View

    private GetRandom random;
    private Grader grader;
    private Scorer scorer;
    //utils

    private ColorGameModel model;
    // Model

    @Override
    public void SetView(
            TextView txtSpeechInput,
            TextView question,
            TextView rightAnswer,
            Chronometer chronometer,
            ColorGameView view) {

        this.txtSpeechInput = txtSpeechInput;
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.chronometer = chronometer;
        this.view = view;
        super.SetView(view);
    }

    @Override
    public void ClearView() {
        this.txtSpeechInput = null;
        this.question = null;
        this.rightAnswer = null;
        this.chronometer = null;
        this.view = null;
        super.ClearView();
    }

    @Override
    public void SetModel() {
        model = new ColorGameModel(view);
        super.SetModel();
    }

    @Override
    public void ClearModel() {
        model = null;
        super.ClearModel();
    }

    @Override
    public void MakeQuestion() {
        if (model == null) {
            SetModel();
            model.init();
        }
        random = new GetRandom(); // 유틸 요청

        final int randomText = random.getRandom_Overlap(9); // 유틸 사용
        final int randomColor = random.getRandom_Overlap(9); // 유틸 사용


        question.setText(model.getColorText()[randomText]); // 뷰를 업데이트
        question.setTextColor(model.getColorNum()[randomColor]); // 뷰를 업데이트

        Log.d("aaaaa", String.valueOf(question.getCurrentTextColor()));
        txtSpeechInput.setText("색깔을 말해주세요 !"); // 뷰를 업데이트
    }

    @Override
    public void CheckAnswer(String result1, String result2, String result3, String result4, String result5) {
        if ((result1.equals("빨강") || result2.equals("빨강") || result3.equals("빨강") ||
                result4.equals("빨강") | result5.equals("빨강"))
                && question.getCurrentTextColor() == -65536) {
            NextQuestion();
        } else if ((result1.equals("검정") || result2.equals("검정") || result3.equals("검정") ||
                result4.equals("검정") | result5.equals("검정"))
                && question.getCurrentTextColor() == -16777216) {
            NextQuestion();
        } else if ((result1.equals("파랑") || result2.equals("파랑") || result3.equals("파랑") ||
                result4.equals("파랑") | result5.equals("파랑"))
                && question.getCurrentTextColor() == -16776961
                ) {
            NextQuestion();
        } else if ((result1.equals("노랑") || result2.equals("노랑") || result3.equals("노랑") ||
                result4.equals("노랑") | result5.equals("노랑"))
                && question.getCurrentTextColor() == -256) {
            NextQuestion();
        } else if ((result1.equals("초록") || result2.equals("초록") || result3.equals("초록") ||
                result4.equals("초록") | result5.equals("초록"))
                && question.getCurrentTextColor() == -16711936) {
            NextQuestion();
        } else if ((result1.equals("보라") || result2.equals("보라") || result3.equals("보라") ||
                result4.equals("보라") | result5.equals("보라"))
                && question.getCurrentTextColor() == -8978313) {
            NextQuestion();
        } else if ((result1.equals("주황") || result2.equals("주황") || result3.equals("주황") ||
                result4.equals("주황") | result5.equals("주황"))
                && question.getCurrentTextColor() == -35072) {
            NextQuestion();

        } else if ((result1.equals("분홍") || result2.equals("분홍") || result3.equals("분홍") ||
                result4.equals("분홍") | result5.equals("분홍"))
                && question.getCurrentTextColor() == -13108) {
            NextQuestion();

        } else if ((result1.equals("하늘") || result2.equals("하늘") || result3.equals("하늘") ||
                result4.equals("하늘") | result5.equals("하늘"))
                && question.getCurrentTextColor() == -8925461) {
            NextQuestion();

        }
    }

    @Override
    public void NextQuestion() {
        model.setRightAnswer(model.getRightAnswer() + 1);
        rightAnswer.setText("맞은문제 : " + model.getRightAnswer()); // 뷰를 업데이트
        if (Integer.valueOf(String.valueOf(chronometer.getText().toString().charAt(1))) >= 1) {
            SaveData();
            GameClear(); // 1분 넘어가면 문제 그만내고 게임을 종료시킴.
        } else MakeQuestion();                                               // 데이터 사용
    }

    @Override
    public void SaveData() {
        BestScoreDB.getInstance().setColorGameBest(BestScore());
    }

    @Override
    public void GameClear() {

        scorer = new Scorer(chronometer, model.getRightAnswer());
        grader = new Grader(scorer); // 유틸 요청

        String score = String.valueOf(scorer.ScoreAnswer());
        String grade = grader.getGrade(false, 30, 25, 20, 15, 10);
        String bestScore = BestScore().toString();

        if (DailyTrainingModel.getInstance().getIsTraining()) {
            DailyTrainingModel.getInstance().setGameNumber(4); // 다음 게임 번호
            MoveTo(DailyTrainingView.class, "TrainingResult", grade, 3);
        } else {
            MoveToScore(score, grade, bestScore, view);
        }
    }

    @Override
    public Integer BestScore() {

        scorer = new Scorer(chronometer, model.getRightAnswer());

        if (BestScoreDB.getInstance().getColorGameBest() == null) {
            BestScoreDB.getInstance().setColorGameBest(scorer.ScoreAnswer());
            return BestScoreDB.getInstance().getColorGameBest();
        } else {
            if (BestScoreDB.getInstance().getColorGameBest() < scorer.ScoreAnswer()) {
                return scorer.ScoreAnswer();
            } else
                return BestScoreDB.getInstance().getColorGameBest();
        }
    }

    @Override
    public void STTinit() {
        NewtonSTTApi.getInstance(view).init();
        NewtonSTTApi.getInstance(view).getClient().setSpeechRecognizeListener(new SpeechRecognizeListener() {
            @Override
            public void onReady() {
                startSTT();
            }

            @Override
            public void onBeginningOfSpeech() {
                startSTT();
            }

            @Override
            public void onEndOfSpeech() {
                startSTT();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                startSTT();
            }

            @Override
            public void onPartialResult(String partialResult) {
                startSTT();
            }

            @Override
            public void onResults(Bundle results) {

                ArrayList<String> list = new ArrayList<>();

                for (int i = 0; i <= 5; i++) {
                    list.add(i, results.getStringArrayList(SpeechRecognizerClient.KEY_RECOGNITION_RESULTS).get(i));
                    if (results.getStringArrayList(SpeechRecognizerClient.KEY_RECOGNITION_RESULTS).get(i) == null) {
                        list.set(i, "유사도 체크 불가");
                    } // 리스트에 검색결과를 차례대로 넣음.
                }
                txtSpeechInput.setText(
                        "1. " + list.get(0) + "\n" + "2. " + list.get(1) + "\n" + "3. " + list.get(2) + "\n" +
                                "4. " + list.get(3) + "\n" + "5. " + list.get(4) + "\n");
                // 유사도별로 화면에 띄워줌

                CheckAnswer(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4)
                        //정답 확인
                );
            }

            @Override
            public void onAudioLevel(float audioLevel) {
                startSTT();
            }

            @Override
            public void onFinished() {
                startSTT();
            }
        });
    }

    @Override
    public void startSTT() {
        NewtonSTTApi.getInstance(view).StartSTT();
    }

    @Override
    public void stopSTT() {
        NewtonSTTApi.getInstance(view).StopSTT();
    }

    @Override
    public void ClearSTT() {
        NewtonSTTApi.getInstance(view).ClearSTT();
        NewtonSTTApi.getInstance(view).setClient(null);
    }
}
