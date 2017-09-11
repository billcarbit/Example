package com.example.wangning.canvas.chart.columnar;

import java.util.List;

/**
 * X轴对象
 *
 * @author wangning
 * @version 1.0 2017-09-11
 * @since JDK 1.8
 */
public class LineX extends BaseLine{

    private List<ScaleX> scaleXList;

    public List<ScaleX> getScaleXList() {
        return scaleXList;
    }

    public void setScaleXList(List<ScaleX> scaleXList) {
        this.scaleXList = scaleXList;
    }
}
