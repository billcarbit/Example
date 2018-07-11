package com.example.wangning;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/12/28.
 */
public class ToastUtils {
    public static void show(Context context, String text) {
        if (!TextUtils.isEmpty(text) && context != null && context.getApplicationContext() != null) {
            Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    public static void show(Context context, int strResId) {
        show(context.getApplicationContext(), context.getApplicationContext().getString(strResId));
    }
}
