package com.example.wangning.inputboard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/4/27.
 */
public class InputBoardActivity extends Activity implements View.OnClickListener {

    private Button btnShowKeyboard;
    private Button btnHideKeyboard;
    private InputMethodManager imm;
    private EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_board);
        initView();
        initData();
        initListener();
   /*     et_input.requestFocus();
        et_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager) et_input.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(et_input, 0);
            }
        });*/

    }

    private void initView() {
        etInput = (EditText) findViewById(R.id.et_input);
        btnShowKeyboard = (Button) findViewById(R.id.btn_show_keyboard);
        btnHideKeyboard = (Button) findViewById(R.id.btn_hide_keyboard);
    }

    private void initData(){
        imm  = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void initListener() {
        btnShowKeyboard.setOnClickListener(this);
        btnHideKeyboard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_keyboard:
                etInput.requestFocus();
                imm.showSoftInput(etInput,InputMethodManager.SHOW_FORCED);
                break;
            case R.id.btn_hide_keyboard:
                imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0); //强制隐藏键盘
                break;
            default:
                break;
        }
    }
}
