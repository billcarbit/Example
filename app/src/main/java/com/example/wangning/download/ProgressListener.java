package com.example.wangning.download;

/**
 * Created by Administrator on 2018/7/16.
 */
public interface ProgressListener {
    void onProgress(long currentBytes, long contentLength, boolean done);
}