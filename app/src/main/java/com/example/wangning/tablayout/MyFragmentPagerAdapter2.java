package com.example.wangning.tablayout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.view.ViewGroup;

import java.util.List;

public class MyFragmentPagerAdapter2 extends FragmentPagerAdapter {
    private List<String> mTabTitle;
    private List<Fragment> mFragments;


    public MyFragmentPagerAdapter2(FragmentManager fm, List<String> tabTitle, List<Fragment> fragments) {
        super(fm);
        mTabTitle = tabTitle;
        mFragments = fragments;

    }

    @Override
    public String getPageTitle(int position) {
        return mTabTitle.get(position);
    }


    public void setFragments(List<String> tabTitel, List<Fragment> fragments) {
        mTabTitle = tabTitel;
        mFragments = fragments;
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabTitle.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }
}
