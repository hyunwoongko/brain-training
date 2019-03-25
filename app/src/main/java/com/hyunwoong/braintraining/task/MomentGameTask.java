package com.hyunwoong.braintraining.task;


import android.os.AsyncTask;
import android.widget.TextView;

import com.hyunwoong.braintraining.presenter.MomentGamePresenter;

public class MomentGameTask extends AsyncTask<String, Integer, Void> {
    private TextView ready;
    private TextView moment;

    private Thread thread = new Thread();
    MomentGamePresenter mPresenter;

    public MomentGameTask(TextView ready, TextView moment, MomentGamePresenter mPresenter) {

        this.ready = ready;
        this.moment = moment;
        this.mPresenter = mPresenter;
    }


    @Override
    protected Void doInBackground(String... params) {

        for (int i = 10; i >= 1; i--) {
            if(!isCancelled()){
            publishProgress(i);
            synchronized (thread) {
                try {
                    thread.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(isCancelled()){
                    break;
                }
                }
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        ready.setText(String.valueOf(values[0]));
        // 카운트다운 텍스트
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ready.setText("Start !");
        moment.setText("순서대로 눌러주세요!");
        mPresenter.Hide();
        //카운트다운 텍스트뷰 제거

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mPresenter.MakeButton();
        moment.setText("아래 숫자를 외워주세요 !");
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
