package com.example.wangning.abslistview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ScrollView;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2016-12-27
 * @since JDK 1.8
 */
public class AbsListViewActivity extends Activity {

    private static final String TAG = "AbsListViewActivity";
    private View target;
    private AAAView mA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abslistview);
        mA =(AAAView) findViewById(R.id.v_a);


    }

    @Override
    protected void onResume() {
        super.onResume();
        ensureTarget();
        Log.e(TAG, "onResume: canChildScrollDown="+canChildScrollDown() );
    }

    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (target instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) target;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(target, -1) || target.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(target, -1);
        }
    }

    public boolean canChildScrollDown() {
/*        if (target instanceof AbsListView) {
            AbsListView absListView = (AbsListView) target;
            return canScrollVertically(target, 1)
                    || absListView.getLastVisiblePosition() != ((AbsListView) target).getChildCount() - 1;
        } else if (target instanceof WebView) {
            WebView webview = (WebView) target;
            return canScrollVertically(target, 1)
                    || webview.getContentHeight() * webview.getScale() != webview
                    .getHeight() + webview.getScrollY();
        } else if (target instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) target;
            View childView = scrollView.getChildAt(0);
            if (childView != null) {
                return canScrollVertically(target, 1)
                        || scrollView.getScrollY() != childView.getHeight()
                        - scrollView.getHeight();
            }
        }else{
            return canScrollVertically(target, 1);
        }*/
        return  canScrollVertically(target, -1);
    }

    /**
     * 用来判断view在竖直方向上能不能向上或者向下滑动
     * @param view v
     * @param direction 方向    负数代表向上滑动 ，正数则反之
     * @return
     */
    public boolean canScrollVertically(View view, int direction) {
        return ViewCompat.canScrollVertically(view, direction);
    }

    private void ensureTarget() {
        // Don't bother getting the parent height if the parent hasn't been laid
        // out yet.
        if (target == null) {
            for (int i = 0; i < mA.getChildCount(); i++) {
                View child = mA.getChildAt(i);
                if (!child.equals(mA)) {
                    target = child;
                    break;
                }
            }
        }
    }
}
