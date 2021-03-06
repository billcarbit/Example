package com.example.wangning.banner;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 */

public class BannerAdapter extends PagerAdapter {

    private List<View> viewList;
    private int size;
    private final int cacheCount = 3;

    public BannerAdapter(List<View> viewList) {
        this.viewList = viewList;
        size = viewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (viewList.size() > cacheCount) {
            container.removeView(viewList.get(position % size));
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup parent = (ViewGroup) viewList.get(position % size).getParent();
        if (parent != null) {
            parent.removeView(viewList.get(position % size));
        }
        container.addView(viewList.get(position % size));
        return viewList.get(position % size);
    }

    @Override
    public int getCount() {
        if (viewList == null || viewList.size() == 0) {
            return 0;
        } else if (viewList.size() == 1) {
            return 1;
        } else if(viewList.size() == 2){
            return 2;
        }else {
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}