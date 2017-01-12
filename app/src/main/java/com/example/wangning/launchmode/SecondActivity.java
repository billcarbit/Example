package com.example.wangning.launchmode;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

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
        TextView tv= (TextView)findViewById(R.id.tv_freight_goods_number);
        tv.setText("\n\n\n\n\n"+"ABC");
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
