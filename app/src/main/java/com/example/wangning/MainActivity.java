package com.example.wangning;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.edittext.EditTextActivity;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        SpannableStringBuilder style = new SpannableStringBuilder("AAAA");
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue0063f3)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(style);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditTextActivity.class));
            }
        });
    }


}
