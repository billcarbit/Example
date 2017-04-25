package com.example.wangning.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<String> mDataList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RecyclerViewAdapter(this, mDataList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData(){
        for(int i=0;i<10;i++){
            mDataList.add("第"+(i+1)+"条数据");
        }
    }

}
