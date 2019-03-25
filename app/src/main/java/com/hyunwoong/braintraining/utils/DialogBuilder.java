package com.hyunwoong.braintraining.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DialogBuilder {

    private static volatile DialogBuilder instance = null;

    private DialogBuilder() {
    }

    public static DialogBuilder getInstance() {
        synchronized (DialogBuilder.class) {
            try {
                if (instance == null) {
                    instance = new DialogBuilder();
                } else
                    return instance;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void MakeDialog(String title, String text, Context context, final Action action) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        action.UserAction();
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
}
