package com.example.wangning.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-04-25
 * @since JDK 1.8
 */
public class PositionDialog {
    private Dialog mDialog;
    private Activity mActivity;
    private View mDialogView;

    public PositionDialog(Activity context) {
        super();
        mActivity = context;
        mDialog = new Dialog(mActivity, R.style.add_dialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialogView = LayoutInflater.from(mActivity).inflate(
                R.layout.dialog_main, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.setContentView(mDialogView, params);
        mDialog.setCancelable(true);


    }

    public void show(View anchorView) {
        final int[] location = new int[2];
        anchorView.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialogWindow.setGravity(Gravity.NO_GRAVITY);
        DisplayMetrics metric = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
/*        int width = metric.widthPixels; // 屏幕宽度（像素）
        if (location[0] + anchorView.getWidth() / 2 + mDialogView.getWidth() > width) {
            lp.x = location[0] + anchorView.getWidth() / 2 - mDialogView.getWidth();
            lp.y = location[1] - mDialogView.getHeight() + anchorView.getHeight() / 2;
            dialogWindow.setAttributes(lp);
        } else {
            lp.x = location[0] + anchorView.getWidth() / 2;
            lp.y = location[1] - mDialogView.getHeight() + anchorView.getHeight() / 2;
            dialogWindow.setAttributes(lp);
        }*/
        lp.x = location[0];
        lp.y = location[1];
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }


    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
