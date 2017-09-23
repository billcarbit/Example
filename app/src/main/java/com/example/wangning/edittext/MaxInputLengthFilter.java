package com.example.wangning.edittext;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2016-12-14
 * @since JDK 1.8
 */
public class MaxInputLengthFilter implements InputFilter {

    private int mMaxLength;

    public MaxInputLengthFilter(int max) {
        mMaxLength = max;
    }


    @Override
    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
        String append = spanned.toString();
        if (charSequence != null && (charSequence + append).length() > mMaxLength) {
            return "";
        }
        return charSequence;
    }
}
