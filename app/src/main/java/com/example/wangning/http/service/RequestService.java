package com.example.wangning.http.service;


import com.example.wangning.http.Result;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by Administrator on 2018/1/3.
 */
public interface RequestService {

    /**
     * 上传文件
     *
     * @param params
     * @return
     */
    @Multipart
    @POST("ovu-base/ovupark/app/uploadFile")
    Call<Result<Void>> uploadFile(@PartMap Map<String, RequestBody> params);


    /**
     * 上传图片
     *
     * @param params
     * @return
     */
    @Multipart
    @POST("fileUpload")
    Call<Result<Void>> uploadImg(@PartMap Map<String, RequestBody> params);



}




