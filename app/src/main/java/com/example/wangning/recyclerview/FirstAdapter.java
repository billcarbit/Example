package com.example.wangning.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.List;

public class FirstAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<FirstNode> mList;
    private Context mContext;

    public FirstAdapter(Context context, List<FirstNode> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_first, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class NormalViewHolder extends MyViewHolder {
        private TextView tv_first;
        private RecyclerView rv_second;

        public NormalViewHolder(View itemView) {
            super(itemView);
            tv_first = itemView.findViewById(R.id.tv_first);
            rv_second = itemView.findViewById(R.id.rv_second);
        }

        @Override
        void update(int position) {
            FirstNode firstNode = mList.get(position);
            tv_first.setText(firstNode.getName());
            rv_second.setLayoutManager(new GridLayoutManager(mContext, 3));
            rv_second.setAdapter(new SecondAdapter(mContext, firstNode.getSecondList()));
        }
    }
}
