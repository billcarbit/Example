package com.example.wangning.gridview;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;

import android.view.View;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */
public class GridViewActivity extends Activity {
    GridView mGv;
    MyAdapter mAdapter;
    List<String> list;
    Context mContext;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        mContext = this;
        initView();
        initData();
    }

    void initView() {
        mGv = (GridView) findViewById(R.id.gv);
    }

    void initData() {
        list = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {

            if (i == 1) {
                list.add("CWNCNWE0CNW0ECN0WEN0CNWE");
            }else{
                list.add("A" + i);
            }
        }
        mAdapter = new MyAdapter();
        mGv.setAdapter(mAdapter);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gridview, null);
                vh.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.tv_name.setText(getItem(position));
            return convertView;
        }

        class ViewHolder {
            TextView tv_name;
        }
    }
}
