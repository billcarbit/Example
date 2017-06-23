package com.example.wangning.countdowntimer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
public class CountDownTimerActivity extends Activity {
    private ListView mLv;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        initView();
        initData();
    }

    private void initView() {
        mLv = (ListView) findViewById(R.id.lv);
        mTv = (TextView) findViewById(R.id.tv);
    }

    private void initData() {
        List<String> datas = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            datas.add("倒计时" + i);
        }
        mLv.setAdapter(new ListViewAdapter(this, datas));
        startCountDown(100);
    }

    private void startCountDown(int totalSec){
        final CountDownTimer countDownTimer = new CountDownTimer();
        countDownTimer.setTotalSec(totalSec);
        countDownTimer.start(new CountDownTimer.OnTimeChangeListener() {
            @Override
            public void onCountDown(int totalSec) {
                if (totalSec < 0) {
                    countDownTimer.stop();
                }
                mTv.setText(countDownTimer.secToTime(totalSec));
            }

            @Override
            public void onCountDownStart(int totalSec) {
                mTv.setText(countDownTimer.secToTime(totalSec));
            }
        });
    }


}
