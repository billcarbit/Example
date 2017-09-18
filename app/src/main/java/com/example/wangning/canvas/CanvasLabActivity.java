package com.example.wangning.canvas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
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

    Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_lab);
        LinearLayout mylayout = (LinearLayout) findViewById(R.id.ll);
        Paint paint = new Paint();
        paint.setStrokeWidth(5);//笔宽5像素
        paint.setColor(Color.GREEN);//设置为绿笔
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;//获取屏幕的宽和高
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); //设置位图的宽高,bitmap为透明
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);//设置为透明，画布也是透明

        int orange_f76b1c = getResources().getColor(R.color.orange_f76b1c);
        int white_fdf6df = getResources().getColor(R.color.white_fdf6df);
        LinearGradient gradient = new LinearGradient(0, 0, 0, 600, Color.RED, Color.RED, Shader.TileMode.CLAMP);

        paint.setShader(gradient);


        Path path = new Path();
        path.moveTo(0, 0);
        path.cubicTo(300, 0, 300, 600, 600, 600);
        path.cubicTo(700, 600, 700, 200, 800, 200);
        canvas.drawPath(path, paint);


        Drawable drawable = new BitmapDrawable(bitmap);
        mylayout.setBackgroundDrawable(drawable);
    }


}
