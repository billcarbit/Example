package com.example.wangning.decimalformat;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-06-23
 * @since JDK 1.8
 */
public class DecimalFormatter {

    public static String formatToSepara(float data) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(data);
    }

    /**
     * 保留2位小数
     *
     * @param val
     * @return
     */
    public static String rahToStr(double val) {
        if (!Double.isNaN(val) && val != Double.NEGATIVE_INFINITY && val != Double.POSITIVE_INFINITY) {
            BigDecimal bd = new BigDecimal(val);
            val = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String value = decimalFormat.format(val);
//			if (value.endsWith(".00")) {
//				value = value.substring(0, value.indexOf(".00"));
//			}
            return value;
        }
        return "";
    }

    /**
     * 转换万元单位，保留2位小数
     *
     * @param val
     * @return
     */
    public static String rahToStr_Wan(double val) {
        String tempValStr = rahToStr(val);
//        if (tempValStr.length() >= 6){
//
//        }else {
//
//        }
        double value = 0;
        String valStr = tempValStr;
        try {
            value = Double.valueOf(tempValStr);
            if (value == 0) {
                valStr = "0.00";
            } else {
                DecimalFormat decimalFormat = new DecimalFormat(",##0.00");
                if (tempValStr.length() > 8) {
                    valStr = decimalFormat.format(value * 0.0001) + "万";
                } else {
                    valStr = decimalFormat.format(value);
                }

            }

        } catch (Exception e) {
            valStr = tempValStr;
        }
        return valStr;
    }
}
