package com.example.wangning.decimalformat;

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
}
