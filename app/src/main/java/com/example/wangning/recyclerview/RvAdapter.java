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
 * Created by Administrator on 2018/3/10.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    private List<String> mList;
    private Activity mActivity;

    public RvAdapter(Activity activity, List<String> stringList) {
        mList = stringList;
        mActivity = activity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder( LayoutInflater.from(mActivity).inflate(
                R.layout.item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public abstract class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }

        abstract void update(int position);
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

}
