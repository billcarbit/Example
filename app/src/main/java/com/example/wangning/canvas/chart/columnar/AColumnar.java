package com.example.wangning.canvas.chart.columnar;

import com.example.wangning.R;

/**
 * 自定义柱子对象
 *
 * @author wangning
 * @version 1.0 2017-09-11
 * @since JDK 1.8
 */
public class AColumnar extends BaseColumnar {

    private int topTextMargin = 4;//柱子顶部数字到柱子顶部的距离
    private int topTextColor = R.color.green_00b6aa;//顶部数字的颜色
    private int topTextSize = 10;//顶部数字文本大小
    private int data;//表示的值

    public int getTopTextMargin() {
        return topTextMargin;
    }

    public void setTopTextMargin(int topTextMargin) {
        this.topTextMargin = topTextMargin;
    }

    public int getTopTextColor() {
        return topTextColor;
    }

    public void setTopTextColor(int topTextColor) {
        this.topTextColor = topTextColor;
    }

    public int getTopTextSize() {
        return topTextSize;
    }

    public void setTopTextSize(int topTextSize) {
        this.topTextSize = topTextSize;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
