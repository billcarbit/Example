package com.example.wangning.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * 基础提醒框
 *
 * @author wangning
 * @version 1.0 2017-06-28
 * @since JDK 1.8
 */
public class BaseAlertDialog extends Dialog
        implements View.OnClickListener {

    private TextView mTvTitle;
    private TextView mTvCancel;
    private TextView mTvConfirm;
    private TextView mTvContent;
    private View mVSplit;//分隔线

    private String mTitle;
    private String mMessage;
    private String mCancelText;
    private String mConfirmText;

    private OnCancelListener mOnCancelListener;
    private OnConfirmListener mOnConfirmListener;

    public BaseAlertDialog(Context context) {
        super(context, R.style.add_dialog);
    }


    public BaseAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_base_alert);
        initView();
        initData();
        setListener();
    }


    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvCancel = (TextView) findViewById(R.id.tv_cancel);
        mTvConfirm = (TextView) findViewById(R.id.tv_confirm);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mVSplit = findViewById(R.id.v_split);
    }

    private void initData() {
        mTvContent.setText(mMessage);
        mTvTitle.setVisibility(TextUtils.isEmpty(mTitle) ? View.GONE : View.VISIBLE);
        if (TextUtils.isEmpty(mConfirmText)) {
            mTvConfirm.setVisibility(View.GONE);
            mVSplit.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(mCancelText)) {
            mTvCancel.setVisibility(View.GONE);
            mVSplit.setVisibility(View.GONE);
        }
        mTvTitle.setText(mTitle);
        mTvConfirm.setText(mConfirmText);
        mTvCancel.setText(mCancelText);


    }

    private void setListener() {
        mTvCancel.setOnClickListener(this);
        mTvConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                if (mOnCancelListener != null) {
                    mOnCancelListener.onCancel();
                }
                break;
            case R.id.tv_confirm:
                if (mOnConfirmListener != null) {
                    mOnConfirmListener.onConfirm();
                }
                break;
            default:
                break;
        }
    }


    public void setOnConfirmListener(OnConfirmListener listener) {
        mOnConfirmListener = listener;
    }

    public void setOnCancelListener(OnCancelListener listener) {
        mOnCancelListener = listener;
    }


    public BaseAlertDialog setTitleContent(String title) {
        mTitle = title;
        return this;
    }


    public BaseAlertDialog setMessage(String message) {
        mMessage = message;
        return this;
    }

    public BaseAlertDialog setPositiveButton(String text, OnConfirmListener listener) {
        mConfirmText = text;
        setOnConfirmListener(listener);
        return this;
    }

    public BaseAlertDialog setNegativeButton(String text, OnCancelListener listener) {
        mCancelText = text;
        setOnCancelListener(listener);
        return this;
    }


    public interface OnConfirmListener {
        void onConfirm();
    }

    public interface OnCancelListener {
        void onCancel();
    }

}
