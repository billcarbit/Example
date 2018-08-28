package com.example.wangning.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/1/5.
 */
public class RecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
    private OnLoadMoreListener mOnLoadMoreListener;

    public RecyclerViewOnScrollListener() {
        super();
    }

    public RecyclerViewOnScrollListener(OnLoadMoreListener listener) {
        super();
        mOnLoadMoreListener = listener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
            mOnLoadMoreListener.loadMore();
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }

    interface OnLoadMoreListener {
        void loadMore();
    }
}
