package com.example.wangning.upload;

import android.content.Context;


import java.util.List;

/**
 * Created by Administrator on 2018/9/27.
 */
public class MyUploader {
    private Uploader mUploader = new Uploader();


    public void uploadImageList(Context context, List<String> uriList, final Callback callback) {
        mUploader.uploadImg(context, uriList);
        mUploader.setOnUploadListener(new Uploader.OnUploadImgListener() {
            @Override
            public void onUploadImgSuccess(String url) {
                callback.onUploadSuccess(url);
            }

            @Override
            public void onUploadImgFail(String msg, int errorCode) {
                callback.onUploadFail(msg,errorCode);
            }
        });
    }

    public void upload(Context context, String uri, final Callback callback) {
        mUploader.uploadImg(context, uri);
        mUploader.setOnUploadListener(new Uploader.OnUploadImgListener() {
            @Override
            public void onUploadImgSuccess(String url) {
                callback.onUploadSuccess(url);
            }

            @Override
            public void onUploadImgFail(String msg, int errorCode) {
                callback.onUploadFail(msg,errorCode);
            }
        });
    }

    public interface Callback {
        void onUploadSuccess(String url);

        void onUploadFail(String msg, int errorCode);

    }
}
