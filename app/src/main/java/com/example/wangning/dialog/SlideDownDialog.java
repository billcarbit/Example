package com.example.wangning.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-07-17
 * @since JDK 1.8
 */
public class SlideDownDialog extends Dialog
        implements View.OnClickListener {

    private TextView tv_sales;
    private TextView tv_allot;
    private TextView tv_supply;
    private TextView tv_all;
    private int mAnchorX;
    private int mAnchorY;


    public SlideDownDialog(Context context, int anchorX, int anchorY) {
        super(context, R.style.add_dialog);
        mAnchorX = anchorX;
        mAnchorY = anchorY;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.x = mAnchorX;
        lp.y = mAnchorY;
        getWindow().setAttributes(lp);
        getWindow().setWindowAnimations(R.style.slide_down);
        getWindow().setGravity(Gravity.TOP);
        setContentView(R.layout.dialog_records);
        initView();
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (R.id.tv_sales == viewId) {

        } else if (R.id.tv_allot == viewId) {

        } else if (R.id.tv_supply == viewId) {

        } else if (R.id.tv_all == viewId) {

        }
    }

    private void initView() {
        tv_sales = (TextView) findViewById(R.id.tv_sales);
        tv_allot = (TextView) findViewById(R.id.tv_allot);
        tv_supply = (TextView) findViewById(R.id.tv_supply);
        tv_all = (TextView) findViewById(R.id.tv_all);

        tv_sales.setOnClickListener(this);
        tv_allot.setOnClickListener(this);
        tv_supply.setOnClickListener(this);
        tv_all.setOnClickListener(this);
    }


}
