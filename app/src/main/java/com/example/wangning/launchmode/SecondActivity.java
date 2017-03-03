package com.example.wangning.launchmode;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2016-12-26
 * @since JDK 1.8
 */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView tv= (TextView)findViewById(R.id.tv_second);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = getLayoutInflater().inflate(R.layout.popwindow_main, null);

                PopupWindow mPopupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
                mPopupWindow.setTouchable(true);
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

                mPopupWindow.showAtLocation(view, Gravity.CENTER,0,0);
                WindowManager.LayoutParams  windowLp = getWindow().getAttributes();
                windowLp.alpha=0.5f;
                getWindow().setAttributes(windowLp);

                mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams  windowLp = getWindow().getAttributes();
                        windowLp.alpha=1.0f;
                        getWindow().setAttributes(windowLp);
                    }
                });

                TextView tvAll =  (TextView) popupView.findViewById(R.id.tv_all);
                tvAll.setOnClickListener(new View.OnClickListener() {//全部
                    @Override
                    public void onClick(View view) {

                    }
                });

                TextView tvIncome =  (TextView) popupView.findViewById(R.id.tv_income);
                tvIncome.setOnClickListener(new View.OnClickListener() {//收入
                    @Override
                    public void onClick(View view) {

                    }
                });

                TextView tvOutcome =  (TextView) popupView.findViewById(R.id.tv_outcome);
                tvOutcome.setOnClickListener(new View.OnClickListener() {//支出
                    @Override
                    public void onClick(View view) {

                    }
                });

                TextView tvOutdate =  (TextView) popupView.findViewById(R.id.tv_outdate);
                tvOutdate.setOnClickListener(new View.OnClickListener() {//过期
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
