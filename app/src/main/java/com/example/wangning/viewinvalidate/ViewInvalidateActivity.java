package com.example.wangning.viewinvalidate;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2016-12-27
 * @since JDK 1.8
 */
public class ViewInvalidateActivity  extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( new View(getApplicationContext()){

            Paint vPaint = new Paint();  //绘制样式物件
            private int i = 0;           //弧形角度

            @Override
            protected void onDraw (Canvas canvas) {
                // TODO Auto-generated method stub
                super.onDraw(canvas);

                // 设定绘图样式
                vPaint.setColor( 0xff00ffff ); //画笔颜色
                vPaint.setAntiAlias( true );   //反锯齿
                vPaint.setStyle( Paint.Style.STROKE);

                // 绘制一个弧形
                canvas.drawArc(new RectF(60, 120, 260, 320), 0, i, true, vPaint );

                // 弧形角度
                if( (i+=10) > 360 )
                    i = 0;

                // 重绘, 再一次执行onDraw 程序
                invalidate();
            }
        });
    }
}
