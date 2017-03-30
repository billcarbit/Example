package com.example.wangning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.wangning.launchmode.SecondActivity;
import com.example.wangning.loading.LoadingDataDialog;
import com.example.wangning.viewinvalidate.ViewInvalidateActivity;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = MainActivity.class.getSimpleName();

    private LoadingDataDialog mDataDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e(TAG, "onBackPressed: " );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        Log.e(TAG, "onSaveInstanceState: " );
        onBackPressed();
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e(TAG, "onKeyUp: ");
        return super.onKeyUp(keyCode, event);
    }
}
