package com.example.wangning.checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-28
 * @since JDK 1.8
 */
public class CheckboxActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBox.setChecked(isChecked);
            }
        });
    }
}
