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


        TextView tv_html_text = (TextView)findViewById(R.id.tv_html_text);
        String content = "<p style=\"margin-top: 0px; margin-bottom: 15px; border: 0px; outline: none; list-style: none; color: rgb(57, 57, 57); font-family: 微软雅黑; white-space: normal;\">&nbsp; &nbsp; &nbsp; 新华社北京9月6日电 国家主席习近平6日应约同美国总统特朗普通电话。</p><p style=\"margin-top: 0px; margin-bottom: 15px; border: 0px; outline: none; list-style: none; color: rgb(57, 57, 57); font-family: 微软雅黑; white-space: normal;\">　　习近平指出，当前，中美各领域交往与合作继续推进。两国外交安全团队和经济团队保持密切沟通，双方正在筹备首轮中美社会和人文对话、执法及网络安全对话。中方重视总统先生年内对中国的国事访问，希望双方团队共同努力，确保访问取得成功。</p><p style=\"margin-top: 0px; margin-bottom: 15px; border: 0px; outline: none; list-style: none; color: rgb(57, 57, 57); font-family: 微软雅黑; white-space: normal;\">　　特朗普表示，我同习近平主席保持密切沟通、就重大国际和地区问题加强协调十分重要。我期待着年内对中国进行国事访问并同习主席再次会晤。</p><p style=\"margin-top: 0px; margin-bottom: 15px; border: 0px; outline: none; list-style: none; color: rgb(57, 57, 57); font-family: 微软雅黑; white-space: normal;\">　　两国元首重点就当前朝鲜半岛局势交换了看法。习近平强调，中方坚定不移致力于实现朝鲜半岛无核化，维护国际核不扩散体系。同时，我们始终坚持维护朝鲜半岛和平稳定，坚持通过对话协商解决问题。要坚持和平解决的大方向，解决朝鲜半岛核问题，归根结底要靠对话谈判、综合施策，积极探寻长久解决之道。</p><p style=\"margin-top: 0px; margin-bottom: 15px; border: 0px; outline: none; list-style: none; color: rgb(57, 57, 57); font-family: 微软雅黑; white-space: normal;\">　　特朗普表示，美方对当前朝鲜半岛形势的发展深感关切，重视中方在解决朝核问题上的重要作用，愿加强同中方的沟通，尽早找到解决朝鲜半岛核问题的办法。</p><p><span style=\"white-space: nowrap;\"><br/></span></p>";//content=<p style="margin-top: 0px; margin-bottom: 15px; border: 0px; outline: none; list-style: none; color: rgb(57, 57, 57); font-family: 微软雅黑; white-space: normal;">&nbsp; &nbsp; &nbsp; 新华社北京9月6日电 国家主席习近平6日应约同美国总统特朗普通电话。</p><p style="margin-top: 0px; margin-bottom: 15px; border: 0px; outline: none; list-style: none; color: rgb(57, 57, 57); font-family: 微软雅黑; white-space: normal;">　　习近平指出，当前，中美各领域交往与合作继续推进。两国外交安全团队和经济团队保持密切沟通，双方正在筹备首轮中美社会和人文对话、执法及网络安全对话。中方重视总统先生年内对中国的国事访问，希望双方团队共同努力，确保访问取得成功。</p><p style="margin-top: 0px; margin-bottom: 15px; border: 0px; outline: none; list-style: none; color: rgb(57, 57, 57); font-family: 微软雅黑; white-space: normal;">　　特朗普表示，我同习近平主席保持密切沟通、就重大国际和地区问题加强协调十分重要。我期待着年内对中国进行国事访问并同习主席再次会晤。</p><p style="margin-top: 0px; margin-bottom: 15px; border: 0px; outline: none; list-style: none; color: rgb(57, 57, 57); font-family: 微软雅黑; white-space: normal;">　　两国元首重点就当前朝鲜半岛局势交换了看法。习近平强调，中方坚定不移致力于实现朝鲜半岛无核化，维护国际核不扩散体系。同时，我们始终坚持维护朝鲜半岛和平稳定，坚持通过对话协商解决问题。要坚持和平解决的大方向，解决朝鲜半岛核问题，归根结底要靠对话谈判、综合施策，积极探寻长久解决之道。</p><p style="margin-top: 0px; margin-bottom: 15px; border: 0px; outline: none; list-style: none; color: rgb(57, 57, 57); font-family: 微软雅黑; white-space: normal;">　　特朗普表示，美方对当前朝鲜半岛形势的发展深感关切，重视中方在解决朝核问题上的重要作用，愿加强同中方的沟通，尽早找到解决朝鲜半岛核问题的办法。</p><p><span style="white-space: nowrap;"><br/></span></p>
        tv_html_text.setText("富文本:"+Html.fromHtml(String.format(html_text,content)));


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
