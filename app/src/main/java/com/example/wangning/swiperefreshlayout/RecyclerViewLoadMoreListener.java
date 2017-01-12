package com.example.wangning.swiperefreshlayout;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-01-11
 * @since JDK 1.8
 */
public class RecyclerViewLoadMoreListener extends RecyclerView.OnScrollListener  {

    //RecyclerView 列表管理器
    private final LinearLayoutManager linearLayoutManager;
    //加载更多回调接口
    private final OnLoadMoreListener onLoadMoreListener;
    //最小值 回调加载
    private final int limit;

    private int prevListCount;

    public RecyclerViewLoadMoreListener(LinearLayoutManager linearLayoutManager, OnLoadMoreListener onLoadMoreListener, int limit) {
        super();
        this.linearLayoutManager = linearLayoutManager;
        this.onLoadMoreListener = onLoadMoreListener;
        this.limit = limit;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        // 向下滑动，判断最后一个item是不是显示中
        int itemCount = linearLayoutManager.getItemCount();
        if (prevListCount != itemCount && itemCount >= limit && linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.getItemCount() - 1) {
            prevListCount = itemCount;
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
