package com.example.wangning.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.wangning.R;


/**
 * 上拉加载
 * Created by Administrator on 2018/3/1.
 */
public class PullUpToLoad extends LinearLayout {

    LinearLayout llLoading;
    LinearLayout llNoMoreData;
    LinearLayout llClickLoadMore;

    public PullUpToLoad(Context context) {
        this(context,null);
    }

    public PullUpToLoad(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_pull_up_refresh, this);
        llLoading = (LinearLayout) findViewById(R.id.ll_loading);
        llNoMoreData = (LinearLayout) findViewById(R.id.ll_no_more_data);
        llClickLoadMore = (LinearLayout) findViewById(R.id.ll_click_load_more);
    }

    /**
     * 正在加载
     */
    public void showLoading(boolean show) {
        if(show){
            llLoading.setVisibility(VISIBLE);
            llNoMoreData.setVisibility(GONE);
            llClickLoadMore.setVisibility(GONE);
        }else{
            llLoading.setVisibility(GONE);
            llNoMoreData.setVisibility(GONE);
            llClickLoadMore.setVisibility(GONE);
        }
    }

    /**
     * 显示没有更多数据了
     * @param show
     */
    public void showNoMoreData(boolean show){
        if(show){
            llLoading.setVisibility(GONE);
            llNoMoreData.setVisibility(VISIBLE);
            llClickLoadMore.setVisibility(GONE);
        }else{
            llLoading.setVisibility(GONE);
            llNoMoreData.setVisibility(GONE);
            llClickLoadMore.setVisibility(GONE);
        }
    }

    /**
     * 显示点击加载更多
     * @param show
     */
    public void showClickLoadMore(boolean show){
        if(show){
            llLoading.setVisibility(GONE);
            llNoMoreData.setVisibility(GONE);
            llClickLoadMore.setVisibility(VISIBLE);
        }else{
            llLoading.setVisibility(GONE);
            llNoMoreData.setVisibility(GONE);
            llClickLoadMore.setVisibility(GONE);
        }
    }

    public void hideAll(){
        llLoading.setVisibility(GONE);
        llNoMoreData.setVisibility(GONE);
        llClickLoadMore.setVisibility(GONE);
    }
}
