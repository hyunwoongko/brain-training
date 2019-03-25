package com.hyunwoong.braintraining.presenter;

import android.widget.TextView;

import com.hyunwoong.braintraining.contract.LabialGameContract;
import com.hyunwoong.braintraining.database.BestScoreDB;
import com.hyunwoong.braintraining.model.DailyTrainingModel;
import com.hyunwoong.braintraining.model.LabialGameModel;
import com.hyunwoong.braintraining.utils.Effect;
import com.hyunwoong.braintraining.utils.GetRandom;
import com.hyunwoong.braintraining.utils.Grader;
import com.hyunwoong.braintraining.utils.MyScriptBuilder;
import com.hyunwoong.braintraining.utils.NewtonTTSApi;
import com.hyunwoong.braintraining.utils.Scorer;
import com.hyunwoong.braintraining.utils.TTSManager;
import com.hyunwoong.braintraining.utils.TypeWriter;
import com.hyunwoong.braintraining.utils.TypeWriterRequester;
import com.hyunwoong.braintraining.view.DailyTrainingView;
import com.hyunwoong.braintraining.view.LabialGameView;
import com.myscript.atk.scw.SingleCharWidgetApi;

public class LabialGamePresenter extends BasePresenter implements LabialGameContract.presenter {

    private TypeWriter typeWriter;
    private TypeWriterRequester typeWriterRequester;
    private TextView textInput;
    private SingleCharWidgetApi widgetApi;
    private MyScriptBuilder builder;
    private LabialGameModel model;
    private GetRandom random;
    private LabialGameView view;
    private TextView Qnumber;
    private TextView RightAnswerNumber;
    private Scorer scorer;
    private Grader grader;

    public void SetView(TypeWriter typeWriter,
                        TextView textInput,
                        SingleCharWidgetApi widgetApi,
                        TextView Qnumber,
                        TextView RightAnswerNumber,
                        LabialGameView view) {
        this.typeWriter = typeWriter;
        this.textInput = textInput;
        this.widgetApi = widgetApi;
        this.Qnumber = Qnumber;
        this.RightAnswerNumber = RightAnswerNumber;
        this.view = view;

        super.SetView(view);
    }

    @Override
    public void InitModel() {
        model.Init();
    }

    @Override
    public void SetModel() {
        super.SetModel();
        model = new LabialGameModel();
    }

    @Override
    public void init() {

        if (model == null) {
            SetModel();
            InitModel();
        } // Modelnit

        if (typeWriterRequester == null) {
            typeWriterRequester = new TypeWriterRequester(typeWriter);
        }
        typeWriterRequester.Write("문제를 들으시려면 \n 저를 눌러주세요.", 100);
        //TypeWriter Init

        TTSManager.getInstance().initializeLibrary(view);
        NewtonTTSApi.getInstance(view).Init();
        //TTS Init

        if (random == null) {
            random = new GetRandom();
        }
        model.setArr(random.getRandom_notOverlap(50));
        //Randeom Init

        if (builder == null) {
            builder = new MyScriptBuilder(widgetApi, view);
        }
        builder.Build();
        //MyScript Init
    }

    @Override
    public void StopTTS() {
        if (NewtonTTSApi.getInstance(view).isPlaying()) {
            NewtonTTSApi.getInstance(view).stopTTS();
        }

    }

    @Override
    public void MakeQuestion() {
        model.setStrText(model.getNUM_SENTENCE().get(model.getArr()[model.getSenNum()]));
        model.setTextNum(model.getNUM_LABIAL().get(model.getArr()[model.getSenNum()]));
        TTSManager.getInstance().initializeLibrary(view);
        NewtonTTSApi.getInstance(view).playTTS(model.getStrText());
        model.setQnumber(model.getQnumber() + 1);
        Qnumber.setText("현재 문제 : " + model.getQnumber());
        if (model.getQnumber() > 20) {
            GameClear();
        }
    }

    @Override
    public void Delete() {
        textInput.setText("");
        widgetApi.clear();
    }

    @Override
    public void Submit() { /*TODO: 코드 가독성 너무 안좋음
                              TODO : 보기 좋게 변경할 것*/

        if (model.getStrText() != null && model != null) {
            if (textInput.getText().toString().equals(model.getNUM_LABIAL().get(model.getArr()[model.getSenNum()]).toString())) {
                typeWriterRequester.Write("정답! " + model.getNUM_LABIAL().get(model.getArr()[model.getSenNum()]).toString() + "개 " + "\n"
                        + model.getSTRING_LABIAL().get(model.getArr()[model.getSenNum()]), 100);
                TTSManager.getInstance().finalizeLibrary();
                Effect.getInstance().RightAnswer_Beep(view);
                model.setSenNum(model.getSenNum() + 1);
                model.setRightAnswerNumber(model.getRightAnswerNumber() + 1);
                RightAnswerNumber.setText("맞은 문제 : " + model.getRightAnswerNumber() + " / 20");
                MakeQuestion();

            } else {
                Effect.getInstance().WrongAnswer_Beep(view);
                typeWriterRequester.Write("오답! " + model.getNUM_LABIAL().get(model.getArr()[model.getSenNum()]).toString() + "개 " + "\n"
                        + model.getSTRING_LABIAL().get(model.getArr()[model.getSenNum()]), 100);
                TTSManager.getInstance().finalizeLibrary();
                model.setSenNum(model.getSenNum() + 1);
                MakeQuestion();
            }
        }
        widgetApi.clear();
    }

    @Override
    public void avoidSubmit() {
        typeWriterRequester.Write("끝까지 들어주세요!", 50);
    }

    @Override
    public boolean isPlaying() {
        return NewtonTTSApi.getInstance(view).isPlaying();
    }

    @Override
    public void GameClear() {

        scorer = new Scorer(model.getRightAnswerNumber());
        grader = new Grader(scorer);

        String score = String.valueOf(scorer.ScoreAnswer());
        String grade = grader.getGrade(false, 18, 15, 13, 10, 5);
        String bestScore = BestScore().toString();

        if (DailyTrainingModel.getInstance().getIsTraining()) {
            DailyTrainingModel.getInstance().setGameNumber(8); // 다음 게임 번호
            MoveTo(DailyTrainingView.class, "TrainingResult", grade, 7);
        } else {
            MoveToScore(score, grade, bestScore, view);
        }
    }

    @Override
    public Integer BestScore() {

        scorer = new Scorer(model.getRightAnswerNumber());

        if (BestScoreDB.getInstance().getLabialGameBest() == null) {
            BestScoreDB.getInstance().setLabialGameBest(scorer.ScoreAnswer());
            return BestScoreDB.getInstance().getLabialGameBest();
        } else {
            if (BestScoreDB.getInstance().getLabialGameBest() < scorer.ScoreAnswer()) {
                return scorer.ScoreAnswer();
            } else
                return BestScoreDB.getInstance().getLabialGameBest();
        }
    }

}
