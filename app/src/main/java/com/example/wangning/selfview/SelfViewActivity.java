package com.example.wangning.selfview;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import com.example.wangning.R;

/**
 * 自定义View
 *
 * @author wangning
 * @version 1.0 2017-06-28
 * @since JDK 1.8
 */
public class SelfViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfview);
        RedDotView redDotView = (RedDotView)findViewById(R.id.red_dot);
        redDotView.setText(1);

    }
}
