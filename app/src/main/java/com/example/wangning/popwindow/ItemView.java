package com.example.wangning.popwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * Created by Administrator on 2017/4/27.
 */
public class ItemView extends RelativeLayout {
    private View mLayout;
    private TextView tv_name;

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        //mLayout = LayoutInflater.from(context).inflate(R.layout.item_gridview, this);
        mLayout = LayoutInflater.from(context).inflate(R.layout.item_sign_in_gift, this);
        tv_name = (TextView)findViewById(R.id.tv_name);
    }

    public View getLayout() {
        return mLayout;
    }

    public void setText(String text) {
        tv_name.setText(text);
    }

}
