package com.example.wangning.edittext;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

/**
 * 文字个数间隔
 *
 * @author wangning
 * @version 1.0 2017-03-20
 * @since JDK 1.8
 */
public class SpaceIntervalFilter implements InputFilter {

    private static final String TAG = "SpaceIntervalFilter";

    private int mNum;//输入多少个字符出现一个空格

    public SpaceIntervalFilter(int num) {
        mNum = num;
    }

    @Override
    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
        String append = spanned.toString();
        Log.e(TAG, "filter: append" + append);
        Log.e(TAG, "filter: charSequence" + charSequence);
        if (charSequence != null && (charSequence + append).length()  > 4) {
          return charSequence+" ";
        }
    /*    if (charSequence != null && (charSequence + append).length() > mMaxLength) {
            return "";
        }*/
        return charSequence;
    }
}
