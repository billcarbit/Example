package com.example.wangning.selfview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-07-11
 * @since JDK 1.8
 */
public class RedDotView extends LinearLayout {

    private TextView mTvNum;

    public RedDotView(Context context) {
        super(context);
    }

    public RedDotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_red_dot, this);
        mTvNum = (TextView) findViewById(R.id.tv_num);
    }

    public void setText(int num) {
        if (num > 9) {
            mTvNum.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_rect_dot));
            if (num > 99) {
                mTvNum.setText("99+");
            } else {
                mTvNum.setText(String.valueOf(num));
            }
        }else{
            mTvNum.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_oval_dot));
            mTvNum.setText(String.valueOf(num));
        }
    }


}
