package com.hyunwoong.braintraining.presenter;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.MixGameContract;
import com.hyunwoong.braintraining.database.BestScoreDB;
import com.hyunwoong.braintraining.model.DailyTrainingModel;
import com.hyunwoong.braintraining.model.MixGameModel;
import com.hyunwoong.braintraining.utils.Effect;
import com.hyunwoong.braintraining.utils.GetRandom;
import com.hyunwoong.braintraining.utils.Grader;
import com.hyunwoong.braintraining.utils.Scorer;
import com.hyunwoong.braintraining.view.DailyTrainingView;
import com.hyunwoong.braintraining.view.MixGameView;

import java.util.ArrayList;

public class MixGamePresenter extends BasePresenter implements MixGameContract.presenter{

    private ImageView mixquestion1;
    private ImageView mixquestion2;
    private ImageView mixanswer1;
    private ImageView mixanswer2;
    private ImageView mixanswer3;
    private TextView RightAnswerNumber;
    private TextView Qnumber;
    private MixGameView view;
    // view

    private GetRandom random;
    private  Scorer scorer;
    private Grader grader;
    // utils

    MixGameModel model;
    // model

    @Override
    public void SetView(ImageView mixquestion1,
                        ImageView mixquestion2,
                        ImageView mixanswer1,
                        ImageView mixanswer2,
                        ImageView mixanswer3,
                        TextView RightAnswerNumber,
                        TextView Qnumber,
                        MixGameView view) {


        this.mixquestion1 = mixquestion1;

        this.mixquestion2 = mixquestion2;
        this.mixanswer1 = mixanswer1;
        this.mixanswer2 = mixanswer2;
        this.mixanswer3 = mixanswer3;
        this.RightAnswerNumber = RightAnswerNumber;
        this.Qnumber = Qnumber;
        this.view = view;
        super.SetView(view);
    }

    @Override
    public void ClearView() {
        super.ClearView();
    }

    @Override
    public void SetModel() {
        model = new MixGameModel();
        super.SetModel();
    }

    @Override
    public void InitModel() {
        model.init();
    }

    @Override
    public void ClearModel() {
        super.ClearModel();
    }

    @Override
    public void MakeQuestion() {

        if (model == null) {
            SetModel();
            InitModel();
        }
        if (random == null) {
            random = new GetRandom();
        }
        model.setQnumber(model.getQnumber() + 1);
        Qnumber.setText(String.valueOf("현재 문제 : " + model.getQnumber()));
        final int randomFirstColor = random.getRandom_Overlap(10); // 유틸 사용
        final int randomSecondColor = random.getRandom_Overlap(10); // 유틸 사용

        AssignColor(randomFirstColor, mixquestion1);
        AssignColor(randomSecondColor, mixquestion2);

        model.setRightAnswer(ColorMixer(AssignColor(randomFirstColor, mixquestion1),
                AssignColor(randomSecondColor, mixquestion2)));

        model.setWrongAnswer1(RandomColorMaker());
        while (model.getWrongAnswer1().equals(model.getRightAnswer())) {
            model.setWrongAnswer1(RandomColorMaker());
        }


        model.setWrongAnswer2(RandomColorMaker());
        while (model.getWrongAnswer2().equals(model.getRightAnswer())) {
            model.setWrongAnswer2(RandomColorMaker());
        }

        model.setArr(random.getRandom_notOverlap(3));

        if (model.getArr()[0] == 1) {
            mixanswer1.setBackgroundColor(Color.parseColor(model.getRightAnswer()));
            mixanswer2.setBackgroundColor(Color.parseColor(model.getWrongAnswer1()));
            mixanswer3.setBackgroundColor(Color.parseColor(model.getWrongAnswer2()));
        } else if (model.getArr()[0] == 2) {
            mixanswer1.setBackgroundColor(Color.parseColor(model.getWrongAnswer1()));
            mixanswer2.setBackgroundColor(Color.parseColor(model.getRightAnswer()));
            mixanswer3.setBackgroundColor(Color.parseColor(model.getWrongAnswer2()));
        } else {
            mixanswer1.setBackgroundColor(Color.parseColor(model.getWrongAnswer1()));
            mixanswer2.setBackgroundColor(Color.parseColor(model.getWrongAnswer2()));
            mixanswer3.setBackgroundColor(Color.parseColor(model.getRightAnswer()));
        }
    }

