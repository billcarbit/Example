package com.example.wangning.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */
public class MyAdapter extends BaseAdapter {

    List<Item> mData;
    private Context mContext;

    public MyAdapter(Context context, List<Item> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Item getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int currentType = getItemViewType(position);//当前类型
        if(currentType==0){
            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item, null);
                vh.text = (TextView) convertView.findViewById(R.id.title);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.text.setText(getItem(position).getText());
        }else if(currentType==1){
            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item2, null);
                vh.text = (TextView) convertView.findViewById(R.id.title);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.text.setText(getItem(position).getText());
        }

        return convertView;
    }

    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }



    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(mData.get(position).getType()==0){//当前JavaBean对象的类型
            return 0;//学生类型
        }else if(mData.get(position).getType()==1){
            return 1;//老师类型
        }else {
            return 100;
        }
    }




}
