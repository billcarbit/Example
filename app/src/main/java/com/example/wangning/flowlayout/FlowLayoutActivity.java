package com.example.wangning.flowlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/1/15.
 */
public class FlowLayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);

        EditText et = (EditText) findViewById(R.id.et);
        et.setText("AA");
        et.setSelection(et.length());
        FlowLayout fl = (FlowLayout) findViewById(R.id.fl);
        for (int i = 0; i < 30; i++) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_children, null);
            TextView textView = (TextView) view.findViewById(R.id.second_textview);
            textView.setText("A" + i);
            fl.addView(view);
        }
    }
}
