package com.example.wangning.addersubtracter;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;
import com.example.wangning.utils.AppUtil;


/**
 * 可扩展加减器
 *
 * @author 王宁
 * @version 1.0 2016-11-15
 * @since 1.0
 */
public class AdderSubtracterView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "AdderSubtracterView";
    private int baseAmount = 1; //  步长
    private int minValue = 0;
    private int maxValue = 0;
    private OnValueChangeListener mListener;

    private TextView tvAmount;
    private ImageView ivMinus;
    private ImageView ivPlus;

    private int totalAmount;


    private Context mContext;

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

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.AdderSubtracterView);
        int max = typedArray.getInt(R.styleable.AdderSubtracterView_max, 0);
        int min = typedArray.getInt(R.styleable.AdderSubtracterView_min, 0);
        int value = typedArray.getInt(R.styleable.AdderSubtracterView_value, 0);
        int textWidth = typedArray.getInt(R.styleable.AdderSubtracterView_textWidth, 0);
        setMaxValue(max).setMinValue(min).setValue(value).setBetweenWidth(textWidth);

    }

    public void setOnPlusListener(OnValueChangeListener listener) {
        this.mListener = listener;
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


    public int getValue() {
        return Integer.valueOf(tvAmount.getText().toString());
    }

    public AdderSubtracterView setValue(int value) {
        totalAmount = value;
        tvAmount.setText(String.valueOf(value));
        if (totalAmount <= minValue) {
            setMinusEnable(false);
        } else {
            setMinusEnable(true);
        }
        return this;
    }

    public AdderSubtracterView setMaxValue(int value) {
        maxValue = value;
        if (totalAmount >= maxValue) {
            setPlusEnable(false);
        } else {
            setPlusEnable(true);
        }
        return this;
    }

    public AdderSubtracterView setMinValue(int value) {
        minValue = Integer.valueOf(value);
        return this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_minus:
                if (totalAmount > minValue) {
                    totalAmount = totalAmount - baseAmount;
                }
                if (totalAmount <= minValue) {
                    ivMinus.setEnabled(false);
                }
                ivPlus.setEnabled(true);
                tvAmount.setText(String.valueOf(totalAmount));

                if (mListener != null) {
                    mListener.onMinus(totalAmount);
                }
                break;
            case R.id.iv_plus:
                if (totalAmount < maxValue) {
                    totalAmount = totalAmount + baseAmount;
                }

                if (totalAmount >= maxValue) {
                    ivPlus.setEnabled(false);
                }

                ivMinus.setEnabled(true);
                tvAmount.setText(String.valueOf(totalAmount));

                if (mListener != null) {
                    mListener.onPlus(totalAmount);
                }
                break;
        }


    }


    public interface OnValueChangeListener {
        void onPlus(int newValue);

        void onMinus(int newValue);
    }

}
