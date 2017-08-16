package com.example.wangning.canvas.chart.line;

import java.util.List;

/**
 * 线路径
 *
 * @author wangning
 * @version 1.0 2017-08-16
 * @since JDK 1.8
 */
public class PathLine extends BaseLine {
    private List<Coordinate> coordinateList;

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }
}
