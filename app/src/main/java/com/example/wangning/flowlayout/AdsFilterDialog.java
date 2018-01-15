package com.example.wangning.flowlayout;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.wangning.R;


/**
 * 广告位筛选dialog
 * Created by Administrator on 2018/1/15.
 */
public class AdsFilterDialog extends Dialog
        implements View.OnClickListener {

    Context mContext;

    public AdsFilterDialog(Context context) {
        super(context, R.style.add_dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.activity_flow_layout);
        initView();
        initData();
    }

    void initView() {

    }

    void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
