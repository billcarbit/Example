package com.example.wangning.textswitcher;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 轮播文本
 *
 * @author wangning
 * @version 1.0 2017-09-19
 * @since JDK 1.8
 */
public class CarouselTextView extends LinearLayout implements ViewSwitcher.ViewFactory {
    private static final String TAG = "CarouselTextView";
    Context mContext;
    TextSwitcher switcher;
    int mIndex = 0; //下标
    List<String> mDataList = new ArrayList<String>();
    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e(TAG, "handleMessage: mIndex=" + mIndex);
                    if (mIndex >= mDataList.size()) {
                        mIndex = 0;
                    }
                    switcher.setText(mDataList.get(mIndex));
                    mIndex++;
                    mHandler.sendEmptyMessageDelayed(1,3000);
                    break;
            }
        }

        ;
    };


    public CarouselTextView(Context context) {
        this(context, null);
    }

    public CarouselTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_text_switcher, this);
        initView();
    }

    public void start() {
        mHandler.removeMessages(1);
        mHandler.sendEmptyMessage(1);
    }

    public void shutDown() {
        mHandler.removeMessages(1);
    }

    @Override
    public View makeView() {
        return new TextView(mContext);
    }

    private void initView() {
        switcher = (TextSwitcher) findViewById(R.id.ts);
        switcher.setFactory(this);
        switcher.setInAnimation(AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(mContext, android.R.anim.slide_out_right));
    }

    public List<String> getDataList() {
        return mDataList;
    }

    public void setDataList(List<String> dataList) {
        mDataList = dataList;
    }


}
