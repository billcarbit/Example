package com.example.wangning.banner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 */

public class BannerActivity2 extends Activity {
    private int[] imgs = {R.drawable.banner_top,R.drawable.banner_top,R.drawable.banner_top};
    private List<View> viewList;
    BannerView bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner2);
        viewList = new ArrayList<View>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView image = new ImageView(this);
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageResource(imgs[i]);
            viewList.add(image);
        }
        bannerView = (BannerView) findViewById(R.id.banner);
        bannerView.setBottomDrawable(R.drawable.banner_point_select,R.drawable.banner_point);
        bannerView.startLoop(true);
        bannerView.setLoopInterval(2000);
        bannerView.setViewList(viewList);
       //bannerView.setTransformAnim(true);
    }

}
