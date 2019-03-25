package com.hyunwoong.braintraining.presenter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hyunwoong.braintraining.contract.DailyTrainingContract;
import com.hyunwoong.braintraining.model.DailyTrainingModel;
import com.hyunwoong.braintraining.utils.Action;
import com.hyunwoong.braintraining.utils.DialogBuilder;
import com.hyunwoong.braintraining.utils.PreferenceHelper;
import com.hyunwoong.braintraining.view.CalculateGameView;
import com.hyunwoong.braintraining.view.ColorGameView;
import com.hyunwoong.braintraining.view.CountGameView;
import com.hyunwoong.braintraining.view.DailyTrainingView;
import com.hyunwoong.braintraining.view.LabialGameView;
import com.hyunwoong.braintraining.view.MainView;
import com.hyunwoong.braintraining.view.MixGameView;
import com.hyunwoong.braintraining.view.MomentGameView;
import com.hyunwoong.braintraining.view.ShellGameView;
import com.hyunwoong.braintraining.view.WordGameView;

import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;

public class DailyTrainingPresenter extends BasePresenter implements DailyTrainingContract.presenter {

    private Button StartDaily;
    private TextView DailyEnd;
    private TextView Cal, Cou, Mom, Col, Mix, She, Wor, Lab;
    private HorizontalCalendar horizontalCalendar;
    private DailyTrainingView view;

    @Override
    public void SetView(
            Button StartDaily,
            TextView DailyEnd,
            TextView Cal,
            TextView Cou,
            TextView Mom,
            TextView Col,
            TextView Mix,
            TextView She,
            TextView Wor,
            TextView Lab,
            HorizontalCalendar horizontalCalendar,
            DailyTrainingView view) {

        this.StartDaily = StartDaily;
        this.DailyEnd = DailyEnd;
        this.Cal = Cal;
        this.Cou = Cou;
        this.Mom = Mom;
        this.Col = Col;
        this.Mix = Mix;
        this.She = She;
        this.Wor = Wor;
        this.Lab = Lab;
        this.horizontalCalendar = horizontalCalendar;
        this.view = view;
        super.SetView(view);
    }

    @Override
    public void ClearView() {
        super.ClearView();
    }

    @Override
    public void SetModel() {
        super.SetModel();
    }

    @Override
    public void ClearModel() {
        super.ClearModel();
    }

    @Override
    public void CalenderInit() {
        DailyTrainingModel.getInstance().init();
    }

    @Override
    public void CalenderViewBuild() {
        Calendar cal = horizontalCalendar.getSelectedDate();
        Date c = new Date(cal.getTimeInMillis());
        DailyTrainingModel.getInstance().setFormatSelectDate(DailyTrainingModel.getInstance().getSdfNow().format(c));

        String DailyScore = PreferenceHelper.getInstance(view)
                .getString(DailyTrainingModel.getInstance().getFormatSelectDate(), "");

        if (DailyScore.length() == 0) {
            Cal.setText("기록 없음");
            Cou.setText("기록 없음");
            Mom.setText("기록 없음");
            Col.setText("기록 없음");
            Mix.setText("기록 없음");
            She.setText("기록 없음");
            Wor.setText("기록 없음");
            Lab.setText("기록 없음");
        } else {
            Cal.setText(DailyScore.charAt(0) + " 등급");
            Cou.setText(DailyScore.charAt(1) + " 등급");
            Mom.setText(DailyScore.charAt(2) + " 등급");
            Col.setText(DailyScore.charAt(3) + " 등급");
            Mix.setText(DailyScore.charAt(4) + " 등급");
            She.setText(DailyScore.charAt(5) + " 등급");
            Wor.setText(DailyScore.charAt(6) + " 등급");
            Lab.setText(DailyScore.charAt(7) + " 등급");
        }
    }

    @Override
    public Calendar getStartDate() {
        CalenderInit();
        return DailyTrainingModel.getInstance().getStartDate();
    }

    @Override
    public Calendar getEndDate() {
        CalenderInit();
        return DailyTrainingModel.getInstance().getEndDate();
    }

    @Override
    public void MoveDailyGame(int RequestCode, Class<? extends Activity> activity) {
        Intent intent = new Intent(view, activity);
        view.startActivityForResult(intent, RequestCode);
    }

