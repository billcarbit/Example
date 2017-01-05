package com.example.wangning.recyclerview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private Activity mActivity;
    private List<String> mList;

    public RecyclerViewAdapter(Activity activity, List<String> stringList) {
        mActivity = activity;
        mList = stringList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mActivity).inflate(R.layout.item_recyclerview, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).tv_head.setText(mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size()+1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_head;
        public MyViewHolder(View view) {
            super(view);
            tv_head = (TextView) view.findViewById(R.id.tv_head);
        }
    }
}
