package com.example.wangning.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.wangning.R;


public class BannerImageHolderView implements CBPageAdapter.Holder<IBanner>{

    private ImageView imageView;
    private OnBannerClickListener onBannerClickListener;
    private int defaultDrawableId = R.drawable.banner_loading_300;
    private CreateBannerImageView createBannerImageView;


    @Override
    public View createView(Context context) {
            if(createBannerImageView ==null){
                imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }else {
                imageView = createBannerImageView.onCreateImageView();
                return imageView;
            }
    }

    public BannerImageHolderView setOnBannerClickListener(OnBannerClickListener listener) {

        onBannerClickListener = listener;
        return this;
    }

    public BannerImageHolderView setCreateBannerImageView(CreateBannerImageView createBannerImageView) {
        this.createBannerImageView = createBannerImageView;
        return this;
    }

    @Override
    public void updateUI(final Context context, final int position, final IBanner data) {

        ImageLoaderUtil.displayImage(data.getImageUrl(), imageView,defaultDrawableId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onBannerClickListener != null) {

                    onBannerClickListener.onBannerClick(data,position);
                }
            }
        });
    }

    @Override
    public BannerImageHolderView setDefaultLoadImage(int drawableId) {
        defaultDrawableId = drawableId!=0?drawableId:defaultDrawableId;
        return this;
    }

    /**
     * Banner点击事件
     */
    public interface OnBannerClickListener{

        void onBannerClick(IBanner banner, int position);
    }

    public interface CreateBannerImageView{

        ImageView onCreateImageView();
    }
}
