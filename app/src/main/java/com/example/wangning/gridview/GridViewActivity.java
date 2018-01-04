package com.example.wangning.gridview;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;

import android.view.View;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */
public class GridViewActivity extends Activity {
    GridView mGv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        initView();
        initData();
    }

    void initView() {
        mGv = (GridView) findViewById(R.id.gv);
    }

    void initData() {
        List<String> list = new ArrayList<String>();
        for(int i=0;i<7;i++){
            list.add("A"+i);
        }
        mGv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int position) {
                return new Object();
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return convertView;
            }
        });
    }
}
