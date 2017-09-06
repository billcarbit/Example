package com.example.wangning.canvas.chart.line;

/**
 * X,Y轴的数据基类
 *
 * @author wangning
 * @version 1.0 2017-08-15
 * @since JDK 1.8
 */
public class BaseData {
    //这4个值用于处理点击事件
    private float startX;//刻度数据在画布中的起始位置x
    private float startY;//刻度数据在画布中的起始位置y
    private float endX;//刻度数据在画布中的终止位置x
    private float endY;//刻度数据在画布中的终止位置y

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }
}
