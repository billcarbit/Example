package com.example.wangning.countdowntimer;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 倒计时业务代码
 *
 * @author wangning
 * @version 1.0 2017-05-10
 * @since JDK 1.8
 */
public class CountDownTimer {
    private int mTotalSec;//总秒数
    private OnTimeChangeListener mOnTimeChangeListener;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mHandler.sendEmptyMessageDelayed(1, 1000);
                    int remainderSec = mTotalSec - 1;
                    mOnTimeChangeListener.onCountDown(remainderSec);
                    mTotalSec--;
                    break;
                default:
                    break;
            }
        }
    };

    public void setTotalSec(int sec) {
        mTotalSec = sec;
    }

    public String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    private String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public void start(OnTimeChangeListener listener) {
        mOnTimeChangeListener = listener;
        mOnTimeChangeListener.onCountDownStart(mTotalSec);
        stop();
        mHandler.sendEmptyMessageDelayed(1,1000);
    }

    public void stop() {
        mHandler.removeMessages(1);
    }

    interface OnTimeChangeListener {
        void onCountDownStart(int totalSec);
        void onCountDown(int totalSec);
    }
}
