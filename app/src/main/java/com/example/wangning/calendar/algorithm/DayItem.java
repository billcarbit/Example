package com.example.wangning.calendar.algorithm;

/**
 * 日历网格页面中每个元素对象
 */
public class DayItem {
    private String text;
    private String date;
    private boolean enable;
    private boolean isSelected;
    private int status;//状态(1:正常 2:未签到 3:异常)

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
