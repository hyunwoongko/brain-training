package com.hyunwoong.braintraining.contract;

import android.app.Activity;
import android.content.Context;
import android.widget.Chronometer;
import android.widget.TextView;

import com.hyunwoong.braintraining.task.CountDownTask;
import com.hyunwoong.braintraining.view.BaseView;

public interface BaseContract {

    interface view {
    }

    interface presenter {
        void SetView(BaseView view);
        void ClearView();
        // View

        void SetModel();
        void ClearModel();
        void InitModel();
        // Model

        void FullScreen();
        //FullScreen

        void BGM_On(Context context);
        void BGM_Off(Context context);
        void SetBGM(String name);
        void Button_Beep();
        // Sound

        void MoveTo(Class<? extends Activity> activity);
        void MoveToScore(String score, String grade, String bestScore, Context context);
        void MoveTo(Class<? extends Activity> activity, Boolean DailyTraining);
        void MoveTo(Class<? extends Activity> activity, String IntentName, String value);
        void MoveTo(Class<? extends Activity> activity, String IntentName, String value, int requestCode);
        void MoveTo(Context context, Class<? extends Activity> activity);
        void Toast(String msg);
        void Toast(String msg , Context context);
        void Exit();
        void FCM_AutoInit();
        //Utils

        int getColor(int resid);
        CountDownTask CountDown(TextView ready, Context context);
        CountDownTask CountDown(Chronometer chronometer, TextView ready, Context context, long pauseoffset);
        void GameClear();
        Integer BestScore();
        void StopCountDownTask(CountDownTask task);
        String getString(int resid);
        void GameBackButtonAction();
        //game

        void ReceiveOn(Context context);
        void ReceiveOff(Context context);
        void setReceiveMode(String str);
        //receiver
    }
}
