package com.hyunwoong.braintraining.task;


import android.os.AsyncTask;
import android.widget.TextView;

import com.hyunwoong.braintraining.presenter.WordGamePresenter;

public class WordGameTask extends AsyncTask<String, Integer, Void> {
    private TextView ready;
    private TextView word;
    Boolean isCorrectionMode;

    private Thread thread = new Thread();
    WordGamePresenter mPresenter;

    public WordGameTask(TextView ready, TextView word , WordGamePresenter mPresenter) {

        this.ready = ready;
        this.word = word;
        this.isCorrectionMode = isCorrectionMode;
        this.mPresenter = mPresenter;
    }

    @Override
    protected Void doInBackground(String... params) {

        for (int i = 120; i >= 1; i--) {
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
        word.setText("단어들을 하나씩 적어주세요 !");
        mPresenter.Hide();
        mPresenter.Build();
        mPresenter.Delete();

        //카운트다운 텍스트뷰 제거

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mPresenter.MakeTextField();
        word.setText("아래 단어들을 외워주세요 !");

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
