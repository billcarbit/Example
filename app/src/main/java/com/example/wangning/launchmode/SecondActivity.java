package com.example.wangning.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wangning.*;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2016-12-26
 * @since JDK 1.8
 */
public class SecondActivity extends Activity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.tv_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(2, new Intent());
                setResult(3, new Intent());
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
