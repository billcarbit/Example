package com.example.wangning.canvas.chart.columnar;

import java.util.List;

/**
 * Y轴
 *
 * @author wangning
 * @version 1.0 2017-09-11
 * @since JDK 1.8
 */
public class LineY extends BaseLine {
    private List<ScaleY> scaleYList;

    public List<ScaleY> getScaleYList() {
        return scaleYList;
    }

    public void setScaleYList(List<ScaleY> scaleYList) {
        this.scaleYList = scaleYList;
    }
}
