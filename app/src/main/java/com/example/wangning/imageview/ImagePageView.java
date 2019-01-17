package com.example.wangning.imageview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

public class ImagePageView extends FrameLayout {

    private List<PinchImageView> mPinchImageViewList = new ArrayList<>();
    private List<String> mImageUrlList = new ArrayList<>();
    private ViewPager viewpager;
    private MyPagerAdapter mMyPagerAdapter;

    public ImagePageView(@NonNull Context context) {
        super(context);
    }

    public ImagePageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_image_pager, this);
        viewpager = findViewById(R.id.viewpager);
        mMyPagerAdapter = new MyPagerAdapter();
        viewpager.setAdapter(mMyPagerAdapter);
    }

    public void setImageUrlList(List<String> imageUrlList) {
        mImageUrlList.clear();
        mImageUrlList.addAll(imageUrlList);

        for (String imageUrl : imageUrlList) {
            PinchImageView pinchImageView = new PinchImageView(getContext());
        }

    }

    public class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }


}
