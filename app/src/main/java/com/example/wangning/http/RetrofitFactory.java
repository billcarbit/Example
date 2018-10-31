package com.example.wangning.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2018/1/3.
 */
public class RetrofitFactory {

    public static String base_url = HttpUrl.BASE_URL;
    private static Retrofit mRetrofit = null;

    public static Retrofit getInstance() {
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder()
                    //配置你的Gson
                    .setDateFormat("yyyy-MM-dd hh:mm:ss")
                    .create();

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            httpClientBuilder.addInterceptor(logging);
//        }
            OkHttpClient httpClient = httpClientBuilder
                    .addInterceptor(new ReceivedCookiesInterceptor())
                    .addInterceptor(new AddCookiesInterceptor())
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    //.proxy(Proxy.NO_PROXY) //开启后无法抓包
                    .build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    //可以接收自定义的Gson，当然也可以不传
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();

            mRetrofit = retrofit;
        }
        return mRetrofit;
    }

}
