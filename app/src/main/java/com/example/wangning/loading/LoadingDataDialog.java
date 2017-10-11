package com.example.wangning.loading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

import com.example.wangning.R;


/**
 * @author pjb
 * @date 2015-12-7 上午10:29:36
 * @category 进度条加载
 */
public class LoadingDataDialog {
    private Dialog progressDialog;
    private AnimationDrawable animationDrawable;

    public void startProgressDialog(Context context) {
        if (progressDialog == null) {
            progressDialog = new Dialog(context, R.style.loading_dialog);
            View view = LayoutInflater.from(context).inflate(
                    R.layout.activity_dialog, null);
            ImageView imageview = (ImageView) view.findViewById(R.id.imageview);
            imageview.setImageResource(R.drawable.updataproback);
            animationDrawable = (AnimationDrawable) imageview.getDrawable();
            animationDrawable.start();

            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            int weight = dm.widthPixels * 1 / 4;
            int height = dm.heightPixels * 1 / 4;
            LayoutParams p = new LayoutParams(height, weight);
            progressDialog.setContentView(view, p);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    public void stopProgressDialog() {
        try {
            if (progressDialog != null) {
                animationDrawable.stop();
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isShowing() {
        if (progressDialog != null && progressDialog.isShowing()) {
            return true;
        } else {
            return false;
        }
    }

    public void setCancelable(boolean able) {
        if (progressDialog != null) {
            progressDialog.setCancelable(able);
        }
    }

}
