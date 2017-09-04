package com.example.wangning.gesture;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/3.
 */
public class GestureListViewActivity extends Activity {
    GestureDetector mGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_listview);
        mGestureDetector = new GestureDetector(this, new LearnGestureListener());
        ListView listView =  (ListView)findViewById(R.id.listView);

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", "APPLE_"+i);
            map.put("image", R.mipmap.ic_launcher);
            data.add(map);
        }
        SimpleAdapter sAdapter = new SimpleAdapter(getApplicationContext(),
                data,
                R.layout.item,
                new String[]{"title","image"},
                new int[]{R.id.title, R.id.image});
        listView.setAdapter(sAdapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event))
            return true;
        else
            return false;
    }


    private class LearnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent ev) {
            Log.d("onSingleTapUp", ev.toString());
            return true;
        }

        @Override
        public void onShowPress(MotionEvent ev) {
            Log.d("onShowPress", ev.toString());
        }

        @Override
        public void onLongPress(MotionEvent ev) {
            Log.d("onLongPress", ev.toString());
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("onScroll", "e1="+e1.getY()+",e2="+e2.getY());
            return true;
        }

        @Override
        public boolean onDown(MotionEvent ev) {
            Log.d("onDownd", ev.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("onFling", "velocityX="+velocityX+",velocityY="+velocityY);
            return true;
        }
    }
}
