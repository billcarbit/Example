package com.example.wangning.recyclerview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2016-12-28
 * @since JDK 1.8
 */
public class RecyclerViewActivity extends Activity implements RecyclerViewOnScrollListener.OnLoadMoreListener {
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        initView();
        initData();

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void initData() {
        addData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerViewAdapter = new RecyclerViewAdapter(this, mList);
        mRecyclerView.addOnScrollListener(new RecyclerViewOnScrollListener(this));
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this,2);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        // 绑定recyclerView
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        CommonItemDecoration divider = new CommonItemDecoration(20,20);
        mRecyclerView.addItemDecoration(divider);
    }

    private void addData() {
        for (int i = 0; i < 10; i++) {
            mList.add("i=" + i);
        }
    }

    @Override
    public void loadMore() {
        // addData();
        // mRecyclerViewAdapter.notifyDataSetChanged();
    }
}
