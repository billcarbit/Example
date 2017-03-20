package com.example.wangning.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;


/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-17
 * @since JDK 1.8
 */
public class DeleteDialog implements View.OnClickListener {
    private Dialog mDialog;
    private Context mContext;
    private TextView mTvInfo;
    private Button mBtnSure,mBtnCancel;
    private OnConfirmListener mOnConfirmListener;

    public DeleteDialog(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public void showDialog(String str, OnConfirmListener listener) {
        mOnConfirmListener = listener;
        mDialog = new Dialog(mContext, R.style.add_dialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.view_dialog, null);
        mBtnSure = (Button) view.findViewById(R.id.btn_sure);
        mBtnCancel = (Button) view.findViewById(R.id.btn_cancel);
        mTvInfo = (TextView) view.findViewById(R.id.tv_info);
        mTvInfo.setText(str);
        mBtnSure.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int weight = dm.widthPixels * 4 / 5;
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(weight,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.setContentView(view, p);
        mDialog.setCancelable(true);
        mDialog.show();
    }


    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:// 确定
                mOnConfirmListener.onConfirm();
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }

    interface OnConfirmListener {
        void onConfirm();
    }
}
