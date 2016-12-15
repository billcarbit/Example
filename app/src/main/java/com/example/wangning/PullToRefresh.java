package com.example.wangning;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/12/15.
 */
public class PullToRefresh extends LinearLayout {

    public PullToRefresh(Context context) {
        this(context, null);
    }

    public PullToRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("dispatchTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                float y = ev.getY();
                Log.e("dispatchTouchEvent", "ACTION_MOVE,y=" + y);
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("dispatchTouchEvent", "ACTION_CANCEL");
            case MotionEvent.ACTION_UP:
                Log.e("dispatchTouchEvent", "ACTION_UP");
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("onInterceptTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                float y = ev.getY();
                Log.e("onInterceptTouchEvent", "ACTION_MOVE,y=" + y);
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("onInterceptTouchEvent", "ACTION_CANCEL");
            case MotionEvent.ACTION_UP:
                Log.e("onInterceptTouchEvent", "ACTION_UP");
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("onTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                float y = ev.getY();
                Log.e("onTouchEvent", "ACTION_MOVE,y=" + y);
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("onTouchEvent", "ACTION_CANCEL");
            case MotionEvent.ACTION_UP:
                Log.e("onTouchEvent", "ACTION_UP");
                break;
            default:
                break;
        }

        return true;
    }
}
