package com.example.wangning.canvas.ball;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wangning.R;

public class BallCurveActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_curve);
        BallCurveView ballCurveView = findViewById(R.id.ballCurveView);
       // ballCurveView.startAnim();
    }
}
