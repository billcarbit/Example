package com.example.wangning.annimate;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * 属性动画
 *
 * @author wangning
 * @version 1.0 2017-06-12
 * @since JDK 1.8
 */
public class ValueAnimActivity extends Activity {
    private static final String TAG = "ValueAnimActivity";
    TextView tv_sign_in_days;
    ImageView iv_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        Button button = (Button) findViewById(R.id.btn_value_anim);
        tv_sign_in_days = (TextView) findViewById(R.id.tv_sign_in_days);
        iv_bottom = (ImageView) findViewById(R.id.iv_bottom);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_sign_in_days.setText("7");
                SignAnimModel signAnimModel = new SignAnimModel();
                signAnimModel.playScaleXYChange(tv_sign_in_days,500,1.5f,1.0f);
                signAnimModel.setOnPlayTimeChangeListener(new SignAnimModel.OnPlayTimeChangeListener() {
                    @Override
                    public void onPlayTimeChangeListener(long playTime) {
                        if (playTime < 250 && playTime > 150) {
                            tv_sign_in_days.setText("8");
                        }
                    }
                });
                AnimatorSet bouncer = new AnimatorSet();
                bouncer.play(signAnimModel.alphaChange(tv_sign_in_days, 300, 0.3f, 1.0f))
                        .with(signAnimModel.translationChange(tv_sign_in_days, 300, -50.0f))
                        .with(signAnimModel.translationChange(iv_bottom, 300, -50.0f))
                        .with(signAnimModel.scaleYChange(iv_bottom,300,0.2f,1.0f))
                        .before(signAnimModel.translationChange(tv_sign_in_days, 300, 10.0f));
                bouncer.start();

            }
        });
    }


}
