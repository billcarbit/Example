package com.example.wangning.gesture;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-07-18
 * @since JDK 1.8
 */
public class GestureActivity extends Activity {
    GestureDetector mGestureDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        mGestureDetector = new GestureDetector(this, new LearnGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event))
            return true;
        else
            return false;
    }

    private class LearnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent ev) {
            Log.d("onSingleTapUp", ev.toString());
            return true;
        }

        @Override
        public void onShowPress(MotionEvent ev) {
            Log.d("onShowPress", ev.toString());
        }

        @Override
        public void onLongPress(MotionEvent ev) {
            Log.d("onLongPress", ev.toString());
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("onScroll", "e1="+e1.getY()+",e2="+e2.getY());
            return true;
        }

        @Override
        public boolean onDown(MotionEvent ev) {
            Log.d("onDownd", ev.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("onFling", "velocityX="+velocityX+",velocityY="+velocityY);
            return true;
        }
    }

}
