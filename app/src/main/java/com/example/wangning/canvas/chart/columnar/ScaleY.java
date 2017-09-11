package com.example.wangning.canvas.chart.columnar;

import java.util.List;

/**
 * Y轴刻度
 *
 * @author wangning
 * @version 1.0 2017-09-11
 * @since JDK 1.8
 */
public class ScaleY extends BaseScale{
    private List<DataY> dataYList;

    public List<DataY> getDataYList() {
        return dataYList;
    }

    public void setDataYList(List<DataY> dataYList) {
        this.dataYList = dataYList;
    }
}
