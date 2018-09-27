package com.example.wangning.coordinator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.wangning.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator2);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        addData();

        initRecyclerView();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                boolean canDown = recyclerView.canScrollVertically(1);
                Log.e("AA", "onScrollStateChanged: canDown=" + canDown + ",newState=" + newState);
            }
        });
        mRecyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {

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
        DividerItemDecoration divider = new DividerItemDecoration();
        mRecyclerView.addItemDecoration(divider);
    }

    private void addData() {
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setContent("comment+" + i);
            List<Comment.Replay> replayList = new ArrayList();
            for (int j = 0; j < 3; j++) {
                Comment.Replay replay = new Comment.Replay();
                replay.setContent(comment.getContent()+",replay+" + j);
                replayList.add(replay);
            }
            comment.setReplayList(replayList);
            mList.add(comment);
        }
    }


    @Override
    public void loadMore() {

    }
}
