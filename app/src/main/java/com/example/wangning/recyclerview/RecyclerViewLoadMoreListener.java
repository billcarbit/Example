package com.example.wangning.recyclerview;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2016-12-28
 * @since JDK 1.8
 */
public class RecyclerViewLoadMoreListener extends RecyclerView.OnScrollListener {
    private static final String TAG = "RecyclerView";
    //RecyclerView 列表管理器
    private final LinearLayoutManager linearLayoutManager;
    //加载更多回调接口
    private final OnLoadMoreListener onLoadMoreListener;
    //最小值 回调加载
    private final int limit;


    public RecyclerViewLoadMoreListener(LinearLayoutManager linearLayoutManager, OnLoadMoreListener onLoadMoreListener, int limit) {
        super();
        this.linearLayoutManager = linearLayoutManager;
        this.onLoadMoreListener = onLoadMoreListener;
        this.limit = limit;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        // 向下滑动，判断最后一个item是不是显示中
        if (linearLayoutManager.getItemCount() >= limit && linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.getItemCount() - 1) {
            Log.e(TAG, "onScrollStateChanged: newState=" + newState);
            Log.e(TAG, "onScrollStateChanged:linearLayoutManager.getItemCount()= " + linearLayoutManager.getItemCount()
                    + ",linearLayoutManager.findLastVisibleItemPosition()=" + linearLayoutManager.findLastVisibleItemPosition() +
                    ",linearLayoutManager.getItemCount() - 1 = " + (linearLayoutManager.getItemCount() - 1));
            onLoadMoreListener.onLoadMore();
        }
    }

    /**
     * 加载更多接口定义
     */
    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
