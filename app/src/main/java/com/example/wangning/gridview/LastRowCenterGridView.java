package com.example.wangning.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wangning.R;

import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-05-02
 * @since JDK 1.8
 */
public class LastRowCenterGridView extends LinearLayout {

    private Context mContext;
    private LinearLayout ll_main;

    public LastRowCenterGridView(Context context) {
        this(context, null);
    }

    public LastRowCenterGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.gridview_lastrowcenter2, this);
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        ll_main.removeAllViews();
    }

    public void initData(List<String> list, int columnNum) {
        for (int i = 0; list != null && i < list.size(); i++) {
            if (i % columnNum == 0) {
                LinearLayout rowLayout = new LinearLayout(mContext);
                LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                if (i != 0) {
                    layoutParams.setMargins(0, 150, 0, 0);//行间距
                }

                rowLayout.setGravity(Gravity.CENTER);
                rowLayout.setLayoutParams(layoutParams);
                ll_main.addView(rowLayout);

                int nextColumnNum;
                if(list.size() - i-1 > columnNum){
                    nextColumnNum = columnNum;
                }else{
                    nextColumnNum = list.size() - i;
                }
                for (int j = 0; j <  nextColumnNum; j++) {
                    LinearLayout itemLayout = new LinearLayout(mContext);
                    ImageView imageView = new ImageView(mContext);
                    imageView.setImageResource(R.mipmap.ic_launcher);
                    itemLayout.addView(imageView);
                    LayoutParams lp = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    if (j != 0) {
                        lp.setMargins(100, 0, 0, 0);//列间距
                    }

                    itemLayout.setLayoutParams(lp);
                    rowLayout.addView(itemLayout);
                }
            }
        }
    }
}
