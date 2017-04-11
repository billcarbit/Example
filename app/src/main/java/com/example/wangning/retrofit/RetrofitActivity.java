package com.example.wangning.retrofit;

import android.app.Activity;
import android.os.Bundle;

import com.example.wangning.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-04-11
 * @since JDK 1.8
 */
public class RetrofitActivity extends Activity {

    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

/*        mRetrofit = new Retrofit.Builder()
                .baseUrl(URLConstant.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SimpleService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
