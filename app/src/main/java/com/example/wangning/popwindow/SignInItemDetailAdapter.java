package com.example.wangning.popwindow;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.wangning.R;

import java.util.List;

/**
 * 签到奖励详情适配器
 *
 * @author wangning
 * @version 1.0 2017-04-26
 * @since JDK 1.8
 */
public class SignInItemDetailAdapter extends BaseAdapter {
    private Activity mActivity;
    private List<String> mDetailList;

    public SignInItemDetailAdapter(Activity activity, List<String> detailList) {
        mActivity = activity;
        mDetailList = detailList;
    }

    @Override
    public int getCount() {
        return mDetailList == null ? 0 : mDetailList.size();
    }

    @Override
    public String getItem(int i) {
        return mDetailList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mActivity).inflate(R.layout.item_sign_in_gift_detail, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
            viewHolder.tv_num = (TextView) view.findViewById(R.id.tv_num);
            viewHolder.tv_beans = (TextView) view.findViewById(R.id.tv_beans);

            viewHolder.ll_coupon_value = (LinearLayout) view.findViewById(R.id.ll_coupon_value);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String signItemDetail = getItem(position);
        viewHolder.tv_num.setText(signItemDetail);

        return view;
    }

    static class ViewHolder {
        ImageView iv_pic;
        TextView tv_num;
        LinearLayout ll_coupon_value;
        TextView tv_beans;
    }
}