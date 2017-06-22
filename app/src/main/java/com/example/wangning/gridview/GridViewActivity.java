package com.example.wangning.gridview;

import android.app.Activity;
import android.os.Bundle;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */
public class GridViewActivity extends Activity {
    LastRowCenterGridView mGv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        initView();
        initData();
    }

    void initView() {
        mGv = (LastRowCenterGridView) findViewById(R.id.gv);

    }

    void initData() {
        List<String> stringList = new ArrayList<String>();
        for (int i = 0; i < 2; i++) {
            stringList.add("B" + i);
        }
        mGv.setDataSource(stringList);
        mGv.createView();
    }
}
