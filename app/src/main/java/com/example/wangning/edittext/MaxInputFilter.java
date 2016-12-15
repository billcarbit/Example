package com.example.wangning.edittext;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

/**
 * 限制输入最大值的过滤器
 *
 * @author shuaiqiangqiang
 * @version 1.0 2016-11-29
 * @since JDK 1.7
 */
public class MaxInputFilter implements InputFilter {

    private double mMax;
    private static final String EMPTY_VALUE = "";  // 空串

    public MaxInputFilter(double _max) {
        mMax = _max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Log.e("MaxInputFilter","source="+source+",start="+start+",end="+end+",dest="+dest+",dstart="+dstart+",dend="+dend);
        String resultString = EMPTY_VALUE;  // 返回空串表示输入内容不起作用
        try {
            String inputResult;
            if (dstart >= dest.length()) {
                inputResult = dest.toString() + source.toString();
            } else if (dstart == dend) {
                String beginString = dest.toString().substring(0, dstart);
                String endString = dest.toString().substring(dstart);
                inputResult = beginString + source + endString;
            } else {
                String beginString = dest.toString().substring(0, dstart);
                String endString = dest.toString().substring(dend);
                inputResult = beginString + source + endString;
                resultString = dest.toString().substring(dstart, dend);
            }

            double d = Double.parseDouble(inputResult);
            if (d <= mMax) {
                // 当输入的内容小于最大值时，不作输入限制
                return null;  // 返回null表示不限制输入
            }
        } catch (NumberFormatException e) {
            return null; // 如果转换异常，不作限制
        }
        return resultString;
    }
}
