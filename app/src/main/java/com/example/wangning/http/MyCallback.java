package com.example.wangning.http;

import android.content.Context;
import android.content.Intent;


import com.example.wangning.R;
import com.example.wangning.utils.NetWorkUtils;
import com.example.wangning.utils.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/1/3.
 */
public abstract class MyCallback<T extends Result> implements Callback<T> {

    private final int CODE_FAIL = 0;//逻辑失败
    private final int CODE_SUCCESS = 1;//逻辑成功
    private final int CODE_LOGIN_OVERDUE = 2;//登录超时
    private final int CODE_NETWORK_DISCONNECT = 3;//无网络

    private Context mContext;

    public MyCallback(Context context) {
        mContext = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T result = response.body();
        if (result == null) {
            onFail("服务器异常", CODE_FAIL);
            return;
        }
        if (result.getCode() == CODE_SUCCESS) {
            try {
                onSuccess(result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            if (result.getCode() == CODE_LOGIN_OVERDUE) {//登陆过期
                ToastUtils.show(mContext, "登陆过期");
            } else {
                onFail(result.getMessage(), result.getCode());
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (!NetWorkUtils.isNetworkConnected(mContext)) {
            ToastUtils.show(mContext, "请检查网络");
            onFail(null, CODE_NETWORK_DISCONNECT);
        } else {
            onFail("服务器异常", CODE_FAIL);
        }
        t.printStackTrace();
    }


    abstract protected void onSuccess(T resp);

    abstract protected void onFail(String msg, int errorCode);
}



