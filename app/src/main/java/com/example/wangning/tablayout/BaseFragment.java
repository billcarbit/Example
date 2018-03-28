package com.example.wangning.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangning.R;


/**
 * Created by Administrator on 2017/12/28.
 */
public abstract class BaseFragment extends Fragment {
    protected int mPageIndex = 0;
    protected int mPageSize = 10;
    protected int mRefreshColor = R.color.black;

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getContentView(), null);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initView(rootView);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getContentView();

    protected abstract void initView(View rootView);

    protected abstract void initData();
}
