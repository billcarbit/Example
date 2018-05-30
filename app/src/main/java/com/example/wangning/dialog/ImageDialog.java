package com.example.wangning.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageView;

import com.example.wangning.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/30.
 */
public class ImageDialog extends Dialog {
    private ImageView iv_pic;

    public ImageDialog(Context context) {
        super(context, R.style.add_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_image);
        initView();
    }

    private void initView() {
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
    }


}
