package com.example.wangning.popwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-02-27
 * @since JDK 1.8
 */
public class PopupWindowActivity extends Activity {

    private TextView mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popwindow);

        mButton = (TextView) findViewById(R.id.btn_popupwin);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new SlideDownWindow(PopupWindowActivity.this).show(v);
            }
        });
    }
}
