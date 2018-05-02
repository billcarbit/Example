package com.example.wangning.letterSearch;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    private List<String> mList;
    private LayoutInflater mInflater;

    public RvAdapter(Activity activity, List<String> stringList) {
        mList = stringList;
        mInflater = LayoutInflater.from(activity);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mInflater.inflate(
                R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected abstract class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }

        abstract void update(int position);
    }

    protected class ItemViewHolder extends MyViewHolder {
        private TextView tv_name;

        public ItemViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
        }

        @Override
        public void update(int position) {
            String item = mList.get(position);
            tv_name.setText(item);
        }
    }


}
