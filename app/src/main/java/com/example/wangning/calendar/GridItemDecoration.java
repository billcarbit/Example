package com.example.wangning.calendar;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) % 7 == 0) {
            outRect.left = 0;
            outRect.right = 20;
        }else if(parent.getChildAdapterPosition(view) % 7 == 1){
            outRect.left = 20;
            outRect.right = 20;
        }
    }
}
