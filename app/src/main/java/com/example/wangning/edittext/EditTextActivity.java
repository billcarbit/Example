package com.example.wangning.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/5/15.
 */
public class EditTextActivity extends Activity {

    EditText et_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
        et_1 = findViewById(R.id.et_1);
        et_1.setFilters(new InputFilter[]{new MaxInputFilter(2)});
    }


}
