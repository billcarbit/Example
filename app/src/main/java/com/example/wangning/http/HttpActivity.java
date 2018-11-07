package com.example.wangning.http;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;
import com.example.wangning.ToastUtils;
import com.example.wangning.http.param.request.LoginReq;
import com.example.wangning.http.param.response.LoginResp;
import com.example.wangning.http.service.RequestService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class HttpActivity extends BaseActivity {

    private Button btn;

    @Override
    protected int getContentView() {
        return R.layout.activity_http;
    }

    @Override
    protected void initView() {
        btn = findViewById(R.id.btn);
    }

    @Override
    protected void initData() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpLogin();
            }
        });
    }

    private void httpLogin() {
        RequestService service = RetrofitFactory.getInstance().create(RequestService.class);
        Map<String, String> map = new HashMap<>();
        map.put("account","w1w1w");
        map.put("pwd","d11d1d1d1d1d1");
        Call<Result<Void>> call = service.formUrlEncoded(map);
        setHttpCall(call);
        call.enqueue(new MyCallback<Result<Void>>(mContext) {
            @Override
            protected void onSuccess(Result<Void> resp) {

            }

            @Override
            protected void onFail(String msg, int errorCode) {
            }
        });

    }
}
