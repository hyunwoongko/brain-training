package com.hyunwoong.braintraining.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.hyunwoong.braintraining.presenter.BasePresenter;
import com.hyunwoong.braintraining.utils.Action;
import com.hyunwoong.braintraining.receiver.ReceiverModel;
import com.hyunwoong.braintraining.view.PauseView;

public class DynamicReceiverService extends Service {

    private BroadcastReceiver receiver;
    private IntentFilter filter;
    private BasePresenter basePresenter;

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        basePresenter = new BasePresenter();

        if (ReceiverModel.getInstance().getRECEIVE_MODE().equals("BASE")) {
            DynamicReceiver(new Action() {
                @Override
                public void UserAction() {
                    basePresenter.BGM_Off(getApplicationContext());
                }
            }, getPhoneAction());
        }

        else if (ReceiverModel.getInstance().getRECEIVE_MODE().equals("GAME")) {
            DynamicReceiver(new Action() {
                @Override
                public void UserAction() {
                    basePresenter.MoveTo(getApplicationContext(), PauseView.class);
                }
            }, getPhoneAction(), getSMSAction());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Unregister();
    }

    public void DynamicReceiver(final Action actions, final String... Actions) {
        //여러가지 상황에 대해 모두 같은 액션을 취해야 할때
        // 클래스마다 액션을 정의해줄수 있음.
        filter = new IntentFilter();
        receiver = new BroadcastReceiver() {
            int aTime = 0;

            @Override
            public void onReceive(Context context, Intent intent) {
                aTime++;
                if (aTime == 1) {
                    String action = intent.getAction();
                    for (String Action : Actions) {
                        if (action.equals(Action)) {
                            actions.UserAction();
                        }
                    }
                }
            }
        };

        for (String Action : Actions) {
            filter.addAction(Action);
        }
        registerReceiver(receiver, filter);
    }

    public void Unregister() {
        Thread thread = new Thread();
        synchronized (thread) {
            try {
                unregisterReceiver(receiver);
            } catch (Exception e) {
            }
        }
    }

    public String getPhoneAction() {
        return android.telephony.TelephonyManager.ACTION_PHONE_STATE_CHANGED;
    }

    public String getSMSAction() {
        return "android.provider.Telephony.SMS_RECEIVED";
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
