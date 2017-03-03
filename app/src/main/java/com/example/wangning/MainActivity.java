package com.example.wangning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
}
