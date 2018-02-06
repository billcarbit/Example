package com.example.wangning.banner.gallery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.wangning.R;
import com.example.wangning.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 画廊banner
 * Created by Administrator on 2018/1/11.
 */
public class GalleryBannerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_banner);
        GalleryBannerView bannerView = (GalleryBannerView)findViewById(R.id.banner);
        List<View> viewList = new ArrayList<View>();
        int imgs[] = new int[]{R.drawable.banner_top,R.drawable.banner_top,R.drawable.guagua_bg};
        for (int i = 0; i < imgs.length; i++) {
            ImageView image = new ImageView(this);
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageResource(imgs[i]);
            viewList.add(image);
        }
        bannerView.setBottomDrawable(R.drawable.banner_point_select,R.drawable.banner_point);
        bannerView.startLoop(false);
        bannerView.setViewList(viewList);
    }

}
