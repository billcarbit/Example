package com.example.wangning.canvas.chart.pie;

import android.app.Activity;
import android.os.Bundle;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-08-18
 * @since JDK 1.8
 */
public class PieActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        PieView pieView = (PieView) findViewById(R.id.v_pie);
    }
}
