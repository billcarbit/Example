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
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private Activity mActivity;
    private List<String> mList;
    private LayoutInflater mInflater;

    public RecyclerViewAdapter(Activity activity, List<String> stringList) {
        mActivity = activity;
        mList = stringList;
        mInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_FOOTER:
                return new LoadMoreViewHolder(mInflater.inflate(
                        R.layout.activity_item_load_more, parent, false));
            default:
                return new NormalViewHolder(mInflater.inflate(
                        R.layout.item_recyclerview, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }


    class NormalViewHolder extends MyViewHolder {
        TextView tv_head;

        public NormalViewHolder(View view) {
            super(view);
            tv_head = (TextView) view.findViewById(R.id.tv_head);
        }

        @Override
        public void update(int position) {
            tv_head.setText(mList.get(position));
        }
    }

    class LoadMoreViewHolder extends MyViewHolder {
        public LoadMoreViewHolder(View view) {
            super(view);
        }

        @Override
        public void update(int position) {

        }
    }

    abstract class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }

        abstract void update(int position);
    }
}
