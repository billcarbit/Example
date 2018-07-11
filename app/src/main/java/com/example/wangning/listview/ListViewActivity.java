package com.example.wangning.listview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */
public class ListViewActivity extends Activity {
    private static final String TAG = "ListViewActivity";
    ListView lv;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        lv = (ListView) findViewById(R.id.lv);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        List<Item> list = new ArrayList<>();
        Item item;
        for (int i = 0; i < 20; i++) {
            item = new Item();
            item.setText("ITEM_" + i);
            item.setType(i % 2);
            list.add(item);
        }
        MyAdapter myAdapter = new MyAdapter(this, list);
        lv.setAdapter(myAdapter);

       /* lv.setOnScrollListener(new AbsListView.OnScrollListener() {
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
        });*/
    }
}
