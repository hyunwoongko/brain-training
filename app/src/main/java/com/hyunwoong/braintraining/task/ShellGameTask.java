package com.hyunwoong.braintraining.task;

import android.os.AsyncTask;

import com.hyunwoong.braintraining.presenter.ShellGamePresenter;
import com.hyunwoong.braintraining.utils.GetRandom;

public class ShellGameTask extends AsyncTask<String, Integer, Void> {

    private Thread thread = new Thread();
    private GetRandom random = new GetRandom();
    ShellGamePresenter mPresenter;
    int randomNum;

    public ShellGameTask(ShellGamePresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    protected Void doInBackground(String... params) {


        for (int i = 1; i <= mPresenter.TaskTimes(); i++) {
            if (!isCancelled()) {
                publishProgress(i);
                synchronized (thread) {
                    try {
                        thread.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (isCancelled()) {
                    break;
                }
            }
        }

        return null;
    }

    public void Shuffle(int Action) {
        if (Action == 0) {
            mPresenter.SwapLeft();
        } else if (Action == 1) {
            mPresenter.SwapRight();
        } else if (Action == 2) {
            mPresenter.ShuffleLeft();
        } else if (Action == 3) {
            mPresenter.ShuffleRight();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        randomNum = random.getRandom_Overlap(4);
        Shuffle(randomNum);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

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
