package com.example.wangning.annimate;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-04-24
 * @since JDK 1.8
 */
public class AnimateActivity extends Activity implements View.OnClickListener {
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        initView();
        initData();
    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn);
    }

    private void initData() {
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                SignDialog signDialog = new SignDialog(this);
                signDialog.show();
                break;
            default:
                break;
        }
    }
}
