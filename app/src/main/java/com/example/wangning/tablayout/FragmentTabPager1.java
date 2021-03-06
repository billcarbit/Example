package com.example.wangning.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/13.
 */
public class FragmentTabPager1 extends BaseFragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private Fragment mFragment1, mFragment2;
    private List<Fragment> mFragmentList = new ArrayList<>();//页卡视图集合

    @Override
    protected int getContentView() {
        return R.layout.fragment_pager1;
    }

    @Override
    protected void initView(View rootView) {
        mViewPager = (ViewPager) rootView.findViewById(R.id.vp);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tabs);


    }

    @Override
    protected void initData() {
        initPagerTab();
    }

    private void initPagerTab() {
        mFragment1 = new Fragment1_1();
        mFragment2 = new Fragment2_2();

        //添加页卡视图
        mFragmentList.add(mFragment1);
        mFragmentList.add(mFragment2);

        //添加页卡标题
        mTitleList.add("11");
        mTitleList.add("22");


        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        MyFragmentPagerAdapter mAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mTitleList, mFragmentList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器

    }

}
