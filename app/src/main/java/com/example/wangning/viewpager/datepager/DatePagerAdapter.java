package com.example.wangning.viewpager.datepager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-07-18
 * @since JDK 1.8
 */
public class DatePagerAdapter extends PagerAdapter {

    List<View> views;

    public DatePagerAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    /**
     * 实例化 一个 页卡
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 添加一个 页卡

        container.addView(views.get(position));

        return views.get(position);
    }

    /**
     * 销毁 一个 页卡
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 删除
        container.removeView(views.get(position));
    }

}
