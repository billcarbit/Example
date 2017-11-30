package com.example.wangning.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-11-14
 * @since JDK 1.8
 */
public class DelEditTextActivity extends Activity {
    private static final String TAG = "DelEditTextActivity";
    EditTextWithDel et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_edittext);
        initView();
        initData();
    }

    void initView() {
        et1 = (EditTextWithDel) findViewById(R.id.et1);
        et2 = (EditTextWithDel) findViewById(R.id.et2);
    }

    void initData() {
        MyTextWatcher myTextWatcher = new MyTextWatcher();
        et1.addTextChangedListener(myTextWatcher);
        et2.addTextChangedListener(myTextWatcher);
    }


    class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String et1Str = et1.getText().toString();
            String et2Str = et2.getText().toString();

            if (TextUtils.isEmpty(et1Str)
                    || TextUtils.isEmpty(et2Str)
                    ) {
            } else {
            }

        }
    }

}
