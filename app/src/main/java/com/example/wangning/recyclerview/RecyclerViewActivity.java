package com.example.wangning.recyclerview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class RecyclerViewActivity extends Activity implements RecyclerViewOnScrollListener.OnLoadMoreListener{
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        addData();
        mRecyclerViewAdapter = new RecyclerViewAdapter(this,mList);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.addOnScrollListener(new RecyclerViewOnScrollListener(this));
        // 绑定recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration2  ddd= new DividerItemDecoration2(this,
                DividerItemDecoration.VERTICAL_LIST,new ColorDrawable(Color.parseColor("#dddddd")));
        ddd.setHeight(10);
        ddd.setWidth(10);
        ddd.setDividerRight(30);
        mRecyclerView.addItemDecoration(ddd);
    }

    private void addData(){
        for (int i = 0; i < 10; i++) {
            mList.add("i="+i);
        }
    }

    @Override
    public void loadMore() {
        addData();
        mRecyclerViewAdapter.notifyDataSetChanged();
    }
}
