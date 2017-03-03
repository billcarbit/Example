package com.example.wangning.addersubtracter;

import android.app.Activity;
import android.os.Bundle;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-03
 * @since JDK 1.8
 */
public class PlusMinusActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plus_minus);
        AdderSubtracterView  mAdderSubtracter = (AdderSubtracterView) findViewById(R.id.adder_subtracter);
        mAdderSubtracter
                .setBetweenWidth(54)
                .setValue("1")
                .setMinValue("0")
                .setMaxValue("3");

    }
}
