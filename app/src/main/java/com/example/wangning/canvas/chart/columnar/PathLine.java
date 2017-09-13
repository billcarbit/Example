package com.example.wangning.canvas.chart.columnar;

import java.util.List;

/**
 * 线路径
 *
 * @author wangning
 * @version 1.0 2017-09-12
 * @since JDK 1.8
 */
public class PathLine extends BaseLine {
    private  List<Coordinate> coordinateList;

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }
}
