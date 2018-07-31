package com.example.wangning.download;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
 * 基于retrofit的下载器
 * Created by Administrator on 2018/7/24.
 */
public class RetrofitDownloader implements ProgressListener {
    private static final String BASE_URL = "http://localhost:8080/hello/";
    private Call<ResponseBody> mDownloadCall;
    private File outputFile;
    private int seek = 0;
    private long mContentLength;
    private ProgressResponseBody mProgressResponseBody;
    private Activity mActivity;

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    okhttp3.Response response = chain.proceed(chain.request());
                    Log.e("addInterceptor", "intercept: response="+response.toString());
                    mProgressResponseBody = new ProgressResponseBody(response.body(), RetrofitDownloader.this);
                    return response.newBuilder().body(mProgressResponseBody).build();
                }
            })
            .addNetworkInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    okhttp3.Response response = chain.proceed(chain.request());
                    Log.e("addNetworkInterceptor", "intercept: response="+response.toString());
                    mProgressResponseBody = new ProgressResponseBody(response.body(), RetrofitDownloader.this);
                    return response.newBuilder().body(mProgressResponseBody).build();
                }
            })
            .build();

    public Retrofit mRetrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.newBuilder().build())
            .baseUrl(BASE_URL).build();

    public RetrofitDownloader(Activity activity) {
        mActivity = activity;
        outputFile = new File(activity.getExternalCacheDir() + "/IDE.exe");
        Log.e("AA", "RetrofitDownloader: " + outputFile.getPath().toString());
        verifyStoragePermissions(activity);
    }

    public void startDownload() {
        if (seek <= 0) {
            startNewDownload();
        } else {
            continueDownload();
        }
    }

    /**
     * 开始新的下载
     */
    private void startNewDownload() {
        DownloadRetrofit service = mRetrofit.create(DownloadRetrofit.class);
        if (mDownloadCall != null && !mDownloadCall.isCanceled()) {
            Log.e("AAA", "startNewDownload:is not Canceled ");
            return;
        }
        mDownloadCall = service.downloadApk();
        mDownloadCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String length = response.headers().get("Content-Length");
                    Log.e("startNewDownload", "thread="+Thread.currentThread().getName()+",onResponse: length=" + length);
                    installApk();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * 继续下载
     */
    private void continueDownload() {
        DownloadRetrofit service = mRetrofit.create(DownloadRetrofit.class);
        if (seek >= mContentLength) {
            return;
        }
        if (mDownloadCall != null && !mDownloadCall.isCanceled()) {
            Log.e("AAA", "continueDownload:is not Canceled ");
            return;
        }
        mDownloadCall = service.downloadApk("bytes=" + seek + "-" + mContentLength);
        mDownloadCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String length = response.headers().get("Content-Length");
                    Log.e("continueDownload", "thread="+Thread.currentThread().getName()+",onResponse: length=" + length);
                    installApk();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void pauseDownload() {
        if (mDownloadCall != null) {
            mDownloadCall.cancel();
        }
    }


    @Override
    public void onProgress(long currentBytes, long contentLength, boolean done) {
        if (mContentLength == 0) {
            mContentLength = contentLength;
        }

        Log.e("ASASA", "onProgress: currentBytes=" + currentBytes + ",contentLength=" + contentLength + ",done=" + done);
    }


    public void output(InputStream inputStream) throws IOException {
        FileOutputStream out = new FileOutputStream(outputFile, true);
        byte buf[] = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) > 0) {
            seek = seek + len;
            if (seek >= mContentLength) {
                Log.e("ASAS", "BBBBBBBBBBBBBBBBBBBBBB,seek=" + seek);
            }
            Log.e("ASAS", "thread=" + Thread.currentThread().getName() + "output: seek=" + seek);
            out.write(buf, 0, len);
        }
        out.close();
        inputStream.close();
    }

    private final int REQUEST_EXTERNAL_STORAGE = 1;
    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }


    private void installApk() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + outputFile), "application/vnd.android.package-archive");
        mActivity.startActivity(i);
    }


}
