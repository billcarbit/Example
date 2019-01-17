package com.example.wangning.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

public class SecondAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<FirstNode.SecondNode> mList;
    private Context mContext;

    public SecondAdapter(Context context, List<FirstNode.SecondNode> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class NormalViewHolder extends MyViewHolder {

        private TextView tv_head;

        public NormalViewHolder(View itemView) {
            super(itemView);
            tv_head = itemView.findViewById(R.id.tv_head);
        }

        @Override
        void update(final int position) {
            final FirstNode.SecondNode secondNode = mList.get(position);
            if (secondNode.isSelected()) {
                secondNode.setName("选中");
            }
            tv_head.setText(secondNode.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    secondNode.setSelected(true);
                    notifyItemChanged(position);
                }
            });
        }
    }
}
