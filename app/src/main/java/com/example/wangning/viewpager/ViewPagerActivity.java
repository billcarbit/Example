package com.example.wangning.viewpager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-07-18
 * @since JDK 1.8
 */
public class ViewPagerActivity extends Activity {
    ViewPager viewPager;
    PagerTabStrip pagerTabStrip;
    List<String> titles;
    List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.main_viewtabpager);

        //准备 标题数据源
        titles = new ArrayList<String>();
        titles.add("第一页面");
        titles.add("第二页面");
        titles.add("第三页面");


        //准备数据源
        views = new ArrayList<View>();
        View view1 = View.inflate(this, R.layout.viewpager1, null);
        View view2 = View.inflate(this, R.layout.viewpager2, null);
        View view3 = View.inflate(this, R.layout.viewpager3, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);


        //设置 标题样式
        pagerTabStrip.setBackgroundColor(Color.GREEN);
        pagerTabStrip.setDrawFullUnderline(false);
        pagerTabStrip.setTextColor(Color.RED);
        pagerTabStrip.setTabIndicatorColor(Color.BLACK);

        //创建 Adapter
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(views, titles);

        //添加适配器
        viewPager.setAdapter(pagerAdapter);

    }
}
