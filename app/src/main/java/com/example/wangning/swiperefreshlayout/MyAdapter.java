package com.example.wangning.swiperefreshlayout;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-01-11
 * @since JDK 1.8
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    // item 类型
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_LOAD_MORE = 1;
    private Activity mActivity;
    private List<String> mList;
    private LayoutInflater mInflater;

    public MyAdapter(Activity activity, List<String> list) {
        mActivity = activity;
        mList = list;
        mInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_LOAD_MORE:
                return new LoadMoreViewHolder(mInflater.inflate(
                        R.layout.activity_item_load_more, parent, false));
            default: // TYPE_NORMAL
                return new NormalViewHolder(mInflater.inflate(
                        R.layout.item_recyclerview, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return mList.size() >= Constant.PER_PAGE_COUNT ? mList.size() + 1 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.size() >= Constant.PER_PAGE_COUNT
                && position == getItemCount() - 1) {
            return TYPE_LOAD_MORE;
        } else {
            return TYPE_NORMAL;
        }
    }

    /**
     * item 基类
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        protected ViewHolder(View itemView) {
            super(itemView);
        }

        protected void update(int position) {

        }
    }

    /**
     * 加载更多item
     */
    public class LoadMoreViewHolder extends ViewHolder {

        protected LoadMoreViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void update(int position) {

        }
    }

    public class NormalViewHolder extends ViewHolder implements View.OnClickListener {

        private TextView tv_head;

        protected NormalViewHolder(View itemView) {
            super(itemView);
            tv_head = (TextView) itemView
                    .findViewById(R.id.tv_head);
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        protected void update(int position) {
            tv_head.setText(mList.get(position));
        }
    }

}
