package com.example.wangning.calendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarView extends LinearLayout {

    private ViewPager mViewPager;
    private List<CalendarPageView> mCalendarPageViewList = new ArrayList<>();
    private TextView tvMonth;
    private MyPagerAdapter mMyPagerAdapter;
    private SimpleDateFormat sdfM = new SimpleDateFormat("MM月");
    private SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");


    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_calendar, this);
        mViewPager = findViewById(R.id.vp);
        tvMonth = findViewById(R.id.tv_month);
        mMyPagerAdapter = new MyPagerAdapter(mCalendarPageViewList);
        mViewPager.setAdapter(mMyPagerAdapter);//给ViewPager设置适配器
        mViewPager.addOnPageChangeListener(new OnPageChangeListenerImpl());
        fillData();
    }

    private void fillData() {
        CalendarPageView calendarPageView;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdfMM = new SimpleDateFormat("MM");
        String currentMonthStr = sdfMM.format(calendar.getTime());
        int currentMonth = Integer.parseInt(currentMonthStr);
        for (int i = 0; i < 12; i++) {
            calendar.set(Calendar.MONTH, i);
            calendarPageView = new CalendarPageView(getContext());
            calendarPageView.createData(calendar.getTime());
            calendarPageView.setCurrentMonth(sdfM.format(calendar.getTime()));
            mCalendarPageViewList.add(calendarPageView);
        }
        mMyPagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(currentMonth-1);
    }

    private class OnPageChangeListenerImpl implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tvMonth.setText(mCalendarPageViewList.get(position).getCurrentMonth());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    //ViewPager适配器
    private class MyPagerAdapter extends PagerAdapter {
        private List<CalendarPageView> mViewList;

        public MyPagerAdapter(List<CalendarPageView> viewList) {
            this.mViewList = viewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();//页卡数
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;//官方推荐写法
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));//添加页卡
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));//删除页卡
        }

    }


}
