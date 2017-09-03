package com.example.wangning.scrollview;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-16
 * @since JDK 1.8
 */
public class ScrollViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        ListView listView =  (ListView)findViewById(R.id.listView);

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", "APPLE");
            map.put("image", R.mipmap.ic_launcher);
            data.add(map);
        }
        SimpleAdapter sAdapter = new SimpleAdapter(getApplicationContext(),
                data,
                R.layout.item,
                new String[]{"title","content","check","check1","image"},
                new int[]{R.id.title, R.id.image});
        listView.setAdapter(sAdapter);
    }
}
