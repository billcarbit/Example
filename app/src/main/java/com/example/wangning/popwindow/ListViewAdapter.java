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
public class ListViewAdapter extends BaseAdapter {

    private List<RowView> mList;
    private Activity mActivity;

    public ListViewAdapter(Activity activity, List<RowView> list) {
        mActivity = activity;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public RowView getItem(int position) {
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
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_row_view_container, null);
            vh.row_view = (RowView) convertView.findViewById(R.id.row_view);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        TextView tv_1 = (TextView) vh.row_view.findViewById(R.id.tv_1);
        tv_1.setText(getItem(position).getItem1Text());
        TextView tv_2 = (TextView) vh.row_view.findViewById(R.id.tv_2);
        tv_2.setText(getItem(position).getItem2Text());
        TextView tv_3 = (TextView) vh.row_view.findViewById(R.id.tv_3);
        tv_3.setText(getItem(position).getItem3Text());
        TextView tv_4 = (TextView) vh.row_view.findViewById(R.id.tv_4);
        tv_4.setText(getItem(position).getItem4Text());
        Log.e("getView", "getView: tv_1="+tv_1.getText()+",tv_2="+tv_2.getText()+",tv_3="+tv_3.getText()+",tv_4="+tv_4.getText() );
        return convertView;
    }

    static class ViewHolder {
        private RowView row_view;
    }
}
