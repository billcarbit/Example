package com.example.wangning.popwindow;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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

    private ProcessPopupWindow mPopupWindow;
    private Button btnNormalPopupwin;
    private Button btnAnimatePw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popwindow);
        initView();
        initListener();
    }


    private void initView() {
        btnNormalPopupwin = (Button) findViewById(R.id.btn_normal_popupwin);
        btnAnimatePw = (Button) findViewById(R.id.btn_animate_pw);
    }

    private void initListener() {
        btnNormalPopupwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView = getLayoutInflater().inflate(R.layout.popwindow_main, null);
                PopupWindow mPopupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
                mPopupWindow.setTouchable(true);
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

                //mPopupWindow.showAtLocation(view, Gravity.CENTER,0,0);
                mPopupWindow.showAsDropDown(v);
                WindowManager.LayoutParams windowLp = getWindow().getAttributes();
                windowLp.alpha = 0.4f;
                getWindow().setAttributes(windowLp);
                mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams windowLp = getWindow().getAttributes();
                        windowLp.alpha = 1.0f;
                        getWindow().setAttributes(windowLp);
                    }
                });
            }
        });

        btnAnimatePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow == null) {
                    mPopupWindow = new ProcessPopupWindow(PopupWindowActivity.this);
                }
                mPopupWindow.show(v);
                mPopupWindow.startValueAnimator(0.0f, 1.0f, 2000);
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
