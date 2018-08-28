package com.example.wangning.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.wangning.R;


/**
 * Created by Administrator on 2017/1/4.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (position < state.getItemCount() - 3) {
            outRect.set(outRect.left, outRect.top, outRect.right, 10);
        }
        if (position % 2 == 0 && position != state.getItemCount()-1) {
            outRect.set(outRect.left, outRect.top, 10, outRect.bottom);
        }


        Log.e("AAA", "getItemOffsets:,  state.getItemCount()=" + state.getItemCount() + ",position=" + position);
    }
}