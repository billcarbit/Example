package com.example.wangning.canvas.chart.line;

/**
 * 刻度基类
 *
 * @author wangning
 * @version 1.0 2017-08-15
 * @since JDK 1.8
 */
public class BaseScale {
    private float height = 5;//刻度高度
    private float width;//刻度宽度
    private float x;//刻度在画布中的X位置
    private float y;//刻度在画布中的Y位置

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
