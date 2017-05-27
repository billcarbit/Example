package com.example.wangning;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
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
import com.example.wangning.launchmode.SecondActivity;
import com.example.wangning.loading.LoadingDataDialog;

public class MainActivity extends Activity {
    public final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivityForResult(new Intent(this, SecondActivity.class), 3);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent backIntent) {
        Log.e(TAG, "onActivityResult: resultCode="+resultCode+",requestCode="+requestCode );
        switch (resultCode) {
            case 2:
                break;
            default:
                break;
        }
    }
}
