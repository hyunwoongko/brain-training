package com.hyunwoong.braintraining.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.TextView;

import com.hyunwoong.braintraining.contract.BaseContract;
import com.hyunwoong.braintraining.receiver.ReceiverModel;
import com.hyunwoong.braintraining.service.BGMService;
import com.hyunwoong.braintraining.service.BGMmodel;
import com.hyunwoong.braintraining.service.DynamicReceiverService;
import com.hyunwoong.braintraining.task.CountDownTask;
import com.hyunwoong.braintraining.utils.Effect;
import com.hyunwoong.braintraining.view.BaseView;
import com.hyunwoong.braintraining.view.MainView;
import com.hyunwoong.braintraining.view.ScoreView;
import com.google.firebase.messaging.FirebaseMessaging;

public class BasePresenter implements BaseContract.presenter {
    //Util Class

    private BaseView view;
    // View

    @Override
    public void SetView(BaseView view) {
        this.view = view;
    }

    @Override
    public void ClearView() {
        this.view = null;
    }

    @Override
    public void SetModel() {
    }

    @Override
    public void ClearModel() {
    }

    @Override
    public void InitModel() {

    }

    @Override
    public void BGM_On(Context context) {
        Intent intent = new Intent(context, BGMService.class);
        context.startService(intent);
    }

    @Override
    public void BGM_Off(Context context) {
        Intent intent = new Intent(context, BGMService.class);
        context.stopService(intent);
    }

    @Override
    public void Button_Beep() {
        Effect.getInstance().Button_Beep(view);
    }

    @Override
    public void SetBGM(String name) {
        BGMmodel.getInstance().setBgmName(name);
    }
    // 각 클래스마다 Resume에서 어떤 BGM을 사용할것인지 정해주면 됨.

    @Override
    public void FullScreen() {
        view.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        view.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void MoveTo(Class<? extends Activity> activity) {
        Intent intent = new Intent(view, activity);
        view.startActivity(intent);
    }

    @Override
    public void MoveTo(Context context, Class<? extends Activity> activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    @Override
    public void MoveTo(Class<? extends Activity> activity, String IntentName, String value, int requestCode) {
        Intent intent = new Intent(view, activity);
        intent.putExtra(IntentName, value);
        view.setResult(requestCode, intent);
        view.finish();
    }

    @Override
    public void MoveTo(Class<? extends Activity> activity, String IntentName, String value) {
        Intent intent = new Intent(view, activity);
        intent.putExtra(IntentName, value);
        view.startActivity(intent);
    }

    @Override
    public void MoveTo(Class<? extends Activity> activity, Boolean DailyTraining) {
        Intent intent = new Intent(view, activity);
        view.startActivity(intent);
    }

    @Override
    public void MoveToScore(String score, String grade, String bestScore, Context context) {
        Intent intent = new Intent(context, ScoreView.class);
        intent.putExtra("score", score);
        intent.putExtra("grade", grade);
        intent.putExtra("bestScore", bestScore);
        context.startActivity(intent);
    }

    @Override
    public void Toast(String msg) {
        android.widget.Toast.makeText(view, msg, android.widget.Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Toast(String msg , Context context) {
        android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Exit() {
        view.finish(); // 액티비티 종료
        System.exit(0); // 시스템 종료
        android.os.Process.killProcess(android.os.Process.myPid()); // 프로세스 킬
    }

    @Override
    public CountDownTask CountDown(Chronometer chronometer, TextView ready, Context context, long pauseoffset) {
        CountDownTask countDownTask = new CountDownTask(chronometer, ready, context, pauseoffset);
        countDownTask.execute();
        return countDownTask;
    }

    @Override
    public void GameClear() {
    }

    @Override
    public Integer BestScore() {
        return null;
    }

    @Override
    public CountDownTask CountDown(TextView ready, Context context) {
        CountDownTask countDownTask = new CountDownTask(ready, context);
        countDownTask.execute();
        return countDownTask;
    }

    @Override
    public void StopCountDownTask(CountDownTask task) {
        task.cancel(true);
    }

    @Override
    public String getString(int resid) {
        return view.getResources().getString(resid);
    }

    @Override
    public int getColor(int resid) {
        return view.getResources().getColor(resid);
    }

    @Override
    public void FCM_AutoInit() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }

    @Override
    public void GameBackButtonAction() {
        new AlertDialog.Builder(view)
                .setTitle("게임 종료")
                .setMessage("게임중에 나가시면 지금까지 얻은 게임 데이터를 잃습니다." + "\n" +
                        "그래도 나가시겠습니까?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MoveTo(MainView.class);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setCancelable(true)
                .show();
    }

    @Override
    public void ReceiveOn(Context context) {
        Intent intent = new Intent(context, DynamicReceiverService.class);
        context.startService(intent);
    }

    @Override
    public void ReceiveOff(Context context) {
        Intent intent = new Intent(context, DynamicReceiverService.class);
        context.stopService(intent);
    }

    @Override
    public void setReceiveMode(String str) {
        ReceiverModel.getInstance().setRECEIVE_MODE(str);
    }
}