package com.example.wangning;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private TextView mTvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout ll_coo = (LinearLayout) findViewById(R.id.ll_coo);
        View popLayout = LayoutInflater.from(this).inflate(R.layout.popup_turn_point, ll_coo);
        View llPop = popLayout.findViewById(R.id.ll_pop);
        Log.e(TAG, "onCreate: llPop=" + llPop);

        mTvVersion = (TextView)findViewById(R.id.tv);
        mTvVersion.setText(String.format(getString(R.string.space_text),"1","2"));
        aaa();
    }

    private void aaa(){
        mTvVersion.setText(String.format(getString(R.string.space_text),"1","2"));

    }




}
