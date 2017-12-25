package com.example.wangning.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wangning.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-04-11
 * @since JDK 1.8
 */
public class RetrofitActivity extends Activity {
    private static final String TAG = "RetrofitActivity";
    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);


        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                //可以接收自定义的Gson，当然也可以不传
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpRequest(retrofit);
            }
        });
    }


    void httpRequest(Retrofit retrofit){
        BlogService service = retrofit.create(BlogService.class);
        Blog blog = new Blog();
        blog.setContent("新建的Blog");
        blog.setTitle("测试");
        Call<Result<Blog>> call = service.createBlog(blog);
        call.enqueue(new Callback<Result<Blog>>() {
            @Override
            public void onResponse(Call<Result<Blog>> call, Response<Result<Blog>> response) {
                // 已经转换为想要的类型了
                Result<Blog> result = response.body();
                Log.e(TAG, "onResponse: result="+result );
            }
            @Override
            public void onFailure(Call<Result<Blog>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public interface BlogService {

        @POST("/")
        Call<Result<Blog>> createBlog(@Body Blog blog);

    }



}
