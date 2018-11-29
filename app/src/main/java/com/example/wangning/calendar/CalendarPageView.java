package com.example.wangning.calendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.wangning.R;
import com.example.wangning.calendar.algorithm.CalenderManager;
import com.example.wangning.calendar.algorithm.DayItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 日历页面
 */
public class CalendarPageView extends FrameLayout implements OnDateItemClickListener {

    private RecyclerView mRecyclerView;
    private CalendarAdapter mCalendarAdapter;
    private List<DayItem> mDayList = new ArrayList<>();
    private String monthTitle;
    private String mYearMonth;//yyyy-MM
    private OnDateItemClickListener mOnDateItemClickListener;

    public CalendarPageView(@NonNull Context context) {
        this(context, null);
    }

    public CalendarPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_my_calendar, this);
        mRecyclerView = findViewById(R.id.rv);
        initRecyclerView();
    }


    private void initRecyclerView() {
        mCalendarAdapter = new CalendarAdapter(getContext(), mDayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 7);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        // 绑定recyclerView
        mRecyclerView.setAdapter(mCalendarAdapter);
        mCalendarAdapter.setOnDateItemClickListener(this);
        GridItemDecoration divider = new GridItemDecoration();
        mRecyclerView.addItemDecoration(divider);
    }


    /**
     * 根据年月，创建具体日期
     *
     * @param date      年月
     * @param statusMap 服务端返回的具体日期状态
     */
    public void createData(Date date, Map<String, DayItem> statusMap) {
        CalenderManager calenderManager = new CalenderManager(date);
        calenderManager.createCurrMonthDate(statusMap);
        calenderManager.createLastMonthDate();
        calenderManager.createPreMonthDate();
        List<DayItem> dayItemList = calenderManager.combineAll();
        mDayList.clear();
        mDayList.addAll(dayItemList);
        mCalendarAdapter.notifyDataSetChanged();
    }

    public String getMonthTitle() {
        return monthTitle;
    }

    public void setMonthTitle(String monthTitle) {
        this.monthTitle = monthTitle;
    }

    public String getYearMonth() {
        return mYearMonth;
    }

    public void setYearMonth(String yearMonth) {
        mYearMonth = yearMonth;
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
