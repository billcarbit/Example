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
    RelativeLayout rlAddSales;
    LinearLayout llContainer;
    Context mContext;
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
                View itemView = RelativeLayout.inflate(mContext, R.layout.item_sales_settings, null);
                if (llContainer.getChildCount() < 2) {
                    llContainer.addView(itemView, llContainer.getChildCount());
                }
                if (llContainer.getChildCount() == 2) {
                    rlAddSales.setVisibility(GONE);
                }
                initDelListener();
                break;
            default:
                break;
        }
    }

    void initDelListener() {
        int childCount = llContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView tvDel = (TextView) llContainer.getChildAt(i).findViewById(R.id.tv_del);
            tvDel.setOnClickListener(new DelClickListener(i));
        }
    }

    void initView() {
        et11 = (EditText) findViewById(R.id.et11);
        et12 = (EditText) findViewById(R.id.et12);
        rlAddSales = (RelativeLayout) findViewById(R.id.rl_add_sales);
        llContainer = (LinearLayout) findViewById(R.id.ll_container);

    }

    void initData() {
        rlAddSales.setOnClickListener(this);
        et11.setText("10");
        et11.setEnabled(false);
    }

    public JSONArray getInputData() {
        JSONArray jsonArray = new JSONArray();
        String et11Val = et11.getText().toString();
        String et12Val = et12.getText().toString();
        JSONObject json1 = new JSONObject();
        json1.put("full", et11Val);
        json1.put("send", et12Val);
        jsonArray.add(json1);

        for (int i = 0; i < llContainer.getChildCount(); i++) {
            EditText et1 = (EditText)llContainer.getChildAt(i).findViewById(R.id.et1);
            EditText et2 = (EditText)llContainer.getChildAt(i).findViewById(R.id.et2);
            JSONObject jo = new JSONObject();
            jo.put("full", et1.getText().toString());
            jo.put("send", et2.getText().toString());
            jsonArray.add(jo);
        }

        return jsonArray;
    }

    class DelClickListener implements View.OnClickListener {
        int mPosition;

        public DelClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View v) {
            if (mPosition < llContainer.getChildCount()) {
                llContainer.removeViewAt(mPosition);
            } else {
                llContainer.removeAllViews();
            }
            rlAddSales.setVisibility(VISIBLE);
        }
    }
}
