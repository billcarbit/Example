package com.example.wangning.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */
public class FlowLayout extends ViewGroup {

    private int mHSpacing;//水平间距
    private int mVSpacing;//垂直间距
    private int mRowCount = 1;


    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);// 由attrs 获得 TypeArray
        mHSpacing = ta.getInteger(R.styleable.FlowLayout_h_spacing, 0);
        mVSpacing = ta.getInteger(R.styleable.FlowLayout_v_spacing, 0);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        View childView;
        for (int i = 0; i < childCount; i++) {
            childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("dddd", "onLayout: " );
        int mViewGroupWidth = getMeasuredWidth();  //容器宽度

        int mPainterPosX = 0;  //当前绘图X
        int mPainterPosY = 0;  //当前绘图Y
        int childCount = getChildCount();
        View childView;
        int width, height = 0;
        for (int i = 0; i < childCount; i++) {
            childView = getChildAt(i);
            width = childView.getMeasuredWidth();
            height = childView.getMeasuredHeight();

            //宽度大于容器宽度时，则移到下一行开始位置
            if (mPainterPosX + width > mViewGroupWidth) {
                mPainterPosX = 0;
                mPainterPosY = mPainterPosY + height + mVSpacing;
                mRowCount++;
            }
            //位置摆放
            childView.layout(mPainterPosX, mPainterPosY, mPainterPosX + width, mPainterPosY + height);
            //记录当前已经绘制到的横坐标位置
            mPainterPosX += width + mHSpacing;
        }

        //计算出高度
        LayoutParams lp = getLayoutParams();
        lp.height = height * mRowCount;
        setLayoutParams(lp);

    }


}
