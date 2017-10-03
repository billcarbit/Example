package com.example.wangning.popwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-02-27
 * @since JDK 1.8
 */
public class PopupWindowActivity extends Activity {

    ProcessPopupWindow mPopupWindow;
    private TextView mButton, mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popwindow);

        mButton = (TextView) findViewById(R.id.btn_popupwin);
        mButton2 = (TextView) findViewById(R.id.btn_popupwin2);

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow == null) {
                    mPopupWindow = new ProcessPopupWindow(PopupWindowActivity.this);
                }
                mPopupWindow.show(v);
                mPopupWindow.startValueAnimator(0.0f,1.0f,2000);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }
}
