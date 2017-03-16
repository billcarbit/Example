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
        TextView tv = (TextView)findViewById(R.id.tv);

        String html_text = getString(R.string.html_text);
        tv.setText(Html.fromHtml(String.format(html_text, "123<br/>456")));


    }
}
