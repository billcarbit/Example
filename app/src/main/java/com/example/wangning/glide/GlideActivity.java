package com.example.wangning.glide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-01-13
 * @since JDK 1.8
 */
public class GlideActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ImageView imageView = (ImageView) findViewById(R.id.iv);
        String internetUrl = "http://www.qq745.com/uploads/allimg/141106/1-141106153Q5.png";
        Glide.with(this)
                .load(internetUrl)
                .asBitmap()//强制处理为bitmap
                .into(imageView);
    }

}
