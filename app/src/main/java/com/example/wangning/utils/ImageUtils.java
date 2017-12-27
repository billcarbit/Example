package com.example.wangning.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2017/12/26.
 */

public class ImageUtils {
    static DisplayImageOptions sOptions;        // 显示图片的设置

    public static DisplayImageOptions getDefaultOptions() {
        if (sOptions == null) {
            sOptions = new DisplayImageOptions.Builder()
           /*     .showStubImage(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)*/
                    .cacheInMemory(true)
                    .cacheOnDisc(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)     //设置图片的解码类型
                    .build();
        }
        return sOptions;
    }

    public static void displayImage(String url, ImageView iv) {
        ImageLoader.getInstance().displayImage(url, iv, getDefaultOptions());
    }
    public static void displayImage(String url, ImageView iv, DisplayImageOptions options) {
        ImageLoader.getInstance().displayImage(url, iv, options);
    }
}
