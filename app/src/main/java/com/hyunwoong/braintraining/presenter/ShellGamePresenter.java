package com.hyunwoong.braintraining.presenter;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.ShellGameContract;
import com.hyunwoong.braintraining.database.BestScoreDB;
import com.hyunwoong.braintraining.model.DailyTrainingModel;
import com.hyunwoong.braintraining.model.ShellGameModel;
import com.hyunwoong.braintraining.task.ShellGameTask;
import com.hyunwoong.braintraining.utils.Effect;
import com.hyunwoong.braintraining.utils.GetRandom;
import com.hyunwoong.braintraining.utils.Grader;
import com.hyunwoong.braintraining.utils.Scorer;
import com.hyunwoong.braintraining.view.DailyTrainingView;
import com.hyunwoong.braintraining.view.ShellGameView;

public class ShellGamePresenter extends BasePresenter implements ShellGameContract.presenter {

    private ImageView cup0;
    private ImageView cup1;
    private ImageView cup2;
    private Button swap_R;
    private ShellGameView view;
    private TextView Qnumber;
    private TextView RightAnswerNumber;

    private GetRandom random;
    private Scorer scorer;
    private Grader grader;
    private ShellGameTask task;


    ShellGameModel model;
    private boolean isCorrectionMode;
    private boolean TASKMODE;

    @Override
    public void SetView(ImageView cup0,
                        ImageView cup1,
                        ImageView cup2,
                        Button swap_R,
                        TextView Qnumber,
                        TextView RightAnswerNumber,
                        ShellGameView view) {

        this.cup0 = cup0;
        this.cup1 = cup1;
        this.cup2 = cup2;
        this.swap_R = swap_R;
        this.Qnumber = Qnumber;
        this.RightAnswerNumber = RightAnswerNumber;
        this.view = view;
        super.SetView(view);
    }

    @Override
    public void ClearView() {
        super.ClearView();
    }

    @Override
    public void Cup0() {
        cup0.setImageResource(R.drawable.braintrainer_shellgame);
        cup1.setImageResource(R.drawable.xsign);
        cup2.setImageResource(R.drawable.xsign);
        isCorrectionMode = false;
    }

    @Override
    public void Cup1() {
        cup0.setImageResource(R.drawable.xsign);
        cup1.setImageResource(R.drawable.braintrainer_shellgame);
        cup2.setImageResource(R.drawable.xsign);
        isCorrectionMode = false;
    }

    @Override
    public void Cup2() {
        cup0.setImageResource(R.drawable.xsign);
        cup1.setImageResource(R.drawable.xsign);
        cup2.setImageResource(R.drawable.braintrainer_shellgame);
        isCorrectionMode = false;
    }

    @Override
    public void CheckGameClear() {
        if (model.getQnumber() == 20) {
            GameClear();
        }
    }

    @Override
    public void Shellgame_init() {
        if (model == null) {
            model = new ShellGameModel();
            model.init();
        }

        TASKMODE = true;
        random = new GetRandom();
        model.getList().add(0, "X");
        model.getList().add(1, "Brainee"); // 1번 인덱스가 브레이니의 시작포인트
        model.getList().add(2, "X");
    }

    @Override
    public void Reset() {
        model.getList().set(0, "X");
        model.getList().set(1, "Brainee"); // 1번 인덱스가 브레이니의 시작포인트
        model.getList().set(2, "X");
        cup0.setImageResource(R.drawable.xsign);
        cup1.setImageResource(R.drawable.braintrainer_shellgame);
        cup2.setImageResource(R.drawable.xsign);
        isCorrectionMode = false;
    }

