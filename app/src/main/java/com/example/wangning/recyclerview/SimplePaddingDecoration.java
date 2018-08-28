package com.example.wangning.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/8/28.
 */
public class SimplePaddingDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private Paint leftPaint;
    private Context mContext;

    public SimplePaddingDecoration(Context context) {
        mContext = context;
        dividerHeight = 30;
        leftPaint = new Paint();
        leftPaint.setColor(mContext.getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;//类似加了一个bottom padding

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            float left = child.getLeft();
            float right = left + 80;
            float top = child.getTop();
            float bottom = child.getBottom();
            c.drawRect(left, top, right, bottom, leftPaint);
        }

    }
}

