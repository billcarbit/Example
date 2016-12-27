package com.example.wangning.pulltorefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.wangning.R;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_resresh);

        final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.rl_modulename_refresh);
        refreshLayout.setPullDownListener(new RefreshLayout.OnPullDownListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "onRefresh: setPullDownListener" );
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.refreshComplete();
                    }
                }, 2000);
            }
        }).setPullUpListener(new RefreshLayout.OnPullUpListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "onRefresh: OnPullUpListener");
            }
        });

    }


}
