package com.example.wangning.utils;

import android.content.Context;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoneyUtils {
    public static String rahToStrWanYuan(BigDecimal val) {
        if (val == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("#");
        String formatText = df.format(val);
        if (formatText.length() > 4) {
            formatText = val.multiply(new BigDecimal("0.0001")).setScale(0, BigDecimal.ROUND_HALF_UP) + "万";
        } else {
            formatText = formatText + "元";
        }
        return formatText;
    }
}
