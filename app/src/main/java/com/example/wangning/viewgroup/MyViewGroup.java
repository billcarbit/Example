package com.example.wangning.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/8/4.
 */
public class MyViewGroup extends ViewGroup {
    private static final String TAG = "MyViewGroup";
    private Context mContext;

    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure: ");
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).measure(0, 0);
            int height = getChildAt(i).getMeasuredHeight();
            Log.e(TAG, "onMeasure: height=" + height);
        }
        LayoutParams lp = getLayoutParams();
        lp.height = 100;
        setLayoutParams(lp);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e(TAG, "onLayout: l=" + l + ",t=" + t + ",r=" + r + ",b=" + b);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(l, t + 100 * i, r, b);
        }


    }


}
