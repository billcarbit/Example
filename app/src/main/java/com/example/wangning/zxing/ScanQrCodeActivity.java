package com.example.wangning.zxing;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import com.example.wangning.R;

/**
 * Created by Administrator on 2017/12/26.
 */

public class ScanQrCodeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
    }

    public void gotoScan(View view) {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String result = data.getStringExtra("result");
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }

    }

}
