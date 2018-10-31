package com.example.wangning.coordinator;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.wangning.R;
import com.example.wangning.recyclerview.CommonItemDecoration;
import com.example.wangning.recyclerview.DividerItemDecoration;
import com.example.wangning.recyclerview.RecyclerViewOnScrollListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/31.
 */
public class CoordinatorLayoutActivity2 extends Activity
        implements RecyclerViewOnScrollListener.OnLoadMoreListener {

    private CommentAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<Comment> mList = new ArrayList<Comment>();
    private AppBarLayout mAppBarLayout;
    private SwipeRefreshLayout srl;
    private Handler handler = new Handler();
    private int mIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator3);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        srl = (SwipeRefreshLayout)findViewById(R.id.srl);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl.setRefreshing(false);
                        mIndex = 0;
                        mList.clear();
                        addData();
                        mAdapter.notifyDataSetChanged();

                    }
                }, 1000);
            }
        });
        dealScrollConflict();
        addData();
        initRecyclerView();
    }


    /**
     * 处理SwipeRefreshLayout嵌套CoordinatorLayout的下拉冲突
     */
    private void dealScrollConflict() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    srl.setEnabled(true);
                } else {
                    srl.setEnabled(false);
                }
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new CommentAdapter(this, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnScrollListener(new RecyclerViewOnScrollListener(this));
        // 绑定recyclerView
        mRecyclerView.setAdapter(mAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new CommonItemDecoration(0, 20));
    }

    private void addData() {
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setContent("comment+" + mIndex);
       /*     List<Comment.Replay> replayList = new ArrayList();
            for (int j = 0; j < 3; j++) {
                Comment.Replay replay = new Comment.Replay();
                replay.setContent(comment.getContent() + ",replay+" + j);
                replayList.add(replay);
            }
            comment.setReplayList(replayList);*/
            mIndex++;
            mList.add(comment);
        }
    }


    @Override
    public void loadMore() {
        addData();
    }
}
