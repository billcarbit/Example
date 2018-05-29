package com.example.wangning.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/5/15.
 */
public class EditTextActivity extends Activity implements View.OnClickListener {

    EditText et_1, et_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
        et_1 = (EditText) findViewById(R.id.et_1);
        et_2 = (EditText) findViewById(R.id.et_2);
        et_2.setInputType(InputType.TYPE_NULL);


        et_1.setOnClickListener(this);
        et_2.setOnClickListener(this);

        et_2.requestFocus();
        et_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    et_1.requestFocus();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
