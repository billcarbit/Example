package com.example.wangning.tabpage;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.wangning.R;
import com.example.wangning.fragment.Fragment2;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-20
 * @since JDK 1.8
 */
public class TabPageActivity extends FragmentActivity {

    private static final String TAG = "TabPageActivity";
    List<String> mTabTitleList = new ArrayList<String>();
    private List<Fragment2> mPdFragmentList = new ArrayList<Fragment2>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_page);
        initData();
       final TabPageIndicator mTabIndicator = (TabPageIndicator) findViewById(R.id.tab_indicator);
        ViewPager vpTab = (ViewPager) findViewById(R.id.tab_viewpager);
        vpTab.setOffscreenPageLimit(0);
        FragmentAdapter mTabsAdapter = new FragmentAdapter(getSupportFragmentManager(), mTabTitleList, mPdFragmentList);
        mTabsAdapter.setFragments(mTabTitleList, mPdFragmentList);
        vpTab.setAdapter(mTabsAdapter);
        mTabIndicator.setViewPager(vpTab);
        mTabIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: position=" + position);
                if (position >= mPdFragmentList.size()) {
                    return;
                }
                mTabIndicator.setCurrentItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        for (int i = 0; i < 3; i++) {
            mTabTitleList.add("A+" + i);
        }
        for (int i = 0; i < 3; i++) {
            Fragment2 aaa = new Fragment2();
            Bundle bundle = new Bundle();
            bundle.putString("a", "" + i);
            aaa.setArguments(bundle);
            mPdFragmentList.add(aaa);
        }
    }
}
