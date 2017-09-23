package com.example.wangning.textview;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.widget.TextView;

import com.example.wangning.R;
import com.example.wangning.utils.MoneyUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-15
 * @since JDK 1.8
 */
public class TextActivity extends Activity {

    private static final String TAG = "TextActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        TextView tv_diff_size = (TextView) findViewById(R.id.tv_diff_size);
        String text = MoneyUtils.rahToStrWanYuan(null);
        Log.e(TAG, "onCreate: text="+text );
        SpannableString s = new SpannableString(text);
        if(text.endsWith("万") || text.endsWith("元")){
            s.setSpan(new AbsoluteSizeSpan(14, true), s.length()-1, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        tv_diff_size.setText(s);


        TextView tv_html_format = (TextView) findViewById(R.id.tv_html_format);
        String html_text = getString(R.string.html_text);
        tv_html_format.setText(Html.fromHtml(String.format(html_text, "123<br/>456")));

        TextView tv_format = (TextView) findViewById(R.id.tv_format);
        String text_format = getString(R.string.text_format);
        tv_format.setText(String.format(text_format, "123", "456"));
        tv_format.setText(String.format(getString(R.string.text_double), 1.12));


        TextView tv_html_color = (TextView) findViewById(R.id.tv_html_color);
        String wfc_text_payment = getString(R.string.wfc_text_payment);
        tv_html_color.setText(Html.fromHtml(String.format(wfc_text_payment, "AAA<")));

        HashMap<String, String> sa = objToMap(new A());
        Log.e("A", "onCreate: sa" + sa.toString());

    }

    public HashMap<String, String> objToMap(Object obj) {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        Class clazz = obj.getClass();
        List<Class> clazzs = new ArrayList<Class>();

        do {
            clazzs.add(clazz);
            clazz = clazz.getSuperclass();
        } while (!clazz.equals(Object.class));

        for (Class iClazz : clazzs) {
            Field[] fields = iClazz.getDeclaredFields();
            for (Field field : fields) {
                Object objVal = null;
                field.setAccessible(true);
                try {
                    objVal = field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                objVal = objVal == null ? "" : objVal;
                hashMap.put(field.getName(), objVal.toString());
            }
        }

        return hashMap;
    }
}
