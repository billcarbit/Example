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
    private int x;//刻度在画布中的X位置
    private int y;//刻度在画布中的Y位置

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
