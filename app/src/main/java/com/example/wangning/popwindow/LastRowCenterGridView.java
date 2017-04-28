package com.example.wangning.popwindow;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 最后一行居中的网格布局
 *
 * @author wangning
 * @version 1.0 2017-04-28
 * @since JDK 1.8
 */
public class LastRowCenterGridView extends LinearLayout {
    GridView mGv;
    LinearLayout mLlBottomContainer;
    List<String> mDataList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private int mNumColumns;

    public LastRowCenterGridView(Context context) {
        this(context, null);
    }

    public LastRowCenterGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.gridview_lastrowcenter, this);
        mGv = (GridView) findViewById(R.id.gv);
        mLlBottomContainer = (LinearLayout) findViewById(R.id.ll_bottom_container);
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mOnItemClickListener.onItemClick(view, i);
            }
        });
    }

    public void initData(List<String> list,int numColumns) {
        setNumColumns(numColumns);
        mDataList = list;
        if (mDataList == null || mDataList.size() == 0) {
            return;
        }
        int rowsNum = rowsOf(mDataList.size(), mNumColumns);
        if (mDataList.size() % mNumColumns != 0) {//最后一行不足4个
            final List<String> ll = new ArrayList<String>();
            for (int i = 0; i < (rowsNum - 1) * mNumColumns; i++) {
                ll.add(mDataList.get(i));
            }
            GridViewAdapter adapter = new GridViewAdapter(mContext, ll);
            mGv.setAdapter(adapter);
            for (int i = 0; i < mDataList.size() % mNumColumns; i++) {//最后一行余下的条目
                ItemView lastRowItemView = new ItemView(mContext);
                final int index = i;
                lastRowItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(v, (index + ll.size()));
                    }
                });
                lastRowItemView.setText(mDataList.get(i + ll.size()));
                mLlBottomContainer.addView(lastRowItemView);
            }
        } else {
            GridViewAdapter adapter = new GridViewAdapter(mContext, mDataList);
            mGv.setAdapter(adapter);
        }
    }

    public void setNumColumns(int numColumns) {
        mNumColumns = numColumns;
        mGv.setNumColumns(numColumns);
    }

    /**
     * 已知总数量和列数求行数
     *
     * @param size    总数量
     * @param columns 列数
     * @return 计算得到的行数
     */
    public int rowsOf(int size, int columns) {
        if (size < 1 || columns < 1)
            return 0;
        // 整除
        boolean isDivisible = (size % columns) == 0;
        if (isDivisible) {
            return size / columns;
        } else {
            return size / columns + 1;
        }
    }

    /**
     * 每个item的水平间距
     *
     * @param dp
     */
    public void setHorizontalSpacing(int dp) {
        if (mDataList == null || mDataList.size() == 0) {
            return;
        }
        mGv.setHorizontalSpacing(dp);

        /*for (int i = 0; i < mLlBottomContainer.getChildCount(); i++) {
            View childView = mLlBottomContainer.getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childView.getLayoutParams();
            layoutParams.setMargins(dp, 0, 0, 0);
            childView.setLayoutParams(layoutParams);
        }*/
        for (int i = 0; i < mLlBottomContainer.getChildCount(); i++) {
            ItemView childView = (ItemView) mLlBottomContainer.getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childView.getLayoutParams();
            layoutParams.setMargins(dp, 0, 0, 0);
            childView.setLayoutParams(layoutParams);
        }
    }

    /**
     * 每个item的垂直间距
     *
     * @param dp
     */
    public void setVerticalSpacing(int dp) {
        if (mDataList == null || mDataList.size() == 0) {
            return;
        }
        mGv.setVerticalSpacing(dp);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mLlBottomContainer.getLayoutParams();
        layoutParams.setMargins(0, dp, 0, 0);
        mLlBottomContainer.setLayoutParams(layoutParams);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
