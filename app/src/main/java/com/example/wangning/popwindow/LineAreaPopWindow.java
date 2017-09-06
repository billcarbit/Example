package com.example.wangning.popwindow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-05
 * @since JDK 1.8
 */
public class LineAreaPopWindow implements PopupWindow.OnDismissListener {
    PopupWindow mPopupWindow;
    PopupWindow.OnDismissListener mOnDismissListener;
    TextView tvTitle;
    TextView tv_120;
    TextView tv_60;
    TextView tv_20;
    TextView tv_7;

    public LineAreaPopWindow(final Context context) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_line_area, null);
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(false);//必须写
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(0);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        mPopupWindow.setOnDismissListener(this);

        tvTitle = (TextView) popupView.findViewById(R.id.tv_title);
        tv_120 = (TextView) popupView.findViewById(R.id.tv_120);
        tv_120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"AAA",Toast.LENGTH_SHORT).show();
            }
        });
        tv_60 = (TextView) popupView.findViewById(R.id.tv_60);
        tv_20 = (TextView) popupView.findViewById(R.id.tv_20);
        tv_7 = (TextView) popupView.findViewById(R.id.tv_7);
    }

    public void showAtLocation(View anchor, int x, int y) {
        mPopupWindow.showAtLocation(anchor, Gravity.LEFT, x, -mPopupWindow.getHeight());
    }

    public void setTitle(String title, String num120, String num60, String num20, String num7) {
        tvTitle.setText(title);
        tv_120.setText(num120);
        tv_60.setText(num60);
        tv_20.setText(num20);
        tv_7.setText(num7);
    }

    public void show(View anchor) {
        mPopupWindow.showAsDropDown(anchor);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        mOnDismissListener = listener;
    }

    @Override
    public void onDismiss() {
        mOnDismissListener.onDismiss();
    }
}
