package com.example.wangning.pulltorefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.wangning.R;

public class PullToRefreshActivity extends AppCompatActivity {

    public final static String TAG = PullToRefreshActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_resresh);
/*
        final RefreshLayout2 refreshLayout = (RefreshLayout2) findViewById(R.id.rl_modulename_refresh);
        refreshLayout.setPullDownListener(new RefreshLayout2.OnPullDownListener() {
            @Override
            public void onRefresh() {
              Log.e(TAG, "onRefresh: setPullDownListener" );
            *//*      refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.refreshComplete();
                    }
                }, 2000);*//*
            }
        }).setPullUpListener(new RefreshLayout2.OnPullUpListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "onRefresh: OnPullUpListener");
            }
        });*/

    }


}
