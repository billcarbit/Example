package com.example.wangning.canvas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.wangning.R;


/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-16
 * @since JDK 1.8
 */
public class CanvasActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        LinearLayout mylayout =(LinearLayout)findViewById(R.id.ll);
        Canvas canvas;

        Paint  paint = new Paint();
        Shader mShader = new LinearGradient(0,0,140,140,new int[] {Color.RED,Color.GREEN},new float[]{0.0f,0.5f},Shader.TileMode.CLAMP);
        paint.setShader(mShader);
        paint.setStrokeWidth(5);//笔宽5像素
        paint.setColor(Color.RED);//设置为红笔

        Bitmap bitmap = Bitmap.createBitmap(800, 480, Bitmap.Config.ARGB_8888); //设置位图的宽高,bitmap为透明
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);//设置为透明，画布也是透明
        canvas.drawLine(0, 20, 1000, 20, paint);


        Drawable drawable = new BitmapDrawable(bitmap) ;
        mylayout.setBackgroundDrawable(drawable);

    }


}
