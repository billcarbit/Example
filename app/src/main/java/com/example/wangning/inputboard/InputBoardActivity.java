package com.example.wangning.inputboard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/4/27.
 */
public class InputBoardActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_board);
        final EditText et_input = (EditText) findViewById(R.id.et_input);
        et_input.requestFocus();
        et_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager) et_input.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(et_input, 0);
            }
        });

    }
}
