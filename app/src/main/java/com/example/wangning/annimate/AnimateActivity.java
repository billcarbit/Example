package com.example.wangning.annimate;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
    private ImageView iv_bell;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        initView();
        initData();
    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn_get_json);
        iv_bell = (ImageView) findViewById(R.id.iv_bell);

    }

    private void initData() {
        mBtn.setOnClickListener(this);

        iv_bell.setImageResource(R.drawable.bell_anim);
        animationDrawable = (AnimationDrawable) iv_bell.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_json:
                SignDialog signDialog = new SignDialog(this);
                signDialog.show();
                break;
            default:
                break;
        }
    }
}
