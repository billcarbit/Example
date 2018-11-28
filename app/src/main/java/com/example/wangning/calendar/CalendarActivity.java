package com.example.wangning.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wangning.R;

public class CalendarActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendarView = findViewById(R.id.calendar);


    }
}
