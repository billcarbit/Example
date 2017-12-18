package com.example.wangning.addview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.example.wangning.R;
import com.example.wangning.swipemenu.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-11-30
 * @since JDK 1.8
 */
public class SalesItemView2 extends LinearLayout
        implements View.OnClickListener {

    private static final String TAG = "SalesItemView";
    RelativeLayout rlAddSales;
    ListView listView;
    MyAdapter myAdapter;
    Context mContext;
    List<InputItem> mItemList = new ArrayList<InputItem>();

    public SalesItemView2(Context context) {
        super(context);
    }

    public SalesItemView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_add_sales_container2, this);
        initView();
        initData();
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId) {
            case R.id.rl_add_sales:
                if (mItemList.size() < 2) {
                    mItemList.add(new InputItem());
                    listView.setVisibility(VISIBLE);
                }
                if (mItemList.size() == 2) {
                    rlAddSales.setVisibility(GONE);
                } else {
                    rlAddSales.setVisibility(VISIBLE);
                }
                myAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    void initView() {
        rlAddSales = (RelativeLayout) findViewById(R.id.rl_add_sales);
        listView = (ListView) findViewById(R.id.list_view);

    }

    void initData() {
        rlAddSales.setOnClickListener(this);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }

    public JSONArray getInputData() {
        JSONArray jsonArray = new JSONArray();
        return jsonArray;
    }

    static class ViewHolder {
        public EditText et1, et2;
        public TextView tvDel;
        public SwipeMenuLayout sml;
    }

    class InputItem {
        String et1;
        String et2;

        public String getEt1() {
            return et1;
        }

        public void setEt1(String et1) {
            this.et1 = et1;
        }

        public String getEt2() {
            return et2;
        }

        public void setEt2(String et2) {
            this.et2 = et2;
        }
    }

    class MyAdapter extends BaseAdapter {

        public MyAdapter() {

        }

        @Override
        public int getCount() {
            return mItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return mItemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mItemList.get(position).hashCode();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sales_settings, null);
                vh.et1 = (EditText) convertView.findViewById(R.id.et1);
                vh.et2 = (EditText) convertView.findViewById(R.id.et2);
                vh.tvDel = (TextView) convertView.findViewById(R.id.tv_del);
                vh.sml = (SwipeMenuLayout) convertView.findViewById(R.id.sml);

                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }

            vh.et1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.e(TAG, "setEt1: " + s.toString());
                    mItemList.get(position).setEt1(s.toString());
                }
            });

            vh.et2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.e(TAG, "setEt2: " + s.toString());
                    mItemList.get(position).setEt2(s.toString());
                }
            });
            vh.tvDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    vh.sml.quickClose();
                    //TODO
                    rlAddSales.setVisibility(View.VISIBLE);
                    mItemList.remove(position);
                    myAdapter.notifyDataSetChanged();
                }
            });

            return convertView;
        }


    }
}
