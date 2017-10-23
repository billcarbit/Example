package com.example.wangning.textswitcher;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-15
 * @since JDK 1.8
 */
public class TextSwitcherActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_switcher);
        CarouselTextView carouselTextView= (CarouselTextView)findViewById(R.id.ctv);
        List<String> list = new ArrayList<String>();
        list.add("AAA\n aaa \n aaaa1111");
        list.add("BBB");
        list.add("CCC");
        carouselTextView.getDataList().addAll(list);
        carouselTextView.start();
    }
}
