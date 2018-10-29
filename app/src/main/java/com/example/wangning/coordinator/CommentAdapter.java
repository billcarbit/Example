package com.example.wangning.coordinator;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;

import com.example.wangning.R;
import com.example.wangning.recyclerview.DividerItemDecoration;
import com.example.wangning.recyclerview.RecyclerViewOnScrollListener;

import java.util.List;

/**
 * Created by admin on 2018/9/8.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_REPLAY = 2;


    private Context mContext;
    private List<Comment> mList;

    public CommentAdapter(Context context, List<Comment> stringList) {
        mContext = context;
        mList = stringList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_FOOTER:
                return new LoadMoreViewHolder(LayoutInflater.from(mContext).inflate(
                        R.layout.activity_item_load_more, parent, false));
            case TYPE_REPLAY:
                return new ReplayViewHolder(LayoutInflater.from(mContext).inflate(
                        R.layout.activity_item_replayu, parent, false));
            default:
                return new NormalViewHolder(LayoutInflater.from(mContext).inflate(
                        R.layout.item_recyclerview, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.update(position);
    }


    class ReplayViewHolder extends MyViewHolder {

        private RecyclerView rv_replay;

        public ReplayViewHolder(View itemView) {
            super(itemView);
            rv_replay = (RecyclerView) itemView.findViewById(R.id.rv_replay);
        }

        @Override
        void update(int position) {
            List<Comment.Replay> replayList = mList.get(position).getReplayList();
            ReplayAdapter replayAdapter = new ReplayAdapter(mContext, replayList);
            rv_replay.setLayoutManager(new LinearLayoutManager(mContext));
            rv_replay.addOnScrollListener(new RecyclerViewOnScrollListener(new RecyclerViewOnScrollListener.OnLoadMoreListener() {
                @Override
                public void loadMore() {

                }
            }));
            // 绑定recyclerView
            rv_replay.setAdapter(replayAdapter);
            //设置Item增加、移除动画
            rv_replay.setItemAnimator(new DefaultItemAnimator());
            DividerItemDecoration divider = new DividerItemDecoration();
            rv_replay.addItemDecoration(divider);
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
            tv_head.setText(mList.get(position).getContent());
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

    @Override
    public int getItemViewType(int position) {

        // 最后一个item设置为footerView
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }else{
            return TYPE_ITEM;
        }

    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    abstract class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }

        abstract void update(int position);
    }


}
