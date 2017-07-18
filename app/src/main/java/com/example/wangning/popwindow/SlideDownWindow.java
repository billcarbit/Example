package com.example.wangning.popwindow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.wangning.R;
import com.example.wangning.utils.AppUtil;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-07-17
 * @since JDK 1.8
 */
public class SlideDownWindow implements PopupWindow.OnDismissListener {
    PopupWindow mPopupWindow;
    PopupWindow.OnDismissListener mOnDismissListener;

    public SlideDownWindow(Context context) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.dialog_records, null);
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);//必须写
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(0);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        mPopupWindow.setOnDismissListener(this);
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
