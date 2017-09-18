package com.example.wangning.canvas.chart.columnar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangning.R;

/**
 * 点击拐点后弹出的popwindow
 *
 * @author wangning
 * @version 1.0 2017-09-05
 * @since JDK 1.8
 */
public class TurnPointPopWindow implements PopupWindow.OnDismissListener {
    PopupWindow mPopupWindow;
    PopupWindow.OnDismissListener mOnDismissListener;
    TextView tvContent;


    public TurnPointPopWindow(final Context context) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_turn_point, null);
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(false);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(0);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        mPopupWindow.setOnDismissListener(this);

        tvContent = (TextView) popupView.findViewById(R.id.tv_content);

    }

    public void showAtLocation(View anchor, int x, int y) {
        mPopupWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, x, y);
    }

    public void setContent(String content) {
        tvContent.setText(content);

    }

    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        mOnDismissListener = listener;
    }

    @Override
    public void onDismiss() {
        if(mOnDismissListener!=null){
            mOnDismissListener.onDismiss();
        }
    }

    public void dismiss() {
        mPopupWindow.dismiss();
    }

    public int getHeight() {
        mPopupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return mPopupWindow.getContentView().getMeasuredHeight();
    }

    public int getWidth() {
        mPopupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return mPopupWindow.getContentView().getMeasuredWidth();
    }

}
