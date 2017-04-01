package com.example.wangning.textview;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.wangning.R;


/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-15
 * @since JDK 1.8
 */
public class TextActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        TextView tv_html_format = (TextView)findViewById(R.id.tv_html_format);
        String html_text = getString(R.string.html_text);
        tv_html_format.setText(Html.fromHtml(String.format(html_text, "123<br/>456")));

        TextView tv_format = (TextView)findViewById(R.id.tv_format);
        String text_format = getString(R.string.text_format);
        tv_format.setText(String.format(text_format, "123","456"));
    }
}
