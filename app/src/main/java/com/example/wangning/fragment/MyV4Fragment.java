package com.example.wangning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wangning.R;
import com.example.wangning.coordinator.Comment;
import com.example.wangning.coordinator.CommentAdapter;
import com.example.wangning.recyclerview.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */
public class MyV4Fragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Comment> mList = new ArrayList<Comment>();
    private CommentAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);

    }


    @Override
    public void onResume() {
        super.onResume();
        addData();
        initRecyclerView();
    }


    private void addData() {
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setContent("comment+" + i);
            List<Comment.Replay> replayList = new ArrayList();
            for (int j = 0; j < 3; j++) {
                Comment.Replay replay = new Comment.Replay();
                replay.setContent(comment.getContent() + ",replay+" + j);
                replayList.add(replay);
            }
            comment.setReplayList(replayList);
            mList.add(comment);
        }
    }


    private void initRecyclerView() {
        mAdapter = new CommentAdapter(getContext(), mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //mRecyclerView.addOnScrollListener(new RecyclerViewOnScrollListener(this));
        // 绑定recyclerView
        mRecyclerView.setAdapter(mAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration divider = new DividerItemDecoration();
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setNestedScrollingEnabled(false);
    }

}
