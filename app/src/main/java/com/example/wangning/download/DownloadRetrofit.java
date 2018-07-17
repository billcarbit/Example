package com.example.wangning.download;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;

/**
 * Created by Administrator on 2018/7/16.
 */
public interface DownloadRetrofit {
    @GET("download/app-release0716.apk")
    Call<ResponseBody> downloadApk(@Header("Range")String range);

    @GET("download/dwdw11.txt")
    Call<ResponseBody> downloadTxt();

}
