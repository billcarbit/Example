package com.example.wangning.download;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.wangning.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/7/16.
 */
public class DownloadActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://localhost:8080/hello/";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFileProgress(
                        new ProgressListener() {
                            @Override
                            public void onProgress(long currentBytes, long contentLength, boolean done) {
                                Log.e("WNWNWNWN",
                                        "onProgress: currentBytes=" + currentBytes
                                                + ",contentLength="
                                                + contentLength + ",done=" + done);
                            }
                        }, new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {

                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                            }
                        });
            }
        });
    }


    public static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL);


    public static void downloadFileProgress(final ProgressListener listener, Callback<ResponseBody> callback) {
        //okhttp拦截
        OkHttpClient client = okHttpClient.newBuilder().addNetworkInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response response = chain.proceed(chain.request());
                return response.newBuilder().body(new ProgressResponseBody(response.body(), listener)).build();
            }
        }).build();


        DownloadRetrofit downloadRetrofit = retrofitBuilder.client(client).build().create(DownloadRetrofit.class);


        downloadRetrofit.downloadFile().enqueue(callback);


    }


}
