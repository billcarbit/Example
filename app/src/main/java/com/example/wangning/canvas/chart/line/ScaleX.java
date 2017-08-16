package com.example.wangning.canvas.chart.line;

/**
 * X刻度
 *
 * @author wangning
 * @version 1.0 2017-08-15
 * @since JDK 1.8
 */
public class ScaleX extends BaseScale {
    private DataX dataX;//刻度对应的数据

    public DataX getDataX() {
        return dataX;
    }

    public void setDataX(DataX dataX) {
        this.dataX = dataX;
    }
}
