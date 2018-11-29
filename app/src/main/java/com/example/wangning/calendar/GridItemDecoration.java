package com.example.wangning.calendar;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int perItemWidth = parent.getMeasuredWidth() / 7;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        View childView = layoutManager.getChildAt(parent.getChildAdapterPosition(view));
        RecyclerView.LayoutParams childLayoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
        childLayoutParams.width = perItemWidth;
        childView.setLayoutParams(childLayoutParams);
    }
}
