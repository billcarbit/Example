package com.example.wangning.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/10.
 */
public class RvActivity extends Activity {
    RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<String>();
    private RvAdapter mRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        addData();
        mRecyclerViewAdapter = new RvAdapter(this, mList);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // 绑定recyclerView
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    private void addData() {
        for (int i = 0; i < 5; i++) {
            mList.add("i=" + i);
        }
    }

}
