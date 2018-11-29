package com.example.wangning.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.wangning.R;
import com.example.wangning.calendar.algorithm.DayItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        calendarView.setSourceDateMap(map);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calendarView.fillData(sdf.parse("2018-10-01"), sdf.parse("2018-11-22"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendarView.setOnDateItemClickListener(new OnDateItemClickListener() {
            @Override
            public void onDateItemClick(DayItem item) {
                Log.e("AA", "onDateItemClick: item" + item.getDate());
            }
        });


        calendarView.setOnPreLoadListener(new CalendarView.OnMonthLoadListener() {
            @Override
            public void onPrevMonthLoad(String firstYMDate) {

            }

            @Override
            public void onNextMonthLoad(String lastYMDate) {

            }

            @Override
            public void onCurrentMonthLoad() {
                DayItem dayItem = new DayItem();
                Map<String, DayItem> map = new HashMap<>();
                dayItem.setStatus(1);
                map.put("2018-11-29", dayItem);
                calendarView.setSourceDateMap(map);
            }
        });
    }
}
