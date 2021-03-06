package com.example.wangning.popwindow;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.example.wangning.R;
import com.example.wangning.utils.AppUtil;

import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-04-25
 * @since JDK 1.8
 */
public class MyPopupWindow {
    private Activity mActivity;
    private PopupWindow mPopupWindow;

    public MyPopupWindow(Activity activity) {
        mActivity = activity;
        View popupView = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_main, null);
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupHeight = popupView.getMeasuredHeight();
        mPopupWindow = new PopupWindow(popupView, AppUtil.dp2px(mActivity, 200), popupHeight);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(mActivity.getResources(), (Bitmap) null));
    }


    public void show(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        Log.e("AAA", "show:mPopupWindow.getWidth()= " + mPopupWindow.getWidth());

        DisplayMetrics metric = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        if (location[0] + view.getWidth() / 2 + mPopupWindow.getWidth() > width) {
            mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] + view.getWidth() / 2 - mPopupWindow.getWidth(), location[1] - mPopupWindow.getHeight() + view.getHeight() / 2);

        } else {
            mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] + view.getWidth() / 2, location[1] - mPopupWindow.getHeight() + view.getHeight() / 2);

        }


    }

}
