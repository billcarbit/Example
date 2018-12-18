package com.example.wangning.annimate;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * 属性动画
 *
 * @author wangning
 * @version 1.0 2017-06-12
 * @since JDK 1.8
 */
public class ValueAnimActivity extends Activity
        implements View.OnClickListener {
    private static final String TAG = "ValueAnimActivity";
    TextView tv_sign_in_days;
    TextView tv_rotate;
    ImageView iv_bottom;
    TextView tv_xiahua;
    RelativeLayout rl_number;
    RotateAnim rotateAnim;
    SlideDownAnim slideDownAnim;
    LinearLayout ll_tran;
    TextView tv_value_change;
    TextView tv_jump;
    ImageView iv_bell;
    ImageView iv_alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animate);
        tv_sign_in_days = (TextView) findViewById(R.id.tv_sign_in_days);
        iv_bottom = (ImageView) findViewById(R.id.iv_bottom);
        rl_number = (RelativeLayout) findViewById(R.id.rl_number);
        tv_rotate = (TextView) findViewById(R.id.tv_rotate);
        tv_xiahua = (TextView) findViewById(R.id.tv_xiahua);
        ll_tran = (LinearLayout) findViewById(R.id.ll_tran);
        tv_value_change = (TextView) findViewById(R.id.tv_value_change);
        tv_jump = (TextView) findViewById(R.id.tv_jump);
        iv_bell = (ImageView) findViewById(R.id.iv_bell);
        iv_alpha = (ImageView) findViewById(R.id.iv_alpha);

        tv_rotate.setOnClickListener(this);
        rl_number.setOnClickListener(this);
        tv_xiahua.setOnClickListener(this);
        tv_value_change.setOnClickListener(this);
        tv_jump.setOnClickListener(this);
        iv_bell.setOnClickListener(this);
        iv_alpha.setOnClickListener(this);


        slideDownAnim = new SlideDownAnim();
        rotateAnim = new RotateAnim(tv_rotate);

    }

    private void runSignAnim() {
        tv_sign_in_days.setText("7");
        SignAnimModel signAnimModel = new SignAnimModel();
        signAnimModel.playScaleXYChange(tv_sign_in_days, 500, 1.5f, 1.0f);
        signAnimModel.setOnPlayTimeChangeListener(new SignAnimModel.OnPlayTimeChangeListener() {
            @Override
            public void onPlayTimeChangeListener(long playTime) {
                if (playTime > 150) {
                    tv_sign_in_days.setText("8");
                }
            }
        });
        AnimatorSet bouncer = new AnimatorSet();
        bouncer.play(signAnimModel.alphaChange(tv_sign_in_days, 300, 0.3f, 1.0f))
                .with(signAnimModel.translationChange(tv_sign_in_days, 300, -50.0f))
                .with(signAnimModel.translationChange(iv_bottom, 300, -50.0f))
                .with(signAnimModel.scaleYChange(iv_bottom, 300, 0.2f, 1.0f))
                .before(signAnimModel.translationChange(tv_sign_in_days, 300, 10.0f));
        bouncer.start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_bell:
                ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(iv_bell, "rotation", 0, 5, 0, -10, 0, 7, 0, -7, 0, 10, 0, -2);
                rotationAnimator.setRepeatCount(ObjectAnimator.INFINITE);
                rotationAnimator.setDuration(1200);
                rotationAnimator.start();
                break;
            case R.id.tv_rotate:
                rotateAnim.toggleRotate(500, 0, -90);
                break;
            case R.id.rl_number:
                runSignAnim();
                break;
            case R.id.tv_xiahua:
                translationChange(ll_tran, 1000, tv_xiahua.getHeight()).start();
                break;
            case R.id.tv_value_change:
                ValueAnimator valueAnimator = ValueAnimator.ofInt(10, 20);
                valueAnimator.setDuration(500);
                valueAnimator
                        .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                tv_value_change.setText(valueAnimator
                                        .getAnimatedValue().toString());
                            }
                        });

                valueAnimator.start();
                break;
            case R.id.tv_jump:
                TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 10);
                animation.setInterpolator(new OvershootInterpolator());
                animation.setDuration(100);
                animation.setRepeatCount(3);
                animation.setRepeatMode(Animation.REVERSE);
                tv_jump.startAnimation(animation);
                break;
            case R.id.iv_alpha:
                ObjectAnimator translateAnimation = ObjectAnimator.ofFloat(iv_alpha, "translationY", -iv_alpha.getHeight(), 0);
                translateAnimation.setDuration(1000);
                translateAnimation.start();
       /*         ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(iv_alpha, "scaleX", 5, 1);
                scaleXAnimator.setDuration(1000);

                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(iv_alpha, "scaleY", 5, 1);
                scaleYAnimator.setDuration(1000);

                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(iv_alpha,"alpha" , 0, 1);
                alphaAnimator.setDuration(2000);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(scaleXAnimator).with(scaleYAnimator).with(alphaAnimator).with(translateAnimation);
                animatorSet.start();*/


                break;
            default:
                break;

        }
    }

    public Animator translationChange(final View view, int millsecDur, float y) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, -y);
        animator.setTarget(view);
        animator.setDuration(millsecDur);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                view.setTranslationY(value);
            }
        });
        return animator;
    }
}
