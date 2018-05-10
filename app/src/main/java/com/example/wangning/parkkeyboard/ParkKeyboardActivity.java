package com.example.wangning.parkkeyboard;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.wangning.R;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/5/10.
 */
public class ParkKeyboardActivity extends Activity {

    private Context mContext;
    EditText writebankcard_mobileedit;
    CustomKeyboard mCustomKeyboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_park_keyboard);

        writebankcard_mobileedit = (EditText) findViewById(R.id.writebankcard_mobileedit);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        try {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(writebankcard_mobileedit, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2 初试化键盘
        MyKeyboardView keyboardView = (MyKeyboardView) findViewById(R.id.customKeyboard);
        mCustomKeyboard = new CustomKeyboard(mContext, keyboardView, writebankcard_mobileedit);
        mCustomKeyboard.showKeyboard();

        writebankcard_mobileedit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mCustomKeyboard.showKeyboard();
                return false;
            }
        });


    }


    //物理返回键
    @Override
    public void onBackPressed() {
        if (mCustomKeyboard.isShowKeyboard()) {
            mCustomKeyboard.hideKeyboard();
        } else {
            finish();
        }
    }

}
