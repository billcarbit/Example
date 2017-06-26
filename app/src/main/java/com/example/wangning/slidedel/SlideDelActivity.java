package com.example.wangning.slidedel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-06-26
 * @since JDK 1.8
 */
public class SlideDelActivity extends Activity {
    private static final String TAG = "SlideDelActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_del);
        initView();
    }

    private void initView() {
        TextView tvDel = (TextView) findViewById(R.id.tv_del);
        tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: ");
            }
        });
    }
}
