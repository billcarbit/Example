package com.example.wangning.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * @version 1.0 2017-04-25
 * @since JDK 1.8
 */
public class PositionDialog {
    private Dialog mDialog;
    private Context mContext;

    public PositionDialog(Context mContext,float x,float y) {
        super();
        this.mContext = mContext;

        mDialog = new Dialog(mContext, R.style.add_dialog);

        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.dialog_main, null);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        mDialog.setContentView(view, params);
        mDialog.setCancelable(true);



        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
//显示的坐标
        lp.x = (int)x;
        lp.y = (int)y;
    /*    int width =100;// getResources().getDimensionPixelOffset(R.dimen.d_width);
        int height = 400;//getResources().getDimensionPixelOffset(R.dimen.d_height);*/
//dialog的大小



        dialogWindow.setAttributes(lp);

    }

    public void show() {
        mDialog.show();
    }


    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
