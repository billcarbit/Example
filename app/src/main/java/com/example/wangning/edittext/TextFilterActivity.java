package com.example.wangning.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.widget.EditText;

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

        EditText  editText =  (EditText)findViewById(R.id.et);
        SpannableString s = new SpannableString("请输入");
        AbsoluteSizeSpan textSize = new AbsoluteSizeSpan(10,true);
        s.setSpan(textSize,0,s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        editText.setHint(s);


        NumSpaceEditText bankNumEditText = (NumSpaceEditText) findViewById(R.id.bankCardNum);
       // bankNumEditText.setMyFilters(new InputFilter[]{new MaxInputLengthFilter(22)});
/*        bankNumEditText.setBankNameListener(new BankNumEditText.BankNameListener() {
            @Override
            public void success(String name) {

            }

            @Override
            public void failure() {

            }
        });*/

    }
}
