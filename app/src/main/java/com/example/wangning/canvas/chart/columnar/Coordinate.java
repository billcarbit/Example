package com.example.wangning.canvas.chart.columnar;

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

    private float valX;//外部设置的横向的值
    private float valY;//外部设置的纵向的值

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

    public float getValX() {
        return valX;
    }

    public void setValX(float valX) {
        this.valX = valX;
    }

    public float getValY() {
        return valY;
    }

    public void setValY(float valY) {
        this.valY = valY;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
