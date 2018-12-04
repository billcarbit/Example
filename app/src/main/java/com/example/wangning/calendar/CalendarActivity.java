package com.example.wangning.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.wangning.R;
import com.example.wangning.calendar.algorithm.DayItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalendarActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        final CalendarView calendarView = findViewById(R.id.calendar);


        DayItem dayItem = new DayItem();
        Map<String, DayItem> map = new HashMap<>();
        dayItem.setStatus(1);
        map.put("2018-11-29", dayItem);
        dayItem = new DayItem();
        dayItem.setStatus(2);
        map.put("2018-11-28", dayItem);
        dayItem = new DayItem();
        dayItem.setStatus(3);
        map.put("2018-11-27", dayItem);
        calendarView.setSourceDateMap(map);

        calendarView.setSignedDayCount("19");

        calendarView.initDateRange(Calendar.getInstance().getTime(), 3);//当前时间的前后3年


        calendarView.setOnDateItemClickListener(new OnDateItemClickListener() {
            @Override
            public void onDateItemClick(DayItem item) {
                Log.e("AA", "onDateItemClick: item" + item.getDate());

            /*    item.setSelected(true);
                calendarView.notifyDaySelected();*/
            }
        });


        calendarView.setOnPreLoadListener(new CalendarView.OnMonthLoadListener() {
            @Override
            public void onCurrentMonthLoad(String ymDate) {
                Log.e("AAA", "onCurrentMonthLoad: ymDate=" + ymDate);
                DayItem dayItem = new DayItem();
                //Map<String, DayItem> map = new HashMap<>();
                //dayItem.setStatus(1);
                //map.put("2018-11-29", dayItem);
                //calendarView.setSourceDateMap(map);
            }
        });
    }
}
