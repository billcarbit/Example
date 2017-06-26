package com.example.wangning.slidedel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-06-26
 * @since JDK 1.8
 */
public class SlideDelLayout extends LinearLayout {
    private static final String TAG = "SlideDelLayout";

    public SlideDelLayout(Context context) {
        super(context);
    }

    public SlideDelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: ");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent:ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent:ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent:ACTION_MOVE ");
                break;
            default:
                break;
        }


        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "onInterceptTouchEvent: ");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: ");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent:ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent:ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent:ACTION_MOVE ");

                int x = (int)getX();
                int y = (int)getY();


                Log.e(TAG, "ACTION_MOVE: x="+getTranslationX());
                scrollTo(x,y);

                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
