package com.example.wangning.canvas.chart.line;

/**
 * 刻度基类
 *
 * @author wangning
 * @version 1.0 2017-08-15
 * @since JDK 1.8
 */
public class BaseScale {
    private int height = 5;//刻度高度
    private int width;//刻度宽度

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}
