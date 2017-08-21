package com.example.wangning.canvas.chart.pie;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-08-18
 * @since JDK 1.8
 */
public class LineSign {

    private String lineAboveText;
    private String lineBelowText;
    private int lineColor = R.color.text_333333;//折线颜色
    private float pointRadius = 10;//点的半径
    private float turnXLength = 50;//折线转折点距离起始点的x距离
    private float turnYLength = 50;//折线转折点距离起始点的y距离
    private float percent;
    private int increaseColor = R.color.red_ff4221;//增长颜色
    private int reduceColor = R.color.green_00b11e; //减少颜色
    private int increaseNum;//增长数量
    private int reduceNum;//减少数量
    private boolean isIncrease;//是否是增长

    public boolean isIncrease() {
        return isIncrease;
    }

    public void setIncrease(boolean increase) {
        isIncrease = increase;
    }

    public int getIncreaseNum() {
        return increaseNum;
    }

    public void setIncreaseNum(int increaseNum) {
        this.increaseNum = increaseNum;
    }

    public int getReduceNum() {
        return reduceNum;
    }

    public void setReduceNum(int reduceNum) {
        this.reduceNum = reduceNum;
    }

    public int getIncreaseColor() {
        return increaseColor;
    }

    public void setIncreaseColor(int increaseColor) {
        this.increaseColor = increaseColor;
    }

    public int getReduceColor() {
        return reduceColor;
    }

    public void setReduceColor(int reduceColor) {
        this.reduceColor = reduceColor;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public String getLineAboveText() {
        return lineAboveText;
    }

    public void setLineAboveText(String lineAboveText) {
        this.lineAboveText = lineAboveText;
    }

    public String getLineBelowText() {
        return lineBelowText;
    }

    public void setLineBelowText(String lineBelowText) {
        this.lineBelowText = lineBelowText;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public float getPointRadius() {
        return pointRadius;
    }

    public void setPointRadius(float pointRadius) {
        this.pointRadius = pointRadius;
    }

    public float getTurnXLength() {
        return turnXLength;
    }

    public void setTurnXLength(float turnXLength) {
        this.turnXLength = turnXLength;
    }

    public float getTurnYLength() {
        return turnYLength;
    }

    public void setTurnYLength(float turnYLength) {
        this.turnYLength = turnYLength;
    }
}
