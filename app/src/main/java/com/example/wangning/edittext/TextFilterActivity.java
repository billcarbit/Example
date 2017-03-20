package com.example.wangning.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-20
 * @since JDK 1.8
 */
public class TextFilterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textfilter);
        EditText et = (EditText) findViewById(R.id.et);
        et.setFilters(new InputFilter[]{new MaxInputLengthFilter(22),new SpaceIntervalFilter(4)});
    }
}
