package com.example.wangning.addersubtracter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;


/**
 * 可扩展加减器
 *
 * @author 王宁
 * @version 1.0 2016-11-15
 * @since 1.0
 */
public class AdderSubtracterView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "AdderSubtracterView";
    private int baseAmount = 1; //  钱/数量的基数
    private int minValue = 1; //最低份额
    private int maxValue = 1; // 最高份额
    private int defaultValue = 1; // 默认份额
    private OnAmountChangeListener mListener;

    private TextView tvAmount;
    private ImageView ivMinus;
    private ImageView ivPlus;

    private int totalAmount;

    //private int count;
    // private int minAmount;
    // private int maxAmount;

    private Context mContext;

    private String mUnit="";//单位

    public AdderSubtracterView(Context context) {
        this(context, null);
        mContext = context;
    }

    public AdderSubtracterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_subtract_adder, this);
        tvAmount = (TextView) findViewById(R.id.tv_count);
        ivMinus = (ImageView) findViewById(R.id.iv_minus);
        ivPlus = (ImageView) findViewById(R.id.iv_plus);
        ivMinus.setOnClickListener(this);
        ivPlus.setOnClickListener(this);
        ivPlus.setEnabled(true);
        ivMinus.setEnabled(true);
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public AdderSubtracterView setPlusEnable(boolean bool) {
        ivPlus.setEnabled(bool);
        return this;
    }

    public AdderSubtracterView setMinusEnable(boolean bool) {
        ivMinus.setEnabled(bool);
        return this;
    }

    public AdderSubtracterView setBetweenWidth(int dp) {
        tvAmount.setWidth(AppUtil.dp2px(mContext, dp));
        return this;
    }

    public AdderSubtracterView setUnit(String unit) {
        mUnit = unit;
        return this;
    }

    public AdderSubtracterView setValue(String value) {
        tvAmount.setText(value + mUnit);
        return this;
    }

    public AdderSubtracterView setMaxValue(String value) {
        maxValue = Integer.valueOf(value);
        return this;
    }

    public AdderSubtracterView setMinValue(String value) {
        minValue = Integer.valueOf(value);
        return this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_minus:
                if (totalAmount > minValue) {
                    totalAmount = totalAmount - baseAmount;//ParseUtil.sub(totalAmount, baseAmount);
                }
                if (totalAmount <= minValue) {
                    ivMinus.setEnabled(false);
                }
                ivPlus.setEnabled(true);
                tvAmount.setText(totalAmount + mUnit);
                break;
            case R.id.iv_plus:
                if (totalAmount < maxValue) {
                    totalAmount = totalAmount + baseAmount;
                }

                if (totalAmount >= maxValue) {
                    ivPlus.setEnabled(false);
                }

                ivMinus.setEnabled(true);
                tvAmount.setText(totalAmount + mUnit);
                break;
        }

        if (mListener != null) {
            //  mListener.onAmountChange(this, totalAmount, count);
        }
    }


    public interface OnAmountChangeListener {
        void onAmountChange(View view, int oldValue, int newValue);
    }

}
