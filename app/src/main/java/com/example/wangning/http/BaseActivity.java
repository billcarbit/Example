package com.example.wangning.http;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.wangning.http.param.response.LoginResp;

import retrofit2.Call;

public abstract class BaseActivity extends Activity {

    protected Context mContext;
    protected Call<Result> mHttpCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getContentView());
        initView();
        initData();
    }

    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected void setHttpCall(Call call) {
        mHttpCall = call;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHttpCall != null && mHttpCall.isExecuted()) {
            mHttpCall.cancel();
        }
    }



    protected <T extends View> T getViewById(int viewId) {
        View view = getWindow().findViewById(viewId);
        return (T) view;
    }
}
