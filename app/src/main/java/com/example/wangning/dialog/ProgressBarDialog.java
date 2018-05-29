package com.example.wangning.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.List;

/**
 * Created by Administrator on 2018/2/11.
 */
public class ProgressBarDialog extends Dialog {

    private static final String TAG = "ProgressBarDialog";


    public ProgressBarDialog(Context context) {
        super(context, R.style.add_dialog);
        Log.e(TAG, "ProgressBarDialog: ");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_loading);
        initView();
        initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
    }

    private void initView() {
        TextView tvLoading = (TextView) findViewById(R.id.tv_loading);
        tvLoading.setText("AAAAA");
    }

    private void initData() {

    }


}
