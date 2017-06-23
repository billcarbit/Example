package com.example.wangning.popwindow;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
<<<<<<< HEAD
=======
import android.view.WindowManager;
>>>>>>> 1690084965674f8ddf07a5519a759d6099eec185
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
<<<<<<< HEAD
    private List<String> mDataList;
=======
>>>>>>> 1690084965674f8ddf07a5519a759d6099eec185

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

<<<<<<< HEAD
    public void setData(List<String> datas) {
        mDataList = datas;
    }

    public void show(View view) {
   /*     WindowManager.LayoutParams windowLp = mActivity.getWindow().getAttributes();
        windowLp.alpha = 0.5f;
        mActivity.getWindow().setAttributes(windowLp);*/
        // mPopupWindow.showAsDropDown(view);
        //  mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);

=======
    public void show(View view) {
>>>>>>> 1690084965674f8ddf07a5519a759d6099eec185
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        Log.e("AAA", "show:mPopupWindow.getWidth()= " + mPopupWindow.getWidth());

        DisplayMetrics metric = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        if (location[0] + view.getWidth() / 2 + mPopupWindow.getWidth() > width) {
            mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] + view.getWidth() / 2 - mPopupWindow.getWidth(), location[1] - mPopupWindow.getHeight() + view.getHeight() / 2);

<<<<<<< HEAD
        if (location[0] + view.getWidth() / 2 + mPopupWindow.getWidth() > width) {
            mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] + view.getWidth() / 2 - mPopupWindow.getWidth(), location[1] - mPopupWindow.getHeight() + view.getHeight() / 2);

=======
>>>>>>> 1690084965674f8ddf07a5519a759d6099eec185
        } else {
            mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] + view.getWidth() / 2, location[1] - mPopupWindow.getHeight() + view.getHeight() / 2);

        }


    }

}
