package com.example.wangning.canvas.chart.pie;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * 违反了里氏替换原则
 *
 * @author wangning
 * @version 1.0 2017-08-17
 * @since JDK 1.8
 */
public class PercentFormatterWarp extends PercentFormatter {
    private static final String TAG = "PercentFormatterWarp";

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        Log.e(TAG, "getFormattedValue: dataSetIndex=" + dataSetIndex);
        return ""+dataSetIndex;
    }
}
