package com.example.wangning.countdowntimer;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/3.
 */
public class CountDownTimer {
    private static final String TAG = "CountDownTimer";
    private TextView mTv;
    private long mInitVal;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e(TAG, "handleMessage: CountDownTimer="+String.valueOf(mInitVal-1));
                    mTv.setText(String.valueOf(mInitVal-1));
                    mHandler.sendEmptyMessageDelayed(1, 1000);
                    break;
                default:
                    break;
            }
        }
    };

    public void start() {
        mHandler.sendEmptyMessage(1);
    }

    public void stop() {
        mHandler.removeMessages(1);
    }

    public CountDownTimer setTextView(TextView tv) {
        mTv = tv;
        return this;
    }

    public CountDownTimer setInitVal(long initVal) {
        mInitVal = initVal;
        return this;
    }
}
