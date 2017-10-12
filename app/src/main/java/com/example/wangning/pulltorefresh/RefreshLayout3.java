package com.example.wangning.pulltorefresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * Created by Administrator on 2017/8/29.
 */
public class RefreshLayout3 extends ViewGroup {

    private static final String TAG = "RefreshLayout3";

    public RefreshLayout3(Context context) {
        this(context, null);
    }

    public RefreshLayout3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;
        /**
         * 遍历所有childView根据其宽和高，以及margin进行布局
         */
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();

            int cl = 0, ct = 0, cr = 0, cb = 0;


            cr = cl + cWidth;
            cb = ct + cHeight;
            childView.layout(cl, ct, cr, cb);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        Log.e(TAG, "onMeasure: childCount=" + childCount);
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
                : 300, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
                : 300);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int actionMasked = ev.getActionMasked(); // support Multi-touch
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                Log.e("ACTION_DOWN", "dispatchTouchEvent: ACTION_DOWN" +
                        "y=" + ev.getY() +
                        "x=" + ev.getX());
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.e("ACTION_MOVE", "dispatchTouchEvent: ACTION_MOVE" +
                        "y=" + ev.getY() +
                        "x=" + ev.getX());

                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                Log.e("ACTION_UP", "dispatchTouchEvent: ACTION_UP" +
                        "y=" + ev.getY() +
                        "x=" + ev.getX());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;

            case MotionEvent.ACTION_POINTER_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
