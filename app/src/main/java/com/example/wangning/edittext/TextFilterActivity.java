package com.example.wangning.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-20
 * @since JDK 1.8
 */
public class TextFilterActivity extends Activity {
    private static final String TAG = "TextFilterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textfilter);

        NumSpaceEditText bankNumEditText = (NumSpaceEditText) findViewById(R.id.bankCardNum);
        SpannableString s = new SpannableString(bankNumEditText.getHint());
        AbsoluteSizeSpan textSize = new AbsoluteSizeSpan(50, true);
        s.setSpan(textSize, 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        bankNumEditText.setHint(s);
        bankNumEditText.setMaxLength(27).setOffset(5);

    }
}
