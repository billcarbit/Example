package com.example.wangning.textview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.wangning.R;

public class SpannableStringActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable);
        TextView tvContent = findViewById(R.id.tv_content);
        tvContent.setText("12万");
        String text = tvContent.getText().toString();
        SpannableString s = new SpannableString(text);
        if (text.endsWith("万") || text.endsWith("元")) {
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FF4040"));
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(24, true);
            s.setSpan(absoluteSizeSpan, 0, s.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            s.setSpan( foregroundColorSpan, 0, s.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tvContent.setText(s);

    }
}
