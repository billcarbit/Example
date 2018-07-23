package com.example.wangning.imageloader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.wangning.R;
import com.example.wangning.utils.AppUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.net.URI;

/**
 * Created by Administrator on 2018/7/23.
 */
public class ImageLoaderActivity extends Activity {

    private ImageView iv;
    private String url = "http://localhost:8080/hello/download/yingyezhzhao.jpg";
    private String url2 = "http://localhost:8080/hello/download/xiaomipingtai.png";
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.drawable.service_pic_default)
            .showImageForEmptyUri(R.drawable.service_pic_default)
            .showImageOnFail(R.drawable.service_pic_default)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageloader);
        iv = (ImageView) findViewById(R.id.iv);

        if (AppUtil.isNetworkConnected(this)) {
            ImageLoader.getInstance().displayImage(url, iv, options);
        } else {
            String path = ImageLoader.getInstance().getDiskCache().get(url2).getPath();
            iv.setImageBitmap(BitmapFactory.decodeFile(path));
        }

    }
}
