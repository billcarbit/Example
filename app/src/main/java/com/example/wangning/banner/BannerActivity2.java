package com.example.wangning.banner;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.wangning.R;
import com.example.wangning.banner.gallery.GalleryBannerView;
import com.example.wangning.banner.youth.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 */

public class BannerActivity2 extends Activity {
    private static final String TAG = "BannerActivity2";
    private int[] imgs = {R.drawable.banner_top, R.drawable.guagua_bg, R.drawable.banner_top, R.drawable.banner_top};
    private List<View> viewList;
    GalleryBannerView bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner2);
        viewList = new ArrayList<View>();
        for (int i = 0; i < imgs.length; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.fragment_banner, null);
            ImageView image =  view.findViewById(R.id.iv);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageResource(imgs[i]);
            viewList.add(view);

      /*      ImageView image = new ImageView(this);
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageResource(imgs[i]);
            viewList.add(image);*/

        }
        bannerView = (GalleryBannerView) findViewById(R.id.banner);
        bannerView.setBottomDrawable(R.drawable.banner_point_select, R.drawable.banner_point);
        bannerView.startLoop(false);
        //bannerView.setLoopInterval(2000);
        bannerView.setViewList(viewList);
        //bannerView.setTransformAnim(true);
    }

}
