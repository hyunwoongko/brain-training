package com.hyunwoong.braintraining.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.DailyTrainingContract;
import com.hyunwoong.braintraining.model.DailyTrainingModel;
import com.hyunwoong.braintraining.presenter.DailyTrainingPresenter;
import com.hyunwoong.braintraining.utils.PreferenceHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class DailyTrainingView extends BaseView implements DailyTrainingContract.view{

    private DailyTrainingPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DailyTrainingPresenter();

        setContentView(R.layout.view_dailytraining);
        Button StartDaily = findViewById(R.id.dailyStartButton);
        TextView DailyEnd = findViewById(R.id.dailyEnd);
        TextView Cal = findViewById(R.id.CalScore);
        TextView Cou = findViewById(R.id.CouScore);
        TextView Mom = findViewById(R.id.MomScore);
        TextView Col = findViewById(R.id.ColScore);
        TextView Mix = findViewById(R.id.MixScore);
        TextView She = findViewById(R.id.SheScore);
        TextView Wor = findViewById(R.id.WorScore);
        TextView Lab = findViewById(R.id.LabScore);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(mPresenter.getStartDate(), mPresenter.getEndDate())
                .datesNumberOnScreen(5)
                .build();

        mPresenter.SetView(StartDaily,DailyEnd,Cal,Cou,Mom,Col,Mix,She,Wor,Lab,horizontalCalendar,this);

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                mPresenter.CalenderViewBuild();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setToday();
        mPresenter.SetBGM("DailyBGM");
        mPresenter.CheckDaily();
    }
    @Override
    public void onDailyStartButtonClicked(View view) {
        mPresenter.DailyTrainingStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.setDailyGrade(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        mPresenter.BackButtonAction();
    }
}
