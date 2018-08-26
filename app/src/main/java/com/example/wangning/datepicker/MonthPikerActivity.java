package com.example.wangning.datepicker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.wangning.R;

/**
 * Created by admin on 2018/8/26.
 */
public class MonthPikerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        initView();
    }

    private void initView() {
        View v = findViewById(R.id.tv);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new YearMonthCurrentPicker(MonthPikerActivity.this).showPickerView(v);
            }
        });
    }
}
