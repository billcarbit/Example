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
    ListView mLv;
    TextView mTv;

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
            datas.add("A" + i);
        }
        mLv.setAdapter(new ListViewAdapter(this, datas));


        final CountDownModel countDownModel = new CountDownModel();
        countDownModel.setTotalSec(10);
        countDownModel.start(new CountDownModel.OnTimeChangeListener() {
            @Override
            public void onCountDown(int totalSec) {
                if (totalSec < 0) {
                    countDownModel.stop();
                }
                mTv.setText(countDownModel.secToTime(totalSec));
            }

            @Override
            public void onCountDownStart(int totalSec) {
                mTv.setText(countDownModel.secToTime(totalSec));
            }
        });

    }


}
