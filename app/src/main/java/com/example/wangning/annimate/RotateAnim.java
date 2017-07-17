package com.example.wangning.annimate;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-07-14
 * @since JDK 1.8
 */
public class RotateAnim implements Animator.AnimatorListener {
    ObjectAnimator rotationAnimator;
    boolean flag = true;

    public RotateAnim(View view) {
        rotationAnimator = ObjectAnimator.ofFloat(view, "rotation", 0, 0);

        rotationAnimator.addListener(this);
    }

    public void startRotateAnim(long dur, float from, float to) {
/*        Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        operatingAnim.setFillAfter(true);// 动画执行完后停留在执行完的状态
        view.startAnimation(operatingAnim);*/
        rotationAnimator.setFloatValues(from, to);
        rotationAnimator.setDuration(dur);
        rotationAnimator.start();


    }

    public void toggleRotate(long dur, float from, float to) {
        if (rotationAnimator.isStarted()) {
            return;
        }
        if (flag) {
            rotationAnimator.setFloatValues(from, to);
        } else {
            rotationAnimator.setFloatValues(to, -from);
        }
        rotationAnimator.setDuration(dur);
        rotationAnimator.start();
        flag = !flag;
    }

    @Override
    public void onAnimationStart(Animator animation) {
    }

    @Override
    public void onAnimationEnd(Animator animation) {
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
