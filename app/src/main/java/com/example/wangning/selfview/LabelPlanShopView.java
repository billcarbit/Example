package com.example.wangning.selfview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.wangning.R;


/**
 * 店面类型label view
 *
 * @author wangning
 * @version 1.0 2017-06-28
 * @since JDK 1.8
 */
public class LabelPlanShopView extends LinearLayout {
    public LabelPlanShopView(Context context) {
        super(context);
    }

    public LabelPlanShopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_label_plan_shop, this);
    }
}
