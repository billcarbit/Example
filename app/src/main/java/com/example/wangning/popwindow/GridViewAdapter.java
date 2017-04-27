package com.example.wangning.popwindow;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */
public class GridViewAdapter extends BaseAdapter {

    private List<String> mList;
    private Activity mActivity;

    public GridViewAdapter(Activity activity, List<String> list) {
        mActivity = activity;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = new ItemView(mActivity).getLayout();
            vh.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.mTvName.setText(getItem(position));

        return convertView;
    }

    static class ViewHolder {
        private TextView mTvName;

    }
}
