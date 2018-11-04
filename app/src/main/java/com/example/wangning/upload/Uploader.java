package com.example.wangning.upload;

import android.content.Context;
import android.text.TextUtils;

import com.example.wangning.http.MyCallback;
import com.example.wangning.http.Result;
import com.example.wangning.http.RetrofitFactory;
import com.example.wangning.http.service.RequestService;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/2/9.
 */
public class Uploader {


    public void uploadImg(final Context context, String imgUrl) {
        List<String> list = new ArrayList<>();
        list.add(imgUrl);
        uploadImg(context, list);
    }

    /**
     * 上传本地图片
     *
     * @param context
     * @param imgUrlList
     */
    public void uploadImg(final Context context, List<String> imgUrlList) {
        Map<String, RequestBody> params = new HashMap<>();
        for (int i = 0; i < imgUrlList.size(); i++) {
            String imgUrl = imgUrlList.get(i);
            if (TextUtils.isEmpty(imgUrl)) {
                continue;
            }
            File file = new File(imgUrl);
            // create RequestBody instance from file
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            //一定要加("AttachmentKey\"; filename=\"" +，不然失败
            params.put("fileName222\"; " + "index=" + i + ";" + "filename=\"" + file.getName(), requestFile);
        }

        // create upload service client
        RequestService service = RetrofitFactory.getInstance().create(RequestService.class);
        // finally, execute the request
        Call<Result<Void>> call = service.uploadImg(params);
        call.enqueue(new MyCallback<Result<Void>>(context) {
            @Override
            protected void onSuccess(Result<Void> resp) {

            }

            @Override
            protected void onFail(String msg, int errorCode) {
                if (mOnUploadImgListener != null) {
                    mOnUploadImgListener.onUploadImgFail(msg, errorCode);
                }
            }
        });
    }

    public interface OnUploadImgListener {

        void onUploadImgSuccess(String url);

        void onUploadImgFail(String msg, int errorCode);
    }

    public interface OnUploadFileListener {
        void onUploadFileSuccess(String url);

        void onUploadFileFail(String msg, int errorCode);
    }

    private OnUploadImgListener mOnUploadImgListener;
    private OnUploadFileListener mOnUploadFileListener;

    public void setOnUploadListener(OnUploadImgListener listener) {
        mOnUploadImgListener = listener;
    }

    public void setOnUploadFileListener(OnUploadFileListener listener) {
        mOnUploadFileListener = listener;
    }
}
