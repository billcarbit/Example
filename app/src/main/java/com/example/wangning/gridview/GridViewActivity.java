package com.example.wangning.gridview;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

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
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        List<String> list = new ArrayList<String>();
        for(int i=0;i<7;i++){
            list.add("A"+i);
        }
        LastRowCenterGridView lastRowCenterGridView = (LastRowCenterGridView) findViewById(R.id.lastRowCenterGridView);
        lastRowCenterGridView.initData(list,3);
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
