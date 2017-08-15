package com.example.wangning.canvas.chart.line;

import java.util.ArrayList;
import java.util.List;

/**
 * X轴
 *
 * @author wangning
 * @version 1.0 2017-08-15
 * @since JDK 1.8
 */
public class LineX extends BaseLine {
    private List<ScaleX> scaleXList = new ArrayList<ScaleX>();

    public List<ScaleX> getScaleXList() {
        return scaleXList;
    }

    public void setScaleXList(List<ScaleX> scaleXList) {
        this.scaleXList = scaleXList;
    }
}
