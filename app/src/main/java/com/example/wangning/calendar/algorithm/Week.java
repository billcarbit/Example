package com.example.wangning.calendar.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Week {
    public List<WeekBean> mWeekList = new ArrayList<WeekBean>();
    public String[] weekArray = {"日", "一", "二", "三", "四", "五", "六"};

    public Week() {
        WeekBean weekBean = null;
        for (int i = 0; i < 7; i++) {
            weekBean = new WeekBean();
            weekBean.setIndex(i);
            weekBean.setText(weekArray[i]);
            mWeekList.add(weekBean);
        }
    }

    public String getWeekName(int index) {
        return weekArray[index];
    }


    /**
     * 获取距离周六的天数
     * @param weekName
     * @return
     */
    public int getSatRangeAmount(String weekName) {
        if (weekName == null) {
            return 0;
        }
        switch (weekName) {
            case "日":
                return 6;
            case "一":
                return 5;
            case "二":
                return 4;
            case "三":
                return 3;
            case "四":
                return 2;
            case "五":
                return 1;
            case "六":
                return 0;
            default:
                return 0;
        }
    }


}
