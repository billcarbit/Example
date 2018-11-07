package com.example.wangning.http.service;


import com.example.wangning.http.Result;
import com.example.wangning.http.param.request.LoginReq;
import com.example.wangning.http.param.response.LoginResp;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

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

    @POST("hello/login")
    Call<Result<LoginResp>> login(@Body LoginReq req);

    @FormUrlEncoded
    @POST("hello/formUrlEncoded")
    Call<Result<Void>> formUrlEncoded(@FieldMap Map<String,String> req);

}




