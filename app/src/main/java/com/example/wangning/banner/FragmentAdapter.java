package com.example.wangning.banner;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-20
 * @since JDK 1.8
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<BannerFragment> mFragments;

    public FragmentAdapter(FragmentManager fm, List<BannerFragment> fragments) {
        super(fm);
        mFragments = fragments;

    }


    @Override
    public String getPageTitle(int position) {
        return null;
    }


    public void setFragments(List<BannerFragment> fragments) {
        mFragments = fragments;
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
