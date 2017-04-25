package com.example.wangning.annimate;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
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
 * @version 1.0 2017-04-24
 * @since JDK 1.8
 */
public class SignDialog {
    Dialog mDialog;

    public SignDialog(Context context) {
        mDialog = new Dialog(context, R.style.add_dialog);
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_dialog, null);
        mDialog.getWindow().setWindowAnimations(R.style.mystyle);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.setContentView(view, params);
        mDialog.setCancelable(true);
    }

    public void show(){
        mDialog.show();
    }

}
