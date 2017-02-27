package com.example.wangning.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
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
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popwindow);

        View popupView = getLayoutInflater().inflate(R.layout.popwindow_main, null);

        mPopupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
       // mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));


        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams  windowLp = getWindow().getAttributes();
                windowLp.alpha=1.0f;
                getWindow().setAttributes(windowLp);
            }
        });


        mButton = (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPopupWindow.showAtLocation(v, Gravity.CENTER,0,0);
                WindowManager.LayoutParams  windowLp = getWindow().getAttributes();
                windowLp.alpha=0.1f;
                getWindow().setAttributes(windowLp);
            }
        });
    }


}
