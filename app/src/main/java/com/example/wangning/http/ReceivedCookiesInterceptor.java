package com.example.wangning.http;

import com.example.wangning.utils.SPUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/22.
 */
public class ReceivedCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            Set<String> cookies = SPUtils.getStringSet(SPUtils.PREF_COOKIES);
            if (cookies == null) {
                cookies = new HashSet<>();
            }
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            SPUtils.putStringSet(SPUtils.PREF_COOKIES, cookies);
        }

        return originalResponse;
    }
}