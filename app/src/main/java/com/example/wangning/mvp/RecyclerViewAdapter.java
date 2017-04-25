package com.example.wangning.mvp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BaseViewHolder> {

    private static final int TYPE_FOOTER = 0;
    private static final int TYPE_ITEM = 1;

    private Activity mActivity;
    private List<String> mList;

    public RecyclerViewAdapter(Activity activity, List<String> stringList) {
        mActivity = activity;
        mList = stringList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = null;
        switch (viewType) {
            case TYPE_FOOTER:
                holder = new ViewContentHolder(LayoutInflater.from(
                        mActivity).inflate(R.layout.item_footer, parent,
                        false));
                break;
            case TYPE_ITEM:
                holder = new ViewContentHolder(LayoutInflater.from(
                        mActivity).inflate(R.layout.item_recyclerview, parent,
                        false));
                break;
            default:
                break;
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_ITEM:
                break;
            case TYPE_FOOTER:
                break;
            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

   public class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(View view) {
            super(view);
        }

        void update() {

        }
    }

    class ViewContentHolder extends BaseViewHolder {
        TextView tv_head;

        public ViewContentHolder(View view) {
            super(view);
            tv_head = (TextView) view.findViewById(R.id.tv_head);
        }
    }

    class ViewFooterHolder extends BaseViewHolder {
        TextView tv_head;

        public ViewFooterHolder(View view) {
            super(view);
            tv_head = (TextView) view.findViewById(R.id.tv_head);
        }
    }
}
