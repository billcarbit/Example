package com.example.wangning.coordinator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.List;

/**
 * Created by admin on 2018/9/8.
 */
public class ReplayAdapter extends RecyclerView.Adapter<ReplayAdapter.MyViewHolder> {


    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;


    private Context mContext;
    private List<Comment.Replay> mReplayList;

    public ReplayAdapter(Context context, List<Comment.Replay> stringList) {
        mContext = context;
        mReplayList = stringList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_FOOTER:
                return new LoadMoreViewHolder(LayoutInflater.from(mContext).inflate(
                        R.layout.activity_item_load_more, parent, false));
            default:
                return new NormalViewHolder(LayoutInflater.from(mContext).inflate(
                        R.layout.item_recyclerview, parent, false));
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


    class NormalViewHolder extends MyViewHolder {
        TextView tv_head;

        public NormalViewHolder(View view) {
            super(view);
            tv_head = (TextView) view.findViewById(R.id.tv_head);
        }

        @Override
        public void update(int position) {
            tv_head.setText(mReplayList.get(position).getContent());
        }
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return mReplayList.size() + 1;
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


    abstract class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }

        abstract void update(int position);
    }

}
