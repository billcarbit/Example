package com.example.wangning.banner.gallery;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */
public class GalleryBannerAdapter extends PagerAdapter {
    private List<View> viewList;

    public GalleryBannerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }


    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      /*  if (viewList.size() > cacheCount) {
            container.removeView(viewList.get(position % size));
        }*/
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
