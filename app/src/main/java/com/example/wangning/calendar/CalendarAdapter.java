package com.example.wangning.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wangning.R;
import com.example.wangning.calendar.algorithm.DayItem;

import java.util.List;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyViewHolder> {

    private Context mContext;
    private List<DayItem> mList;

    public CalendarAdapter(Context context, List<DayItem> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_my_calendar, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    abstract class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }

        abstract void update(int position);
    }

    private class NormalViewHolder extends MyViewHolder {

        private TextView tvDay;
        private RelativeLayout rlBg;

        public NormalViewHolder(View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tv_day);
            rlBg = itemView.findViewById(R.id.rl_bg);
        }

        @Override
        void update(int position) {
            DayItem item = mList.get(position);
            if (item.isSigned()) {
                rlBg.setBackground(mContext.getResources().getDrawable(R.drawable.shape_stroke_ffebac6c_circle_w30_h30));
            }else{
                rlBg.setBackground(null);
            }
            tvDay.setEnabled(item.isEnable());
            tvDay.setText(item.getText());
        }
    }

}
