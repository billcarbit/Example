package com.example.wangning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.rl_modulename_refresh);
        refreshLayout
                .setRefreshListener(new RefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refreshLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                refreshLayout.refreshComplete();
                            }
                        }, 2000);
                    }
                });
        // startActivity(new Intent(this, PullToRefreshActivity.class));

    }


}
