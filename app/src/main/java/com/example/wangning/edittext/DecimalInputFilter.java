package com.example.wangning.edittext;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

/**
 * 浮点型小数位数过滤器
 *
 * @author shuaiqiangqiang
 * @version 1.0 2016-11-29
 * @since JDK 1.7
 */
public class DecimalInputFilter implements InputFilter {

    private int mDecimalNumber;

    private static final String POINTER = ".";   // 小数点
    private static final String EMPTY_VALUE = "";  // 空串

    /**
     * 构造方法
     *
     * @param decimalNumber 小数点后的位数
     */
    public DecimalInputFilter(int decimalNumber) {
        mDecimalNumber = decimalNumber;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (TextUtils.isEmpty(source)) {
            return null;  // 删除时，不作限制
        }
        if (TextUtils.isEmpty(dest) && TextUtils.equals(POINTER, source)) {
            return EMPTY_VALUE;  // 首位不允许输入小数点
        }
        if (!dest.toString().contains(POINTER)) {
            // 当原值不包含小数点时，不作限制
            return null;
        }
        int pointIndex = dest.toString().indexOf(POINTER);  // 小数点的位置
        if (dstart <= pointIndex) {
            return null;
        }
        if (dest.toString().substring(pointIndex).length() >= mDecimalNumber + 1) {
            return EMPTY_VALUE;  // 小数点后位数已达到限制值
        }
        return null;
    }
}
