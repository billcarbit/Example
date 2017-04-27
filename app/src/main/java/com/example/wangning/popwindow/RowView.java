package com.example.wangning.popwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * Created by Administrator on 2017/4/28.
 */
public class RowView extends LinearLayout {

    private TextView tv_1, tv_2, tv_3, tv_4;

    public RowView(Context context) {
        this(context, null);
    }

    public RowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.item_row_view, this);
        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_3 = (TextView) findViewById(R.id.tv_3);
        tv_4 = (TextView) findViewById(R.id.tv_4);
    }

    public void setItem1Text(String text) {
        tv_1.setText(text);
    }

    public void setItem2Text(String text) {
        tv_2.setText(text);
    }

    public void setItem3Text(String text) {
        tv_3.setText(text);
    }

    public void setItem4Text(String text) {
        tv_4.setText(text);
    }
    //
    public CharSequence getItem1Text() {
        return  tv_1.getText();
    }

    public CharSequence getItem2Text() {
        return  tv_2.getText();
    }

    public CharSequence getItem3Text() {
        return  tv_3.getText();
    }

    public CharSequence getItem4Text() {
        return  tv_4.getText();
    }


    public void setItem1visibility(int visibility) {
        tv_1.setVisibility(visibility);
    }

    public void setItem2visibility(int visibility) {
        tv_2.setVisibility(visibility);
    }

    public void setItem3visibility(int visibility) {
        tv_3.setVisibility(visibility);
    }

    public void setItem4visibility(int visibility) {
        tv_4.setVisibility(visibility);
    }
}
