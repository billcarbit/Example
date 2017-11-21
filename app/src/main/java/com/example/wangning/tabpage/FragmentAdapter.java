package com.example.wangning.tabpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.wangning.fragment.Fragment2;

import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-20
 * @since JDK 1.8
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<String> mTabTitle;
    private List<Fragment2> mFragments;
    public FragmentAdapter(FragmentManager fm, List<String> tabTitle, List<Fragment2> fragments) {
        super(fm);
        mTabTitle = tabTitle;
        mFragments = fragments;

    }

    @Override
    public String getPageTitle(int position) {
        return mTabTitle.get(position);
    }



    public void setFragments(List<String> tabTitel, List<Fragment2> fragments) {
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
