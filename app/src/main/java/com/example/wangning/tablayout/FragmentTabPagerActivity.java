package com.example.wangning.tablayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/13.
 */
public class FragmentTabPagerActivity extends FragmentActivity {

    private TabLayout mTabLayout;
    private Fragment[] mFragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);
        mFragments = AssetBottomData.getFragments();
        initView();
    }


    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());
                // Tab 选中之后，改变各个Tab的状态
                for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                    View view = mTabLayout.getTabAt(i).getCustomView();
                    ImageView icon = (ImageView) view.findViewById(R.id.tab_content_image);
                    TextView text = (TextView) view.findViewById(R.id.tab_content_text);
                    if (i == tab.getPosition()) { // 选中状态
                        icon.setImageResource(AssetBottomData.mTabResPressed[i]);
                        text.setTextColor(getResources().getColor(R.color.blue0063f3));
                    } else {// 未选中状态
                        icon.setImageResource(AssetBottomData.mTabRes[i]);
                        text.setTextColor(getResources().getColor(R.color.black));
                    }
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // 提供自定义的布局添加Tab
        for (int i = 0; i < 2; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(AssetBottomData.getTabView(this, i)));
        }
    }

    private void onTabItemSelected(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = mFragments[0];
                break;
            case 1:
                fragment = mFragments[1];
                break;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment != null && !fragment.isAdded()) {
            ft.hide(fragment);
            Log.e("SASA", "onTabItemSelected: fragment="+fragment.toString() );
            ft.add(R.id.home_container, fragment);
        }


        for (Fragment item : mFragments) {
            ft.hide(item);
        }
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }
}
