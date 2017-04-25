package com.example.wangning.retrofit;

import com.example.wangning.retrofit.examples.SimpleService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-04-12
 * @since JDK 1.8
 */
public interface MyHttpService {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<SimpleService.Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
}