    @Override
    public void ShuffleLeft() {
        cup0.setImageResource(R.drawable.cups);
        cup1.setImageResource(R.drawable.cups);
        cup2.setImageResource(R.drawable.cups);

        String temp = new String();
        temp = model.getList().get(0);
        //0번은 잠깐 저장!

        model.getList().set(0, model.getList().get(1)); // 브레이니
        model.getList().set(1, model.getList().get(2)); // X
        model.getList().set(2, temp); // X

        // X B X   --->   B X X
        // 왼쪽으로 전체 1회전

        Animation anim_0 = AnimationUtils.loadAnimation(view, R.anim.leftend_to_rightend);
        Animation anim_1 = AnimationUtils.loadAnimation(view, R.anim.goleft);
        Animation anim_2 = AnimationUtils.loadAnimation(view, R.anim.goleft);

        cup0.startAnimation(anim_0);
        cup1.startAnimation(anim_1);
        cup2.startAnimation(anim_2);
        Effect.getInstance().Shuffle(view);
        temp = null;
    }

    @Override
    public void ShuffleRight() {
        cup0.setImageResource(R.drawable.cups);
        cup1.setImageResource(R.drawable.cups);
        cup2.setImageResource(R.drawable.cups);

        String temp = new String();
        temp = model.getList().get(2);
        //2번은 잠깐 저장!

        model.getList().set(2, model.getList().get(1));
        model.getList().set(1, model.getList().get(0));
        model.getList().set(0, temp);

        // X B X   --->   X X B
        // 오른쪽으로 전체 1회전
        Animation anim_2 = AnimationUtils.loadAnimation(view, R.anim.rightend_to_leftend);
        Animation anim_1 = AnimationUtils.loadAnimation(view, R.anim.goright);
        Animation anim_0 = AnimationUtils.loadAnimation(view, R.anim.goright);

        cup0.startAnimation(anim_0);
        cup1.startAnimation(anim_1);
        cup2.startAnimation(anim_2);
        Effect.getInstance().Shuffle(view);
        temp = null;
    }

    @Override
    public void SwapRight() {

        cup0.setImageResource(R.drawable.cups);
        cup1.setImageResource(R.drawable.cups);
        cup2.setImageResource(R.drawable.cups);

        String temp = new String();
        temp = model.getList().get(2);
        //2번은 잠깐 저장!

        model.getList().set(2, model.getList().get(1));
        model.getList().set(1, temp);

        //  B X   --->   X B
        // 오른쪽 두개 스왑

        Animation anim_1 = AnimationUtils.loadAnimation(view, R.anim.goright);
        Animation anim_2 = AnimationUtils.loadAnimation(view, R.anim.goleft);

        cup1.startAnimation(anim_1);
        cup2.startAnimation(anim_2);
        Effect.getInstance().Shuffle(view);
        temp = null;
    }

    @Override
    public void SwapLeft() {

        cup0.setImageResource(R.drawable.cups);
        cup1.setImageResource(R.drawable.cups);
        cup2.setImageResource(R.drawable.cups);

        String temp = new String();
        temp = model.getList().get(1);
        //2번은 잠깐 저장!

        model.getList().set(1, model.getList().get(0));
        model.getList().set(0, temp);

        //  B X   --->   X B
        // 왼쪽 두개 스왑

        Animation anim_0 = AnimationUtils.loadAnimation(view, R.anim.goright);
        Animation anim_1 = AnimationUtils.loadAnimation(view, R.anim.goleft);

        cup0.startAnimation(anim_0);
        cup1.startAnimation(anim_1);
        Effect.getInstance().Shuffle(view);
        temp = null;
    }

    @Override
    public void MakeQuestion() {
        if (TASKMODE && !isCorrectionMode) {
            task = new ShellGameTask(this);
            task.execute();

            isCorrectionMode = true;
            model.setQnumber(model.getQnumber() + 1);
            Qnumber.setText("현재 문제 : " + model.getQnumber());
        }
    }

