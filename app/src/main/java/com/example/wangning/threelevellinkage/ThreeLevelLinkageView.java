package com.example.wangning.threelevellinkage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class ThreeLevelLinkageView extends LinearLayout {

    ListView lv1, lv2, lv3;
    List<String> list1 = new ArrayList<String>();
    List<String> list2 = new ArrayList<String>();
    List<String> list3 = new ArrayList<String>();

    public ThreeLevelLinkageView(Context context) {
        this(context, null);
    }

    public ThreeLevelLinkageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_three_level_linkage, this);
        lv1 = (ListView) findViewById(R.id.lv1);
        lv2 = (ListView) findViewById(R.id.lv2);
        lv3 = (ListView) findViewById(R.id.lv3);

        lv1.setAdapter(new Adapter1(list1));
        lv2.setAdapter(new Adapter1(list2));
        lv3.setAdapter(new Adapter1(list3));
    }

    public void setLevel1(List<String> list) {
        list1.addAll(list);
    }

    public void setLevel2(List<String> list) {
        list2.addAll(list);
    }

    public void setLevel3(List<String> list) {
        list3.addAll(list);
    }

    class ViewHolder {
        TextView tv;

        public void setTv(TextView textView) {
            tv = textView;
        }

        public TextView getTextView() {
            return tv;
        }
    }

    class Adapter1 extends BaseAdapter {
        private List<String> mDataList;

        public Adapter1(List<String> list) {
            super();
            mDataList = list;
        }

        @Override
        public int getCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

        @Override
        public String getItem(int position) {
            return mDataList.get(position);
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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_level_linkage, null);
                vh.setTv((TextView) convertView.findViewById(R.id.tv));
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.getTextView().setText(mDataList.get(position));
            return convertView;
        }
    }

}
