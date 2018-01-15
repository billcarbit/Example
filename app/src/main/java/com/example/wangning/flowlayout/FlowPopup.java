package com.example.wangning.flowlayout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/1/15.
 */
public class FlowPopup {
    private PopupWindow mPopupWindow;
    Context mContext;

    public FlowPopup(Activity context) {
        mContext = context;
        initView();
        initData();
    }

    void initView() {

        View popupView = LayoutInflater.from(mContext).inflate(R.layout.layout_pop_flow, null);
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(0);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), (Bitmap) null));

    }

    void initData() {

    }

    public void showAsDropDown(View view){
        mPopupWindow.showAsDropDown(view);
    }

}