    @Override
    public void DailyTrainingStart() {
        DailyTrainingModel.getInstance().setIsTraining(true);
        if (DailyTrainingModel.getInstance().getGameNumber() == 0)
            MoveDailyGame(0, CalculateGameView.class); // 시작 전 상태

        else if (DailyTrainingModel.getInstance().getGameNumber() == 1)
            MoveDailyGame(1, CountGameView.class);

        else if (DailyTrainingModel.getInstance().getGameNumber() == 2)
            MoveDailyGame(2, MomentGameView.class);

        else if (DailyTrainingModel.getInstance().getGameNumber() == 3)
            MoveDailyGame(3, ColorGameView.class);

        else if (DailyTrainingModel.getInstance().getGameNumber() == 4)
            MoveDailyGame(4, MixGameView.class);

        else if (DailyTrainingModel.getInstance().getGameNumber() == 5)
            MoveDailyGame(5, ShellGameView.class);

        else if (DailyTrainingModel.getInstance().getGameNumber() == 6)
            MoveDailyGame(6, WordGameView.class);

        else if (DailyTrainingModel.getInstance().getGameNumber() == 7) {
            MoveDailyGame(7, LabialGameView.class);
        }
    }

    @Override
    public void setDailyGrade(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            Cal.setText(data.getStringExtra("TrainingResult") + " 등급");
            DailyTrainingModel.getInstance().setCals(data.getStringExtra("TrainingResult"));
        } else if (requestCode == 1) {
            Cou.setText(data.getStringExtra("TrainingResult") + " 등급");
            DailyTrainingModel.getInstance().setCous(data.getStringExtra("TrainingResult"));
        } else if (requestCode == 2) {
            Mom.setText(data.getStringExtra("TrainingResult") + " 등급");
            DailyTrainingModel.getInstance().setMoms(data.getStringExtra("TrainingResult"));
        } else if (requestCode == 3) {
            Col.setText(data.getStringExtra("TrainingResult") + " 등급");
            DailyTrainingModel.getInstance().setCols(data.getStringExtra("TrainingResult"));
        } else if (requestCode == 4) {
            Mix.setText(data.getStringExtra("TrainingResult") + " 등급");
            DailyTrainingModel.getInstance().setMixs(data.getStringExtra("TrainingResult"));
        } else if (requestCode == 5) {
            She.setText(data.getStringExtra("TrainingResult") + " 등급");
            DailyTrainingModel.getInstance().setShes(data.getStringExtra("TrainingResult"));
        } else if (requestCode == 6) {
            Wor.setText(data.getStringExtra("TrainingResult") + " 등급");
            DailyTrainingModel.getInstance().setWors(data.getStringExtra("TrainingResult"));
        } else if (requestCode == 7) {
            Lab.setText(data.getStringExtra("TrainingResult") + " 등급");
            DailyTrainingModel.getInstance().setLabs(data.getStringExtra("TrainingResult"));
            DailyTrainingModel.getInstance().setIsTraining(false);
            DailyTrainingModel.getInstance().setGameNumber(0);
            PreferenceHelper.getInstance(view).setString(String.valueOf(DailyTrainingModel.getInstance().getFormatTodayDate()),
                    DailyTrainingModel.getInstance().getCals() +
                            DailyTrainingModel.getInstance().getCous() +
                            DailyTrainingModel.getInstance().getMoms() +
                            DailyTrainingModel.getInstance().getCols() +
                            DailyTrainingModel.getInstance().getMixs() +
                            DailyTrainingModel.getInstance().getShes() +
                            DailyTrainingModel.getInstance().getWors() +
                            DailyTrainingModel.getInstance().getLabs());
        }
    }

    @Override
    public void BackButtonAction() {

        DialogBuilder.getInstance().MakeDialog("데일리 트레이닝 종료",
                "데일리 트레이닝 중에 나가시면 지금까지 얻은 게임 데이터를 모두 잃습니다." + "\n" +
                        "그래도 나가시겠습니까?", view,
                new Action() {
                    @Override
                    public void UserAction() {
                        MoveTo(MainView.class);
                        DailyTrainingModel.getInstance().setIsTraining(false);
                        DailyTrainingModel.getInstance().setGameNumber(0);
                    }
                });
    }

    @Override
    public void setToday() {
        DailyTrainingModel.getInstance().setToday(PreferenceHelper.getInstance(view)
                .getString(DailyTrainingModel.getInstance().getFormatTodayDate(), ""));
    }

    @Override
    public void CheckDaily() {
        if (DailyTrainingModel.getInstance().getToday().length() == 0) {
            StartDaily.setVisibility(View.VISIBLE);
            DailyEnd.setVisibility(View.GONE);
            if (DailyTrainingModel.getInstance().getIsTraining()) {
                StartDaily.setText("다음 게임 진행하기 !");
            } else {
                StartDaily.setText("매일 트레이닝 시작!");
            }
        } else {
            StartDaily.setVisibility(View.GONE);
            DailyEnd.setVisibility(View.VISIBLE);
        }
    }
}
