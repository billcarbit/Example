package com.example.wangning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.wangning.edittext.DecimalInputFilter;
import com.example.wangning.loading.LoadingDataDialog;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        double aa = 0;
        double bb = Double.valueOf(0);
        Log.e(TAG, "onCreate: aa="+aa+",bb="+bb );

    }
}
