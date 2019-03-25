package com.hyunwoong.braintraining.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyTrainingModel {
    // 데이터베이스 모델 제외 유일한 싱글톤 모델

    private volatile static DailyTrainingModel mInstance = null;

    private DailyTrainingModel() {

    }

    public static DailyTrainingModel getInstance() {
        if (mInstance == null) {
            synchronized (DailyTrainingModel.class) {
                if (mInstance == null) {
                    mInstance = new DailyTrainingModel();
                }
            }
        }
        return mInstance;
    } // Singleton

    private int gameNumber;
    private boolean isTraining;

    private String Cals, Cous, Moms, Cols, Mixs, Shes, Wors, Labs;
    private long now;
    private Date date;
    private SimpleDateFormat sdfNow;
    private String formatTodayDate;
    private String formatSelectDate;
    private Calendar endDate;
    private String Today;

    public String getToday() {
        return Today;
    }

    public void setToday(String today) {
        Today = today;
    }

    public boolean isTraining() {
        return isTraining;
    }

    public void setTraining(boolean training) {
        isTraining = training;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    private Calendar startDate;

    public void init() {
        endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        /* start before 1 month from now */
        startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        now = System.currentTimeMillis();
        date = new Date(now);
        sdfNow = new SimpleDateFormat("MMdd");
        formatTodayDate = sdfNow.format(date);
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public boolean getIsTraining() {
        return isTraining;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public void setIsTraining(boolean IsTraining) {
        this.isTraining = IsTraining;
    }

    public String getCals() {
        return Cals;
    }

    public String getCous() {
        return Cous;
    }

    public String getMoms() {
        return Moms;
    }

    public String getCols() {
        return Cols;
    }

    public String getMixs() {
        return Mixs;
    }

    public String getShes() {
        return Shes;
    }

    public String getWors() {
        return Wors;
    }

    public String getLabs() {
        return Labs;
    }

    public void setCals(String cals) {
        Cals = cals;
    }

    public void setCous(String cous) {
        Cous = cous;
    }

    public void setMoms(String moms) {
        Moms = moms;
    }

    public void setCols(String cols) {
        Cols = cols;
    }

    public void setMixs(String mixs) {
        Mixs = mixs;
    }

    public void setShes(String shes) {
        Shes = shes;
    }

    public void setLabs(String labs) {
        Labs = labs;
    }

    public void setWors(String wors) {
        Wors = wors;
    }

    public Date getDate() {
        return date;
    }

    public long getNow() {
        return now;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public SimpleDateFormat getSdfNow() {
        return sdfNow;
    }

    public void setSdfNow(SimpleDateFormat sdfNow) {
        this.sdfNow = sdfNow;
    }

    public String getFormatSelectDate() {
        return formatSelectDate;
    }

    public String getFormatTodayDate() {
        return formatTodayDate;
    }

    public void setFormatSelectDate(String formatSelectDate) {
        this.formatSelectDate = formatSelectDate;
    }

    public void setFormatTodayDate(String formatTodayDate) {
        this.formatTodayDate = formatTodayDate;
    }
}