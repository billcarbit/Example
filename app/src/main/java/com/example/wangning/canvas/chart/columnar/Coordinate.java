package com.example.wangning.canvas.chart.columnar;

import java.math.BigDecimal;

/**
 * 坐标
 *
 * @author wangning
 * @version 1.0 2017-08-16
 * @since JDK 1.8
 */
public class Coordinate {
    private float x;//在画布中的位置x
    private float y;//在画布中的位置y

    private BigDecimal valX;//外部设置的横向的值
    private BigDecimal valY;//外部设置的纵向的值

    private float touchXStart;
    private float touchXEnd;
    private float touchYStart;
    private float touchYEnd;
    private boolean isSelected;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public float getTouchXStart() {
        return touchXStart;
    }

    public void setTouchXStart(float touchXStart) {
        this.touchXStart = touchXStart;
    }

    public float getTouchXEnd() {
        return touchXEnd;
    }

    public void setTouchXEnd(float touchXEnd) {
        this.touchXEnd = touchXEnd;
    }

    public float getTouchYStart() {
        return touchYStart;
    }

    public void setTouchYStart(float touchYStart) {
        this.touchYStart = touchYStart;
    }

    public float getTouchYEnd() {
        return touchYEnd;
    }

    public void setTouchYEnd(float touchYEnd) {
        this.touchYEnd = touchYEnd;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public BigDecimal getValX() {
        return valX;
    }

    public void setValX(BigDecimal valX) {
        this.valX = valX;
    }

    public BigDecimal getValY() {
        return valY;
    }

    public void setValY(BigDecimal valY) {
        this.valY = valY;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
