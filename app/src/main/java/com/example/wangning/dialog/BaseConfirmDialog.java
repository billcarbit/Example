package com.example.wangning.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * 基础确认对话框
 *
 * @author wangning
 * @version 3.0 2017-05-26
 * @since JDK 1.8
 */
public class BaseConfirmDialog extends Dialog implements View.OnClickListener {
    private TextView mTvCancel;
    private TextView mTvConfirm;
    private TextView mTvContent;
    private OnConfirmListener mOnConfirmListener;
    private String mContent;
    private String mConfirmText = "确定";
    private String mCancelText = "取消";
    private View mVSplit;//分隔线
    private int mBtnNums;

    public BaseConfirmDialog(Context context) {
        super(context, R.style.add_dialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_confirm_base);
        initView();
        initData();
        setListener();
    }

    private void initView() {
        mTvCancel = (TextView) findViewById(R.id.tv_cancel);
        mTvConfirm = (TextView) findViewById(R.id.tv_confirm);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mVSplit = findViewById(R.id.v_split);



    }

    private void initData() {
        mTvContent.setText(mContent);
        mTvConfirm.setText(mConfirmText);
        mTvCancel.setText(mCancelText);

        if (mBtnNums == 1) {
            mVSplit.setVisibility(View.GONE);
            mTvCancel.setVisibility(View.GONE);
            mTvConfirm.setVisibility(View.VISIBLE);
        } else if (mBtnNums == 2) {
            mVSplit.setVisibility(View.VISIBLE);
            mTvCancel.setVisibility(View.VISIBLE);
            mTvConfirm.setVisibility(View.VISIBLE);
        }
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
                break;
            case R.id.tv_confirm:
                mOnConfirmListener.onConfirm();
                break;
            default:
                break;
        }
    }

    public BaseConfirmDialog setBtnNum(int btnNum) {
        mBtnNums = btnNum;
        return this;
    }

    public void setOnConfirmListener(OnConfirmListener listener) {
        mOnConfirmListener = listener;
    }

    public BaseConfirmDialog setContent(String content) {
        mContent = content;
        return this;
    }

    public BaseConfirmDialog setConfirmText(String confirmText) {
        mConfirmText = confirmText;
        return this;
    }

    public BaseConfirmDialog setCancelText(String cancelText) {
        mCancelText = cancelText;
        return this;
    }

    public interface OnConfirmListener {
        void onConfirm();
    }
}
