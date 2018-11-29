package com.example.wangning.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.wangning.R;
import com.example.wangning.calendar.algorithm.DayItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CalendarActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendarView = findViewById(R.id.calendar);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calendarView.fillData(sdf.parse("2017-9-22"), sdf.parse("2018-12-22"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendarView.setOnDateItemClickListener(new OnDateItemClickListener() {
            @Override
            public void onDateItemClick(DayItem item) {
                Log.e("AA", "onDateItemClick: item"+item.getDate() );
            }
        });
    }
}
