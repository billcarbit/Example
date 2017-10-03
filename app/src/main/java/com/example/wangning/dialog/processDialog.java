package com.example.wangning.dialog;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-27
 * @since JDK 1.8
 */
public class ProcessDialog extends Dialog {

    private AnimationDrawable animationDrawable;
    private TextView tvPercent;
    private float toPercent;
    private float fromPercent;
    private int duration = 1000;
    private int currentPercent;

    public ProcessDialog(Context context) {
        super(context, R.style.add_dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_copying);


        initView();
        initData();
    }

    private void initView() {
        ImageView ivLoading = (ImageView) findViewById(R.id.iv_loading);
        tvPercent = (TextView) findViewById(R.id.tv_percent);
        ivLoading.setImageResource(R.drawable.new_dialog_anim);
        animationDrawable = (AnimationDrawable) ivLoading.getDrawable();
    }

    private void initData() {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    public float getToPercent() {
        return toPercent;
    }

    public void setToPercent(float toPercent) {
        this.toPercent = toPercent;
    }

    public int getCurrentPercent() {
        return currentPercent;
    }

    public void setCurrentPercent(int currentPercent) {
        this.currentPercent = currentPercent;
    }

    public float getFromPercent() {
        return fromPercent;
    }

    public void setFromPercent(float fromPercent) {
        this.fromPercent = fromPercent;
    }

    public void notifyData() {
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

    @Override
    public void show() {
        super.show();
        animationDrawable.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        animationDrawable.stop();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
