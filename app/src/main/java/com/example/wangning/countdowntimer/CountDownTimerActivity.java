package com.example.wangning.countdowntimer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
public class CountDownTimerActivity extends Activity {
    ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        initView();
        initData();
    }

    private void initView() {
        mLv = (ListView) findViewById(R.id.lv);
    }

    private void initData() {
        List<String> datas = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            datas.add("A" + i);
        }
        mLv.setAdapter(new ListViewAdapter(this,datas));
    }

}
