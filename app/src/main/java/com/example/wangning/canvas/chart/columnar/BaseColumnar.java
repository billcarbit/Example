package com.example.wangning.canvas.chart.columnar;

import com.example.wangning.R;

/**
 * 基础柱状
 *
 * @author wangning
 * @version 1.0 2017-09-11
 * @since JDK 1.8
 */
public class BaseColumnar {
    private int color = R.color.green_00b6aa;
    private float width;
    private float height;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

}
