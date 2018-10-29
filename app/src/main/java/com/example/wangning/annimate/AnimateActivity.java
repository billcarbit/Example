package com.example.wangning.annimate;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-04-24
 * @since JDK 1.8
 */
public class AnimateActivity extends Activity implements View.OnClickListener {
    private ImageView iv_scale;
    private TextView tv_scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        initView();
        initData();
    }

    private void initView() {
        iv_scale = (ImageView) findViewById(R.id.iv_scale);
        tv_scale = findViewById(R.id.tv_scale);
    }

    private void initData() {
        initListener();
        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.scale);
        iv_scale.startAnimation(scaleAnimation);
    }

    private void initListener() {
        iv_scale.setOnClickListener(this);
        tv_scale.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_scale:
                final ScaleAnimation animation = new ScaleAnimation(Animation.RELATIVE_TO_SELF, 1.4f,
                        Animation.RELATIVE_TO_SELF, 1.4f);
                animation.setDuration(2000);//设置动画持续时间
                iv_scale.setAnimation(animation);
                break;
            case R.id.tv_scale:
                final ScaleAnimation animation1 = new ScaleAnimation(Animation.RELATIVE_TO_SELF, 1.4f,
                        Animation.RELATIVE_TO_SELF, 1.4f);
                animation1.setDuration(1000);//设置动画持续时间
                tv_scale.setAnimation(animation1);
                break;
            default:
                break;
        }
    }
}
