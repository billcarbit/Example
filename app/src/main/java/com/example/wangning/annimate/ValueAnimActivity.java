package com.example.wangning.annimate;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        tv_sign_in_days = (TextView) findViewById(R.id.tv_sign_in_days);
        iv_bottom = (ImageView) findViewById(R.id.iv_bottom);
        rl_number = (RelativeLayout) findViewById(R.id.rl_number);
        tv_rotate = (TextView) findViewById(R.id.tv_rotate);
        tv_xiahua = (TextView) findViewById(R.id.tv_xiahua);
        tv_rotate.setOnClickListener(this);
        rl_number.setOnClickListener(this);
        tv_xiahua.setOnClickListener(this);
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
            case R.id.tv_rotate:
                rotateAnim.toggleRotate(500,0, -90);
                break;
            case R.id.rl_number:
                runSignAnim();
                break;
            case R.id.tv_xiahua:

                break;
            default:
                break;

        }
    }
}
