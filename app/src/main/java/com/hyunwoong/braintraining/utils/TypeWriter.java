package com.hyunwoong.braintraining.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class TypeWriter extends TextView {


    private CharSequence mText;
    private int mIndex;
    private long mDelay = 100; // in ms
    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));
            if (mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }

    }; // 핸들러를 이용해 글씨를 차례대로 적어줌.

    public TypeWriter(Context context) {
        super(context);
    }
    public TypeWriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void animateText(CharSequence txt) {

        mText = txt;
        mIndex = 0;

        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    public void setCharacterDelay(long m) {
        mDelay = m;
    }
}