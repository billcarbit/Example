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
        private RelativeLayout rlSelectBg;

        public NormalViewHolder(View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tv_day);
            rlBg = itemView.findViewById(R.id.rl_bg);
            rlSelectBg = itemView.findViewById(R.id.rl_select_bg);
        }

        @Override
        void update(final int position) {
            final DayItem item = mList.get(position);
            switch (item.getStatus()) {
                case 1://签到了
                    rlBg.setBackground(mContext.getResources().getDrawable(R.drawable.shape_stroke_ffebac6c_circle_w30_h30));
                    break;
                case 2://未签到
                    rlBg.setBackground(null);
                    break;
                case 3://签到异常
                    rlBg.setBackground(mContext.getResources().getDrawable(R.drawable.calendar_pic_undone));
                    break;
                default:
                    rlBg.setBackground(null);
                    break;
            }
            if (item.isSelected()) {//如果选中，则置灰
                rlSelectBg.setBackground(mContext.getResources().getDrawable(R.drawable.shape_oval_solid_ffeeeeee_w30_h30));
            } else {
                rlSelectBg.setBackground(null);
            }
            tvDay.setEnabled(item.isEnable());
            itemView.setEnabled(item.isEnable());
            tvDay.setText(item.getText());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearAllSelection();
                    item.setSelected(true);
                    notifyDataSetChanged();
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onDateItemClick(item);
                    }
                }
            });
        }
    }

    private OnDateItemClickListener mOnItemClickListener;

    public void setOnDateItemClickListener(OnDateItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    private void clearAllSelection() {
        if (mList == null) {
            return;
        }
        for (DayItem item : mList) {
            item.setSelected(false);
        }
    }
}
