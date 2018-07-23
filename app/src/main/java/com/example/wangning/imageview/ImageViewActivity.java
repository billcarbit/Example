package com.example.wangning.imageview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.wangning.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2018/7/19.
 */
public class ImageViewActivity extends Activity {
    ImageView iv_photo;
    ImageView iv_simple;
    final String mImageUrl = "http://localhost:8080/hello/download/yingyezhzhao.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        iv_photo = (ImageView) findViewById(R.id.iv_photo);
        iv_simple = (ImageView) findViewById(R.id.iv_simple);
        //ImageLoader.getInstance().displayImage(mImageUrl, iv_photo);

        final Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.guagua_bg);
        //创建操作图片是用的matrix对象
        Matrix matrix = new Matrix();
//缩放图片动作
      //  matrix.postScale(1, 1);
//旋转图片动作
        matrix.postRotate(2, 10,10);//以坐标50，100 旋转30°
//创建新图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//将上面创建的bitmap转换成drawable对象，使其可以使用在ImageView,ImageButton中
        BitmapDrawable bmd = new BitmapDrawable(resizedBitmap);
        iv_simple.setAdjustViewBounds(true);
        iv_simple.setImageDrawable(bmd);


    }


}
