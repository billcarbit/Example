package com.example.wangning.annimate;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * 签到动画类
 *
 * @author wangning
 * @version 3.0 2017-06-12
 * @since JDK 1.8
 */
public class SignAnimModel {

    OnSignTextChangeListener mOnSignTextChangeListener;
    OnPlayTimeChangeListener mOnPlayTimeChangeListener;

    public Animator alphaChange(View view, long duration, float fromAlpha, float toAlpha) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(view, "alpha", fromAlpha, toAlpha);
        oa.setDuration(duration);
        return oa;
    }


    public void playScaleXYChange(View view, long duration,float from,float to) {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", from, to);
        scaleXAnimator.setDuration(duration);

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", from, to);
        scaleYAnimator.setDuration(duration);

        animatorSet.play(scaleXAnimator).with(scaleYAnimator);
        animatorSet.start();
    }

    public Animator scaleYChange(View view, long duration,float from,float to) {
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", from, to);
        scaleYAnimator.setDuration(duration);
        return scaleYAnimator;
    }

    public Animator translationChange(final View view, int millsecDur, float y) {
        ValueAnimator animator = ValueAnimator.ofFloat(y, 0f);
        animator.setTarget(view);
        animator.setDuration(millsecDur);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                view.setTranslationY(value);
                mOnPlayTimeChangeListener.onPlayTimeChangeListener(animation.getCurrentPlayTime());
            }
        });
        return animator;
    }

    public void setOnPlayTimeChangeListener(OnPlayTimeChangeListener listener) {
        mOnPlayTimeChangeListener = listener;
    }

    public void setOnSignTextChangeListener(OnSignTextChangeListener listener) {
        mOnSignTextChangeListener = listener;
    }

    public interface OnSignTextChangeListener {
        void onSignTextChange();
    }

    public interface OnPlayTimeChangeListener {
        void onPlayTimeChangeListener(long playTime);
    }

}
