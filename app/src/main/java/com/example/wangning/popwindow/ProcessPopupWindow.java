package com.example.wangning.popwindow;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * 进度条弹窗
 *
 * @author wangning
 * @version 1.0 2017-09-27
 * @since JDK 1.8
 */
public class ProcessPopupWindow {
    private PopupWindow mPopupWindow;
    private AnimationDrawable animationDrawable;
    private TextView tvPercent;
    private int currentPercent;

    public ProcessPopupWindow(Activity context) {
        initView(context);
        initData();
    }

    private void initView(Context context) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.view_copying, null);
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(false);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(0);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));

        ImageView ivLoading = (ImageView) popupView.findViewById(R.id.iv_loading);
        tvPercent = (TextView) popupView.findViewById(R.id.tv_percent);
        ivLoading.setImageResource(R.drawable.new_dialog_anim);
        animationDrawable = (AnimationDrawable) ivLoading.getDrawable();

    }

    private void initData() {

    }


    public void startValueAnimator(float fromPercent,float toPercent,int duration) {
        int from = (int) (fromPercent * 100);
        int to = (int) (toPercent * 100);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(from, to);
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentPercent = (int) valueAnimator.getAnimatedValue();
                tvPercent.setText(valueAnimator.getAnimatedValue().toString() + "%");
            }
        });

        valueAnimator.start();
    }

    public void show(View anchor) {
        mPopupWindow.showAsDropDown(anchor);
        animationDrawable.start();
    }

    public void dismiss() {
        mPopupWindow.dismiss();
        animationDrawable.stop();
    }

    public int getCurrentPercent() {
        return currentPercent;
    }

    public void setCurrentPercent(int currentPercent) {
        this.currentPercent = currentPercent;
    }
}
