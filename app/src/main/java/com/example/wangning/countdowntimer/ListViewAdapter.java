package com.example.wangning.countdowntimer;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
public class ListViewAdapter extends BaseAdapter {
    private static final String TAG = "ListViewAdapter";
    private Context mContext;
    private List<String> mList;



    public ListViewAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_countdown, null);
            vh.tv_countdown = (TextView) convertView.findViewById(R.id.tv_countdown);
            vh.tv_tag = (TextView) convertView.findViewById(R.id.tv_tag);

            Log.e(TAG, "getView: getItemId="+getItemId(position));
            //new CountDownTimer().setTextView(vh.tv_countdown).setInitVal(getItemId(position)).start();



            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv_countdown.setText(getItemId(position) + "");
        vh.tv_tag.setText(getItem(position));
        return convertView;
    }

    static class ViewHolder {
        TextView tv_countdown, tv_tag;
    }
}
