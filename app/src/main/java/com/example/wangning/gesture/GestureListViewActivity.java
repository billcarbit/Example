package com.example.wangning.gesture;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/3.
 */
public class GestureListViewActivity extends Activity implements View.OnClickListener {
    TextView tv;
    LinearLayout ll_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_listview);

        ScrollView sv = (ScrollView) findViewById(R.id.sv);
        sv.smoothScrollTo(0, 0);

        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(this);
        ListView listView = (ListView) findViewById(R.id.listView);
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", "APPLE_" + i);
            map.put("image", R.mipmap.ic_launcher);
            data.add(map);
        }
        SimpleAdapter sAdapter = new SimpleAdapter(getApplicationContext(),
                data,
                R.layout.item,
                new String[]{"title", "image"},
                new int[]{R.id.title, R.id.image});
        listView.setAdapter(sAdapter);
    }


    @Override
    public void onClick(View v) {
        if (R.id.tv == v.getId()) {
        }
    }


}
