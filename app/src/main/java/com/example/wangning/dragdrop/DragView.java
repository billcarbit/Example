package com.example.wangning.dragdrop;

import android.annotation.SuppressLint;
import android.content.ClipDescription;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by KID on 2017/11/14.
 * 随意拖动的view
 */

@SuppressLint("AppCompatCustomView")
public class DragView extends ImageView {

    private int width;
    private int height;
    private int screenWidth;
    private int screenHeight;
    private Context context;

    //是否拖动
    private boolean isDrag = false;

    public boolean isDrag() {
        return isDrag;
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

    public int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        return getResources().getDimensionPixelSize(resourceId);
    }


    private float downX;
    private float downY;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (this.isEnabled()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isDrag = false;
                    downX = event.getX();
                    downY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    final float xDistance = event.getX() - downX;
                    final float yDistance = event.getY() - downY;
                    //当水平或者垂直滑动距离大于10,才算拖动事件
                    if (Math.abs(xDistance) <= 10 && Math.abs(yDistance) <= 10) {
                        break;
                    }
                    int l, r, t, b;
                    isDrag = true;
                    l = (int) (getLeft() + xDistance);
                    r = l + width;
                    t = (int) (getTop() + yDistance);
                    b = t + height;

                    if (l < 0) {
                        l = 0;
                        r = l + width;
                    } else if (r > screenWidth) {
                        r = screenWidth;
                        l = r - width;
                    }
                    if (t < 0) {
                        t = 0;
                        b = t + height;
                    } else if (b > screenHeight) {
                        b = screenHeight;
                        t = b - height;
                    }
                    this.layout(l, t, r, b);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
            }
            return true;
        }
        return false;
    }
}