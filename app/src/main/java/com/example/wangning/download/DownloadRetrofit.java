package com.example.wangning.download;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/7/16.
 */
public interface DownloadRetrofit {
    @GET("download/app-release0716.apk")
    Call<ResponseBody> downloadFile();
}
