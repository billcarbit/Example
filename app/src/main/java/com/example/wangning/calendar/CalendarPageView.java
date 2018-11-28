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
import com.example.wangning.recyclerview.CommonItemDecoration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日历页面
 */
public class CalendarPageView extends FrameLayout {

    private RecyclerView mRecyclerView;
    private CalendarAdapter mCalendarAdapter;
    private List<DayItem> mList = new ArrayList<>();
    private String currentMonth;



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
        mCalendarAdapter = new CalendarAdapter(getContext(), mList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 7);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        // 绑定recyclerView
        mRecyclerView.setAdapter(mCalendarAdapter);
        GridItemDecoration divider = new GridItemDecoration();
        mRecyclerView.addItemDecoration(divider);
    }


    public void createData(Date date) {
        CalenderManager calenderManager = new CalenderManager(date);
        calenderManager.createCurrMonthDate();
        calenderManager.createLastMonthDate();
        calenderManager.createPreMonthDate();
        List<DayItem> dayItemList = calenderManager.combineAll();

        mList.addAll(dayItemList);
        mCalendarAdapter.notifyDataSetChanged();
    }


    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }
}
