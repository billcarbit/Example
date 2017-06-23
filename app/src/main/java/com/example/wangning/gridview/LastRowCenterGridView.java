package com.example.wangning.gridview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */
public class LastRowCenterGridView extends LinearLayout {
    LinearLayout ll_root;
    private List<String> mDataList = new ArrayList<String>();
    private int mNumColumns;
    private int mHorizontalSpacing, mVerticalSpacing;

    LastRowCenterGridView(Context context) {
        this(context, null);
    }

    LastRowCenterGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.last_row_center_gridview, this);
        ll_root = (LinearLayout) findViewById(R.id.ll_root);

        TypedArray lrcgvTypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.LastRowCenterGridView);
        mNumColumns = lrcgvTypedArray.getInt(R.styleable.LastRowCenterGridView_numColumns, 5);
        mHorizontalSpacing = lrcgvTypedArray.getInt(R.styleable.LastRowCenterGridView_horizontalSpacing, 5);
        mVerticalSpacing = lrcgvTypedArray.getInt(R.styleable.LastRowCenterGridView_verticalSpacing, 5);
    }

    public void setDataSource(List<String> list) {
        mDataList.clear();
        mDataList.addAll(list);
    }

    public void createView() {
        int rowsNum = rowsOf(mDataList.size(), mNumColumns);
        if (mNumColumns >= mDataList.size()) {//一行
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

            for (int i = 0; i < mDataList.size(); i++) {
                TextView textView = new TextView(getContext());
                textView.setBackgroundColor(R.color.white);
                textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                textView.setText("A"+i);
                textView.setTextColor(R.color.black);

                linearLayout.addView(textView);
            }
            ll_root.addView(linearLayout);

        } else {//多行
            for (int j = 0; j < rowsNum - 1; j++) {//先把满行的加入
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                for (int k = 0; k < mNumColumns; k++) {//满行
                    TextView textView = new TextView(getContext());
                    textView.setBackgroundColor(R.color.white);
                    textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    textView.setText(j + "B" + k);
                    textView.setTextColor(R.color.black);
                    linearLayout.addView(textView);
                }
                ll_root.addView(linearLayout);
            }

            //最后加入不足一行的，也就是最后一行
            for (int j = 0; j < mDataList.size() % mNumColumns; j++) {
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

                TextView textView = new TextView(getContext());
                textView.setBackgroundColor(R.color.white);
                textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                textView.setText("C" + j);
                textView.setTextColor(R.color.black);

                linearLayout.addView(textView);
                ll_root.addView(linearLayout);
            }
        }

    }


    /**
     * 已知总数量和列数求行数
     *
     * @param size    总数量
     * @param columns 列数
     * @return 计算得到的行数
     */
    private int rowsOf(int size, int columns) {
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


}
