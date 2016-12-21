package com.example.wangning.pulltorefresh;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * Created by Administrator on 2016/12/15.
 */
public class PullToRefresh extends LinearLayout {
    private Context mContext;
    private View mHeadView;
    private TextView mTvHead;
    private int mTouchSlop;
    private int mStartY;

    public PullToRefresh(Context context) {
        this(context, null);
        mContext = context;
    }

    public PullToRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initHead();
    }

    private void initHead() {
        mHeadView = LayoutInflater.from(mContext).inflate(R.layout.header_view, null);
        mTouchSlop = ViewConfiguration.get(mContext).getScaledTouchSlop();
        mTvHead = (TextView) mHeadView.findViewById(R.id.tv_head);
        addView(mHeadView,0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("dispatchTouchEvent", "ACTION_DOWN");
                mStartY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int distance = (int) ev.getY() - mStartY;
                Log.e("ACTION_MOVE","canChildScrollUp()="+canChildScrollUp());
                if (distance>0 && distance > mTouchSlop) {
                    Log.e("ACTION_MOVE","AAAAAAAAAAAAAAAAA,mTouchSlop="+mTouchSlop);
                }
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


    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mHeadView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mHeadView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mHeadView, -1) || mHeadView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mHeadView, -1);
        }
    }




}
