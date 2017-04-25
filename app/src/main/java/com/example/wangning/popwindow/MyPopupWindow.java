package com.example.wangning.popwindow;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.wangning.R;

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
        View popupView = mActivity.getLayoutInflater().inflate(R.layout.popwindow_main, null);
        mPopupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(mActivity.getResources(), (Bitmap) null));
    }

    public void show(View view){
   /*     WindowManager.LayoutParams windowLp = mActivity.getWindow().getAttributes();
        windowLp.alpha = 0.5f;
        mActivity.getWindow().setAttributes(windowLp);*/
       // mPopupWindow.showAsDropDown(view);
      //  mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1]- mPopupWindow.getHeight() * 2);

    }

}
