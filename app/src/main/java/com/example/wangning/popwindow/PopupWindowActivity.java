package com.example.wangning.popwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-02-27
 * @since JDK 1.8
 */
public class PopupWindowActivity extends Activity {
    private PopupWindow mPopupWindow;
    private Button mButton, mBtnStartActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popwindow);

        mButton = (Button) findViewById(R.id.btn_popupwin);
        mBtnStartActivity = (Button) findViewById(R.id.btn_start_activity);
        mBtnStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PopupWindowActivity.this, SecondPopWindowActivity.class));
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new MyPopupWindow(PopupWindowActivity.this).show(v);
            }
        });
    }


}
