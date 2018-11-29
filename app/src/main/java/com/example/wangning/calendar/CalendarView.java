package com.example.wangning.calendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;
import com.example.wangning.calendar.algorithm.DayItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日历自定义控件
 */
public class CalendarView extends LinearLayout implements OnDateItemClickListener {

    private ViewPager mViewPager;
    private List<CalendarPageView> mCalendarPageViewList = new ArrayList<>();
    private TextView tvMonth;
    private MyPagerAdapter mMyPagerAdapter;
    private SimpleDateFormat sdfM = new SimpleDateFormat("MM月");
    private SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
    private Date mBeginDate;//日历开始时间
    private Date mEndDate;//日历结束时间
    private OnDateItemClickListener mOnDateItemClickListener;


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
    }

    /**
     * 初始化日历数据
     */
    public void fillData(Date beginDate, Date endDate) {
        CalendarPageView calendarPageView;
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        SimpleDateFormat sdfYM = new SimpleDateFormat("yyyy-MM");
        calendar.setTime(beginDate);//设置开始时间
        String currentYMDate = sdfYM.format(currentDate);
        int monthRangCount = getMonthRangCount(beginDate, endDate);
        int currentYMIndex = 0;
        for (int i = 0; i < monthRangCount; i++) {
            if (i > 0) {
                calendar.add(Calendar.MONTH, 1);
            }
            calendarPageView = new CalendarPageView(getContext());
            calendarPageView.createData(calendar.getTime());
            calendarPageView.setCurrentMonth(sdfM.format(calendar.getTime()));
            String itemYMDate = sdfYM.format(calendar.getTime());
            if (TextUtils.equals(itemYMDate, currentYMDate)) {//如果遍历到当前年月，记录下标，用于默认选中当前月份
                currentYMIndex = i;
            }
            calendarPageView.setYearMonth(itemYMDate);
            calendarPageView.setOnDateItemClickListener(this);
            mCalendarPageViewList.add(calendarPageView);
        }
        mMyPagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(currentYMIndex);//默认选中当前月份
    }


    /**
     * 获取起止日期之间的的月份总数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    private int getMonthRangCount(Date beginDate, Date endDate) {
        Calendar before = Calendar.getInstance();//固定的某个日期
        before.setTime(beginDate);
        Calendar after = Calendar.getInstance();//当前的日期
        after.setTime(endDate);
        int result = after.get(Calendar.MONTH) - before.get(Calendar.MONTH);
        int month = (after.get(Calendar.YEAR) - before.get(Calendar.YEAR)) * 12;
        int num = result + month + 1;//包含开始月份，所以要+1
        return num;
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


    public void setBeginDate(Date beginDate) {
        mBeginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        mEndDate = endDate;
    }

    @Override
    public void onDateItemClick(DayItem item) {
        if (mOnDateItemClickListener != null) {
            mOnDateItemClickListener.onDateItemClick(item);
        }
    }


    public void setOnDateItemClickListener(OnDateItemClickListener listener) {
        mOnDateItemClickListener = listener;
    }

}
