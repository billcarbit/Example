package com.example.wangning.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import com.example.wangning.R;


/**
 * 加载loading
 *
 * @author wangning
 * @version 1.0 2017-05-31
 * @since JDK 1.8
 */
public class LoadingDialog extends Dialog{

    private AnimationDrawable animationDrawable;

    public LoadingDialog(Context context) {
        super(context, R.style.transparent_dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loading_view);
        ImageView imageview = (ImageView) findViewById(R.id.loading_view);
        imageview.setImageResource(R.drawable.animation_loading);
        animationDrawable = (AnimationDrawable) imageview.getDrawable();

        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        super.show();
        animationDrawable.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        animationDrawable.stop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
