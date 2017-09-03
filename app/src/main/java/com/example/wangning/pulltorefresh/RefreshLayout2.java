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
public class RefreshLayout2 extends ViewGroup {

    private static final float DRAG_RATE = 0.5f;
    private TextView mHeadText;
    private ImageView mIvRefreshPic;
    private AnimationDrawable mIvRefreshPicAnimate;
    private View refreshHeader;
    private View mHeadView;
    private OnPullDownListener mPullDownListener;//下拉监听
    private OnPullUpListener mPullUpListener;//上拉监听
    private float lastY;
    private View target;

    public RefreshLayout2(Context context) {
        this(context, null);
    }

    public RefreshLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
      /*  touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        autoScroll = new AutoScroll();*/
        // 添加默认的头部，先简单的用一个ImageView代替头部
        mHeadView = LayoutInflater.from(context).inflate(R.layout.home_refresh_head, null);
        mHeadText = (TextView) mHeadView.findViewById(R.id.tv_head);
        mIvRefreshPic = (ImageView) mHeadView.findViewById(R.id.iv_refresh_pic);
        mIvRefreshPicAnimate = (AnimationDrawable) mIvRefreshPic.getDrawable();
        setRefreshHeader(mHeadView);
    }

    public void setRefreshHeader(View view) {
        if (view != null && view != refreshHeader) {
            removeView(refreshHeader);
            // 为header添加默认的layoutParams
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 150);
                view.setLayoutParams(layoutParams);
            }
            refreshHeader = view;
            addView(refreshHeader);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }

        if (target == null) {
            ensureTarget();
        }
        if (target == null) {
            return;
        }

        // target铺满屏幕
        final View child = target;
        final int childLeft = getPaddingLeft();
        final int childTop = getPaddingTop();
        final int childWidth = width - getPaddingLeft() - getPaddingRight();
        final int childHeight = height - getPaddingTop() - getPaddingBottom();
        child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);

        // header放到target的上方，水平居中
        int refreshViewWidth = refreshHeader.getMeasuredWidth();
        refreshHeader.layout((width / 2 - refreshViewWidth / 2),
                20,
                (width / 2 + refreshViewWidth / 2),
                20);

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int actionMasked = ev.getActionMasked(); // support Multi-touch
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                Log.e("ACTION_DOWN", "dispatchTouchEvent: ACTION_DOWN" +
                        "y=" + ev.getY() +
                        "x=" + ev.getX());
                lastY = ev.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.e("ACTION_MOVE", "dispatchTouchEvent: ACTION_MOVE" +
                        "y=" + ev.getY() +
                        "x=" + ev.getX());

                float diffY = ev.getY() - lastY;
                Log.e("ACTION_MOVE", "diffY: " + diffY);

                LayoutParams lp = refreshHeader.getLayoutParams();
                lp.height = (int) diffY;
                refreshHeader.setLayoutParams(lp);
                //refreshHeader.setTop((int) diffY);
                //target.offsetTopAndBottom((int)(diffY * DRAG_RATE));
                invalidate();
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


    public RefreshLayout2 setPullDownListener(OnPullDownListener pullDownListener) {
        mPullDownListener = pullDownListener;
        return this;
    }

    public RefreshLayout2 setPullUpListener(OnPullUpListener pullUpListener) {
        mPullUpListener = pullUpListener;
        return this;
    }

    /**
     * 将第一个Child作为target
     */
    private void ensureTarget() {
        // Don't bother getting the parent height if the parent hasn't been laid
        // out yet.
        if (target == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (!child.equals(refreshHeader)) {
                    target = child;
                    break;
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (target == null) {
            ensureTarget();
        }

        // ----- measure target -----
        // target占满整屏
        target.measure(MeasureSpec.makeMeasureSpec(
                getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY));

        // ----- measure refreshView-----
        measureChild(refreshHeader, widthMeasureSpec, heightMeasureSpec);

    }


    public interface OnPullDownListener {
        void onRefresh();
    }

    public interface OnPullUpListener {
        void onRefresh();
    }
}
