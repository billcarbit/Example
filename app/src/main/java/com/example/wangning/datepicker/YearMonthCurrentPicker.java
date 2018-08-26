package com.example.wangning.datepicker;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 年，月，至今 选择器
 * Created by admin on 2018/8/26.
 */
public class YearMonthCurrentPicker {

    private Context mContext;
    private final int YEAR_RANG = 30;

    /**
     * 完整的月份数据1~12
     */
    private List<String> mMonthList = new ArrayList<>();
    /**
     * 滚轮选择器中年份的选项数据
     */
    private List<String> mOptionYears = new ArrayList<>();
    /**
     * 滚轮选择器中月份的选项数据
     */
    private List<List<String>> mOptionMonths = new ArrayList<>();


    public YearMonthCurrentPicker(Context context) {
        mContext = context;
        initData();
    }

    private void initData() {
        initMonthList();
        initOptionYearMonth();
    }

    private void initMonthList() {
        for (int i = 1; i <= 12; i++) {
            mMonthList.add(String.valueOf(i));
        }
    }

    private void initOptionYearMonth() {
        mOptionYears.add("至今");
        List<String> now = new ArrayList<String>();
        now.add("至今");
        mOptionMonths.add(now);

        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        for (int i = currYear; i >= currYear - YEAR_RANG; i--) {
            mOptionYears.add(String.valueOf(i));
            mOptionMonths.add(mMonthList);
        }
    }


    /**
     * 显示滚轮
     *
     * @param view
     */
    public void showPickerView(View view) {
        OptionsPickerView multipleOp = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Log.e("AAA", "onOptionsSelect: options1=" + options1 + ",options2=" + options2);
          /*      if (options1 == 0 || options1 == optionYears.size() - 1) {
                    //选中最新和最早时间时直接显示文字，不需要拼接月份
                    tvTime.setText(optionYears.get(options1));
                } else {
                    //常规的时间，需要拼接年份和月份
                    tvTime.setText(new StringBuffer(optionYears.get(options1)).append("—").append(monthList.get(options2)));
                }*/
            }
        }).setTitleText("请选择时间")
                .build();
        multipleOp.setPicker(mOptionYears, mOptionMonths);
        multipleOp.show();

    }


}
