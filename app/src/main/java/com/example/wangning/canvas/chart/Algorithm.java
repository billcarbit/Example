package com.example.wangning.canvas.chart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-12-11
 * @since JDK 1.8
 */
public class Algorithm {

    /**
     * 该算法利用等差数列通项公式 an = a1 + (n-1)*d
     *
     * @param dataList 数据源
     * @param scaleNum 想要生成的刻度数量
     * @param percent  最大数据所占Y轴比例 0~1
     * @return
     */
    public List<Integer> createYDataList(List<Float> dataList, int scaleNum, float percent) {
        List<Integer> yList = new ArrayList<>();
        int maxY = 0, minY = 0;
        if (dataList == null || dataList.size() == 0 || percent < 0 || percent > 1) {
            return yList;
        }
        float max = Collections.max(dataList);
        float min = Collections.min(dataList);

        float spanMax = max / percent;//高出最大数据 1-percent 的值

        //Y轴刻度最小值应该将min向下取整，最大值应该将spanMax向上取整
        minY = (int) min;
        maxY = (int) (spanMax + 0.9f);
        //已知等差数列首相 a1 为 minY，末项 an 为 maxY，项数为scaleNum,代入公式，求出公差d
        float d = (float) (maxY - minY) / (float) (scaleNum - 1);//由于得出的结果未必是正数，所以类型为float
        //由于Y轴刻度必须是正数，所以公差只能向上取整
        int D = (int) (d + 0.9f);
        //得出最终解
        for (int i = 0; i < scaleNum; i++) {
            yList.add(minY + i * D);
        }
        return yList;
    }


}
