package com.example.wangning.popwindow;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.wangning.R;

import java.util.List;

/**
 * 签到奖品详情弹框
 *
 * @author wangning
 * @version 1.0 2017-04-25
 * @since JDK 1.8
 */
public class SignInItemPopupWindow {
    private Activity mActivity;
    private PopupWindow mPopupWindow;
    private ListView ls_item_detail;
    private SignInItemDetailAdapter mAdapter;
    private List<String> mDetailList;

    public SignInItemPopupWindow(Activity activity, List<String> detailList) {
        mActivity = activity;
        mDetailList = detailList;
        View popupView = LayoutInflater.from(mActivity).inflate(R.layout.popwin_signin_detail, null);
        initView(popupView);
        initData();
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupHeight = popupView.getMeasuredHeight();
        int popupWidth = popupView.getMeasuredWidth();
        mPopupWindow = new PopupWindow(popupView, popupWidth, popupHeight);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(mActivity.getResources(), (Bitmap) null));

    }

    private void initView(View view) {
        ls_item_detail = (ListView) view.findViewById(R.id.ls_item_detail);
    }

    private void initData() {
        mAdapter = new SignInItemDetailAdapter(mActivity, mDetailList);
        ls_item_detail.setAdapter(mAdapter);
    }

    public void show(View anchorView) {

   /*     WindowManager.LayoutParams windowLp = mActivity.getWindow().getAttributes();
        windowLp.alpha = 0.5f;
        mActivity.getWindow().setAttributes(windowLp);*/
        // mPopupWindow.showAsDropDown(view);
        //  mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);

        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);
        DisplayMetrics metric = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
  /*      if (location[0] + anchorView.getWidth() / 2 + mPopupWindow.getWidth() > width) {
            mPopupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0] + anchorView.getWidth() / 2 - mPopupWindow.getWidth(), location[1] - mPopupWindow.getHeight() + anchorView.getHeight() * 82 / 240);
        } else {

            mPopupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY , location[0]+ anchorView.getWidth()/2, location[1]- mPopupWindow.getHeight()+ anchorView.getHeight() * 82 / 240 );
        }*/

        mPopupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0], location[1]);
    }


}