    @Override
    public void GameClear() {

        scorer = new Scorer(model.getRightAnswerNumber());
        grader = new Grader(scorer);

        String score = String.valueOf(scorer.ScoreAnswer());
        String grade = grader.getGrade(false, 18, 15, 13, 10, 5);
        String bestScore = BestScore().toString();

        if (DailyTrainingModel.getInstance().getIsTraining()) {
            DailyTrainingModel.getInstance().setGameNumber(6); // 다음 게임 번호
            MoveTo(DailyTrainingView.class, "TrainingResult", grade, 5);
        } else {
            MoveToScore(score, grade, bestScore, view);
        }
    }

    @Override
    public Integer BestScore() {

        scorer = new Scorer(model.getRightAnswerNumber());

        if (BestScoreDB.getInstance().getShellGameBest() == null) {
            BestScoreDB.getInstance().setShellGameBest(scorer.ScoreAnswer());
            return BestScoreDB.getInstance().getShellGameBest();
        } else {
            if (BestScoreDB.getInstance().getShellGameBest() < scorer.ScoreAnswer()) {
                return scorer.ScoreAnswer();
            } else
                return BestScoreDB.getInstance().getShellGameBest();
        }
    }

    @Override
    public void Cup0Clicked() {
        if (isCorrectionMode) {
            if (model.getList().get(0) == "Brainee") {
                Cup0();
                Toast("정답입니다.");
                model.setRightAnswerNumber(model.getRightAnswerNumber() + 1);
                RightAnswerNumber.setText("맞은 문제 : " + model.getRightAnswerNumber() + " / 20");
                Effect.getInstance().RightAnswer_Beep(view);
                CheckGameClear();
            } else if (model.getList().get(1) == "Brainee") {
                Cup1();
                Effect.getInstance().WrongAnswer_Beep(view);
                CheckGameClear();
            } else if (model.getList().get(2) == "Brainee") {
                Cup2();
                Effect.getInstance().WrongAnswer_Beep(view);
                CheckGameClear();
            }

        }
    }

    @Override
    public void Cup1Clicked() {
        if (isCorrectionMode) {
            if (model.getList().get(0) == "Brainee") {
                Cup0();
                Effect.getInstance().WrongAnswer_Beep(view);
                CheckGameClear();
                model.setCorrectionMode(false);
            } else if (model.getList().get(1) == "Brainee") {
                Cup1();
                Toast("정답입니다.");
                model.setRightAnswerNumber(model.getRightAnswerNumber() + 1);
                RightAnswerNumber.setText("맞은 문제 : " + model.getRightAnswerNumber() + " / 20");
                Effect.getInstance().RightAnswer_Beep(view);
                CheckGameClear();
                model.setCorrectionMode(false);
            } else if (model.getList().get(2) == "Brainee") {
                Cup2();
                Effect.getInstance().WrongAnswer_Beep(view);
                CheckGameClear();
                model.setCorrectionMode(false);
            }
        }
    }

    @Override
    public void Cup2Clicked() {
        if (isCorrectionMode) {
            if (model.getList().get(0) == "Brainee") {
                Cup0();
                Effect.getInstance().WrongAnswer_Beep(view);
                CheckGameClear();
                model.setCorrectionMode(false);
            } else if (model.getList().get(1) == "Brainee") {
                Cup1();
                Effect.getInstance().WrongAnswer_Beep(view);
                CheckGameClear();
                model.setCorrectionMode(false);
            } else if (model.getList().get(2) == "Brainee") {
                Cup2();
                Toast("정답입니다.");
                model.setRightAnswerNumber(model.getRightAnswerNumber() + 1);
                RightAnswerNumber.setText("맞은 문제 : " + model.getRightAnswerNumber() + " / 20");
                Effect.getInstance().RightAnswer_Beep(view);
                CheckGameClear();
                model.setCorrectionMode(false);
            }
        }
    }

    @Override
    public int TaskTimes() {
        return model.getQnumber();
    }

    @Override
    public void StopTask() {
        if (task != null) {
            TASKMODE = false;
            task.cancel(true);
        }
    }

}
