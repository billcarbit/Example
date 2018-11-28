package com.example.wangning.calendar.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalenderManager {

    public List<DayItem> mPreMonthDayList = new ArrayList();//当前日历页面中，上月日期列表
    public List<DayItem> mCurrMonthDayList = new ArrayList();//当前日历页面中，当月日期列表
    public List<DayItem> mLastMonthDayList = new ArrayList();//当前日历页面中，下月日期列表

    public SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
    public Date mCurrentDate;


    public CalenderManager(Date date) {
        mCurrentDate = date;
    }

    public List<DayItem> combineAll() {
        List list = new ArrayList();
        list.addAll(mPreMonthDayList);
        list.addAll(mCurrMonthDayList);
        list.addAll(mLastMonthDayList);
        return list;
    }



    /**
     * 获取给定日期的当月最大天数
     *
     * @param date
     * @return
     */
    public int getMaxDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int max = calendar.getActualMaximum(calendar.DAY_OF_MONTH);    //获取本月最大天数
        return max;
    }


    /**
     * 根据日期获取星期
     *
     * @param date
     * @return
     */
    public String getWeekByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new Week().getWeekName(calendar.get(calendar.DAY_OF_WEEK) - 1);
    }


    /**
     * 获取一个月的第一天
     *
     * @param date
     * @return
     */
    public Date getFirstDayOfMonth(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String dateStr = sdf.format(date);
        String firstDayOfMonth = dateStr + "-01";
        Date firstDayOfMonthDate = null;
        try {
            firstDayOfMonthDate = sdf.parse(firstDayOfMonth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return firstDayOfMonthDate;
    }


    public Date getLastDayOfMonth(Date date) {
        SimpleDateFormat sdfYM = new SimpleDateFormat("yyyy-MM");
        String dateStr = sdfYM.format(date);
        String lastDayOfMonth = dateStr + "-" + getMaxDayOfMonth(date);
        Date resultDate = null;
        try {
            resultDate = sdfYMD.parse(lastDayOfMonth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }



    /**
     * 创建当月日历中，上一个月的日期
     */
    public void createPreMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getFirstDayOfMonth(mCurrentDate));//设置为当月第一天的日期
        int amount = calendar.get(calendar.DAY_OF_WEEK) - 1;
        SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
        DayItem dayItem;
        for (int i = 0; i < amount; i++) {
            calendar.setTime(getFirstDayOfMonth(mCurrentDate));//设置为当月第一天的日期
            calendar.add(Calendar.DATE, i - amount);
            Date preMonthDate = calendar.getTime();
            String result = sdfDay.format(preMonthDate);
            String ymdDate = sdfYMD.format(preMonthDate);
            dayItem = new DayItem();
            dayItem.setText(String.valueOf(Integer.parseInt(result)));
            dayItem.setDate(ymdDate);
            mPreMonthDayList.add(dayItem);
            dayItem.setEnable(false);
        }
    }

    /**
     * 创建当月日历中，下一个月的日期
     */
    public void createLastMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getLastDayOfMonth(mCurrentDate));//设置为当月第一天的日期
        int amount = new Week().getSatRangeAmount(getWeekByDate(calendar.getTime()));//获得距离星期六的天数
        DayItem dayItem = null;
        SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
        for (int i = 0; i < amount; i++) {
            calendar.add(Calendar.DATE, 1);
            Date preMonthDate = calendar.getTime();
            String sdfDayStr = sdfDay.format(preMonthDate);
            dayItem = new DayItem();
            dayItem.setText(String.valueOf(Integer.parseInt(sdfDayStr)));//去除十位数的0
            dayItem.setDate(sdfYMD.format(preMonthDate));
            dayItem.setEnable(false);
            mLastMonthDayList.add(dayItem);
        }
    }

    public void createCurrMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mCurrentDate);
        int amount = calendar.getActualMaximum(calendar.DAY_OF_MONTH);//获取本月最大天数
        SimpleDateFormat sfYM = new SimpleDateFormat("yyyy-MM-");
        DayItem dayItem = null;
        for (int i = 0; i < amount; i++) {
            dayItem = new DayItem();
            dayItem.setText(String.valueOf(i + 1));
            String ymDate = sfYM.format(mCurrentDate);
            if (i < 9) {
                dayItem.setDate(ymDate + "0" + (i + 1));
            } else {
                dayItem.setDate(ymDate + (i + 1));
            }
            dayItem.setEnable(true);
            mCurrMonthDayList.add(dayItem);
        }
    }


    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse("2018-11-28");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CalenderManager calenderManager = new CalenderManager(date);
        calenderManager.createCurrMonthDate();
        calenderManager.createLastMonthDate();
        calenderManager.createPreMonthDate();

        List<DayItem> dayItemList = calenderManager.combineAll();
        for (DayItem item : dayItemList) {
            System.out.println(item.getText()+","+item.getDate());
        }

    }
}
