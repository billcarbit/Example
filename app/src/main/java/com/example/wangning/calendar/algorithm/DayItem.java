package com.example.wangning.calendar.algorithm;

/**
 * 日历网格页面中每个元素对象
 */
public class DayItem {
    private String text;
    private String date;
    private boolean enable;
    private boolean signed;//签到
    private boolean signError;//考勤异常

    public boolean isSignError() {
        return signError;
    }

    public void setSignError(boolean signError) {
        this.signError = signError;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
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
