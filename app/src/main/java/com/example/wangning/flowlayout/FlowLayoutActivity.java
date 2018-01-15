package com.example.wangning.flowlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/1/15.
 */
public class FlowLayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new FlowPopup(FlowLayoutActivity.this).showAsDropDown(v);
            }
        });
    }
}
