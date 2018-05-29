package com.example.wangning.monitor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;

/**
 * Created by admin on 2018/2/10.
 */
public class MonitorActivity extends Activity
        implements View.OnClickListener {

    private static final String TAG = "MonitorActivity";
    Button btnMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        initView();
        initData();
    }

    private void initView() {
        btnMethod = (Button) findViewById(R.id.btn_method);
    }

    private void initData() {
        btnMethod.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_method:
                aaaaaaaaaaaaaaaa();
                break;
            default:
                break;
        }
    }

    private void aaaaaaaaaaaaaaaa(){
        byte[] ds = new byte[1024*1024*10];
        Log.e(TAG, "onClick: btn_method,ds="+ds);
    }
}
