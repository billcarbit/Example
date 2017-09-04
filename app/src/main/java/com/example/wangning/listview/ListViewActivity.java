package com.example.wangning.listview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */
public class ListViewActivity extends Activity {
    private static final String TAG = "ListViewActivity";
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        lv = (ListView) findViewById(R.id.lv);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("ITEM_" + i);
        }
        MyAdapter myAdapter = new MyAdapter(this,list);
        lv.setAdapter(myAdapter);

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e(TAG, "onScrollStateChanged: scrollState="+scrollState );
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.e(TAG, "onScroll: firstVisibleItem=" + firstVisibleItem + "," +
                        "visibleItemCount=" + visibleItemCount
                        + ",totalItemCount=" + totalItemCount);
            }
        });
    }
}