    @Override
    public String AssignColor(int ColorNum, ImageView imageView) {
        if (ColorNum == 0) {
            imageView.setBackgroundColor(view.getResources().getColor(R.color.red));
            return view.getResources().getString(R.string.red_Hexa);
        } else if (ColorNum == 1) {
            imageView.setBackgroundColor(view.getResources().getColor(R.color.blue));
            return view.getResources().getString(R.string.blue_Hexa);
        } else if (ColorNum == 2) {
            imageView.setBackgroundColor(view.getResources().getColor(R.color.green));
            return view.getResources().getString(R.string.green_Hexa);
        } else if (ColorNum == 3) {
            imageView.setBackgroundColor(view.getResources().getColor(R.color.yellow));
            return view.getResources().getString(R.string.yellow_Hexa);
        } else if (ColorNum == 4) {
            imageView.setBackgroundColor(view.getResources().getColor(R.color.orange));
            return view.getResources().getString(R.string.orange_Hexa);
        } else if (ColorNum == 5) {
            imageView.setBackgroundColor(view.getResources().getColor(R.color.purple));
            return view.getResources().getString(R.string.purple_Hexa);
        } else if (ColorNum == 6) {
            imageView.setBackgroundColor(view.getResources().getColor(R.color.pink));
            return view.getResources().getString(R.string.pink_Hexa);
        } else if (ColorNum == 7) {
            imageView.setBackgroundColor(view.getResources().getColor(R.color.skyblue));
            return view.getResources().getString(R.string.skyblue_Hexa);
        } else if (ColorNum == 8) {
            imageView.setBackgroundColor(view.getResources().getColor(R.color.cyan));
            return view.getResources().getString(R.string.cyan_Hexa);
        } else if (ColorNum == 9) {
            imageView.setBackgroundColor(view.getResources().getColor(R.color.magenta));
            return view.getResources().getString(R.string.magenta_Hexa);
        } else return null;
    }

    @Override
    public String ColorMixer(String First, String Second) {
        ArrayList<String> RightColor = new ArrayList<>();
        String rightColor = "";
        for (int i = 1; i <= 6; i++) {
            RightColor.add(Integer.toHexString(
                    (Integer.valueOf(String.valueOf(First.charAt(i)), 16)
                            + Integer.valueOf(String.valueOf(Second.charAt(i)), 16)) / 2));
        }

        for (int i = 0; i <= 5; i++) {
            rightColor += RightColor.get(i);
        }
        return "#" + rightColor;
    }

    @Override
    public String RandomColorMaker() {
        ArrayList<String> WrongColor = new ArrayList<>();
        String wrongColor = "";
        for (int i = 0; i <= 6; i++) {
            Integer wrong = random.getRandom_Overlap(16);
            WrongColor.add(Integer.toHexString(wrong));
        }
        for (int i = 0; i <= 5; i++) {
            wrongColor += WrongColor.get(i);
        }
        return "#" + wrongColor;
    }

    @Override
    public void GameClear() {

        scorer = new Scorer(model.getRightAnswerNum());
        grader = new Grader(scorer);

        String score = String.valueOf(scorer.ScoreAnswer());
        String grade = grader.getGrade(false, 18, 15, 13, 10, 5);
        String bestScore = BestScore().toString();

        if (DailyTrainingModel.getInstance().getIsTraining()) {
            DailyTrainingModel.getInstance().setGameNumber(5); // 다음 게임 번호
            MoveTo(DailyTrainingView.class, "TrainingResult", grade,4);
        } else {
            MoveToScore(score, grade, bestScore, view);
        }
    }

    @Override
    public Integer BestScore() {

        scorer = new Scorer(model.getRightAnswerNum());

        if (BestScoreDB.getInstance().getMixGameBest() == null) {
            BestScoreDB.getInstance().setMixGameBest(scorer.ScoreAnswer());
            return BestScoreDB.getInstance().getMixGameBest();
        } else {
            if (BestScoreDB.getInstance().getMixGameBest() < scorer.ScoreAnswer()) {
                return scorer.ScoreAnswer();
            } else
                return BestScoreDB.getInstance().getMixGameBest();
        }
    }

    @Override
    public void CheckAnswer(int num) {

        if (model.getArr()[0] == num) {
            model.setRightAnswerNum(model.getRightAnswerNum() + 1);
            ;
            RightAnswerNumber.setText("맞은 문제 : " + String.valueOf(model.getRightAnswerNum()) + " / 20");
            MakeQuestion();
            Effect.getInstance().RightAnswer_Beep(view);
        } else {
            Effect.getInstance().WrongAnswer_Beep(view);
            MakeQuestion();
        }

        if (model.getQnumber() > 20) {
            GameClear();
        }
    }
}
