package com.hyunwoong.braintraining.contract;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hyunwoong.braintraining.view.DailyTrainingView;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;

public interface DailyTrainingContract extends BaseContract {

    interface view {
        void onDailyStartButtonClicked(View view);
    }

    interface presenter {
        void SetView(
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
                DailyTrainingView view);

        void CalenderInit();

        void CalenderViewBuild();

        Calendar getStartDate();

        Calendar getEndDate();

        void MoveDailyGame(int RequestCode, Class<? extends Activity> activity);

        void DailyTrainingStart();

        void setDailyGrade(int requestCode, int resultCode, Intent data);

        void BackButtonAction();

        void setToday();

        void CheckDaily();

    }
}
