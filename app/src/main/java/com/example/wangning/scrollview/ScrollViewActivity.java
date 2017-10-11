package com.example.wangning.scrollview;

import android.app.Activity;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.wangning.R;
import com.example.wangning.viewpager.MyPagerAdapter;

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


        CustomViewPager main_viewpager = (CustomViewPager) findViewById(R.id.main_viewpager);
        PagerTabStrip main_viewtabpager = (PagerTabStrip) findViewById(R.id.main_viewtabpager);

        //准备 标题数据源
        List<String> titles = new ArrayList<String>();
        titles.add("第一页面");
        titles.add("第二页面");
        titles.add("第三页面");


        //准备数据源
        List<View> views = new ArrayList<View>();
        View view1 = View.inflate(this, R.layout.viewpager1, null);
        View view2 = View.inflate(this, R.layout.viewpager2, null);
        View view3 = View.inflate(this, R.layout.viewpager3, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);


        //设置 标题样式
        main_viewtabpager.setBackgroundColor(Color.GREEN);
        main_viewtabpager.setDrawFullUnderline(false);
        main_viewtabpager.setTextColor(Color.RED);
        main_viewtabpager.setTabIndicatorColor(Color.BLACK);

        //创建 Adapter
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(views, titles);

        //添加适配器
        main_viewpager.setAdapter(pagerAdapter);

    }
}
