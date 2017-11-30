package com.example.wangning.addview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.wangning.R;
import com.example.wangning.swipemenu.SwipeMenuLayout;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-11-30
 * @since JDK 1.8
 */
public class SalesItemView extends LinearLayout
        implements View.OnClickListener {

    private static final String TAG = "SalesItemView";
    RelativeLayout rlAddSales, rlContainer;
    LinearLayout llItem2, llItem3;
    Context mContext;
    SwipeMenuLayout sml2, sml3;
    TextView tvDel2, tvDel3;
    EditText et11, et12;

    public SalesItemView(Context context) {
        super(context);
    }

    public SalesItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_add_sales_container, this);
        initView();
        initData();
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId) {
            case R.id.rl_add_sales:
                View itemView = RelativeLayout.inflate(mContext, R.layout.layout_mansong_sales, null);
                if (llItem2.getChildCount() == 0) {//如果第2行没有，加上
                    llItem2.addView(itemView);
                    sml2.setVisibility(VISIBLE);
                } else if (llItem2.getChildCount() > 0 && llItem3.getChildCount() == 0) {//如果第2行有了，第3行没有，给第3行加,并隐藏添加按钮
                    llItem3.addView(itemView);
                    rlAddSales.setVisibility(GONE);
                    sml3.setVisibility(VISIBLE);
                }
                break;
            case R.id.tv_del2:
                llItem2.removeAllViews();
                sml2.setVisibility(GONE);
                sml2.quickClose();
                rlAddSales.setVisibility(VISIBLE);
                break;
            case R.id.tv_del3:
                llItem3.removeAllViews();
                sml3.setVisibility(GONE);
                sml3.quickClose();
                rlAddSales.setVisibility(VISIBLE);
                break;
            default:
                break;
        }
    }

    void initView() {
        sml2 = (SwipeMenuLayout) findViewById(R.id.sml2);
        sml3 = (SwipeMenuLayout) findViewById(R.id.sml3);
        tvDel2 = (TextView) findViewById(R.id.tv_del2);
        tvDel3 = (TextView) findViewById(R.id.tv_del3);

        et11 = (EditText) findViewById(R.id.et11);
        et12 = (EditText) findViewById(R.id.et12);

        rlAddSales = (RelativeLayout) findViewById(R.id.rl_add_sales);
        rlContainer = (RelativeLayout) findViewById(R.id.rl_container);
        llItem2 = (LinearLayout) findViewById(R.id.ll_item2);
        llItem3 = (LinearLayout) findViewById(R.id.ll_item3);
    }

    void initData() {
        rlAddSales.setOnClickListener(this);
        tvDel2.setOnClickListener(this);
        tvDel3.setOnClickListener(this);
    }

    public JSONArray getInputData() {
        JSONArray jsonArray = new JSONArray();
        String et11Val = et11.getText().toString();
        String et12Val = et12.getText().toString();
        JSONObject json1 = new JSONObject();
        json1.put("full", et11Val);
        json1.put("send", et12Val);




        return jsonArray;
    }
}
