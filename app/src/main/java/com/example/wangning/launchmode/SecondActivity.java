package com.example.wangning.launchmode;

import android.app.Activity;
import android.os.Bundle;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2016-12-26
 * @since JDK 1.8
 */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
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
