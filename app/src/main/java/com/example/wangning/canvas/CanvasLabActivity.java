package com.example.wangning.canvas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import com.example.wangning.R;


/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-16
 * @since JDK 1.8
 */
public class CanvasLabActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

      //  LinearLayout mylayout = (LinearLayout) findViewById(R.id.ll);
        Canvas canvas;

        Paint paint = new Paint();
        paint.setStrokeWidth(5);//笔宽5像素
        paint.setColor(Color.GREEN);//设置为绿笔
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;//获取屏幕的宽和高
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); //设置位图的宽高,bitmap为透明
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.GRAY);//设置为透明，画布也是透明
   /*     canvas.drawLine(0, 20, 50, 50, paint);
        canvas.drawLine(50, 50, 500, 100, paint);*/

  /*      RectF rectF = new RectF();
        rectF.set(150.0f, 150.0f,  650.0f, 250.0f);
        canvas.drawRect(rectF,paint);*/

        //canvas.translate(width / 2, height / 2);//移动画布

 /*       Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);
         path2.addRect(0, -200, 200, 200, Path.Direction.CW);
      *//*    path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CCW);*//*

         canvas.clipPath(path2, Region.Op.DIFFERENCE);
      *//*  canvas.clipPath(path3,Region.Op.UNION);
        canvas.clipPath(path4,Region.Op.DIFFERENCE);*/

        Path path = new Path();
        //path.lineTo(100,100);
        //path.lineTo(100, 200);
        path.moveTo(0,500);
        path.lineTo(300, 200);
        path.lineTo(800, 400);
        path.lineTo(800, 800);
        path.lineTo(0, 800);

        canvas.drawPath(path, paint);



        Paint paint2 = new Paint();
        paint2.setStrokeWidth(5);//笔宽5像素
        paint2.setColor(Color.RED);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);


        Path path2 = new Path();
        path2.moveTo(0,600);
        path2.lineTo(400, 300);
        path2.lineTo(800, 500);
        path2.lineTo(800, 800);
        path2.lineTo(0, 800);

        canvas.drawPath(path2, paint2);

        Drawable drawable = new BitmapDrawable(bitmap);
      //  mylayout.setBackgroundDrawable(drawable);

    }


}
