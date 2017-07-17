package com.example.wangning.annimate;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-07-17
 * @since JDK 1.8
 */
public class SlideDownAnim {

    private int mAnchorResId;

    public SlideDownAnim create(int anchorResId) {
        mAnchorResId = anchorResId;
        return this;
    }

    public Animator translationChange(int millsecDur, float y) {
        ValueAnimator animator = ValueAnimator.ofFloat(y);
        animator.setTarget(mAnchorResId);
        animator.setDuration(millsecDur);
        return animator;
    }


}
