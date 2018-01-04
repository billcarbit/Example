package com.example.wangning.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/1/4.
 */
public class AddPhotoDialog extends Dialog
        implements View.OnClickListener {
    Context mContext;
    TextView tvShoot;
    TextView tvPhotoAlbum;
    TextView tvCancel;

    public AddPhotoDialog(Context context) {
        super(context, R.style.add_dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setWindowAnimations(R.style.slide_up);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);

        getWindow().setGravity(Gravity.BOTTOM);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_add_photo);
        initView();
        initData();
    }

    void initView() {
        tvShoot = (TextView) findViewById(R.id.tv_shoot);
        tvPhotoAlbum = (TextView) findViewById(R.id.tv_photo_album);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
    }

    void initData() {
        tvShoot.setOnClickListener(this);
        tvPhotoAlbum.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_shoot:
                break;
            case R.id.tv_photo_album:
                break;
            case R.id.tv_cancel:
                break;
            default:
                break;
        }
    }
}
