package com.example.wangning.letterSearch;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wangning.R;
import com.example.wangning.recyclerview.DividerItemDecoration;
import com.example.wangning.recyclerview.DividerItemDecoration2;
import com.example.wangning.recyclerview.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 字母检索
 * Created by Administrator on 2018/4/23.
 */
public class LetterSearchActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RvAdapter mRvAdapter;
    private List<String> mList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_search);
        addData();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRvAdapter = new RvAdapter(this,mList);
        mRecyclerView.setAdapter(mRvAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration2 ddd = new DividerItemDecoration2(this,
                DividerItemDecoration.VERTICAL_LIST, new ColorDrawable(Color.parseColor("#dddddd")));
        ddd.setHeight(10);
        ddd.setWidth(10);
        ddd.setDividerRight(30);
        mRecyclerView.addItemDecoration(ddd);
    }

    private void addData() {
        for (int i = 0; i < 10; i++) {
            mList.add("i=" + i);
        }
    }

}
