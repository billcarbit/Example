package com.example.wangning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.text.DecimalFormat;

public class MainActivity extends Activity {
    public final static String TAG = MainActivity.class.getSimpleName();

    public static String formatTosepara(float data) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, "onCreate: " + formatTosepara(0.32f));

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aaa = "1,2,3,,";
                String[] b = aaa.split(",");
                for (int i = 0; i < b.length; i++) {
                    Log.e(TAG, "onClick: b["+i+"]"+b[i]);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent backIntent) {
        Log.e(TAG, "onActivityResult: resultCode=" + resultCode + ",requestCode=" + requestCode);
        switch (resultCode) {
            case 2:
                break;
            default:
                break;
        }
    }
}
