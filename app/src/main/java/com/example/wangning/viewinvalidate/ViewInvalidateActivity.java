package com.example.wangning.viewinvalidate;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2016-12-27
 * @since JDK 1.8
 */
public class ViewInvalidateActivity extends Activity {

    private static final String TAG = "ViewInvalidateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new View(getApplicationContext()) {

            Paint vPaint = new Paint();  //绘制样式物件
            int sweepAngle = 0;           //弧形角度
            int radius = 100;

            @Override
            protected void onDraw(Canvas canvas) {
                Log.d(TAG, "onDraw: sweepAngle=" + sweepAngle);
                super.onDraw(canvas);

                // 设定绘图样式
                vPaint.setColor(getResources().getColor(R.color.orange)); //画笔颜色
                vPaint.setAntiAlias(true);   //反锯齿
                vPaint.setStyle(Paint.Style.FILL);
                int center = radius;
                RectF rect = new RectF(center - radius, center - radius, center
                        + radius, center + radius);
                // 绘制一个弧形
                canvas.drawArc(rect, 0, sweepAngle, true, vPaint);

                // 弧形角度
                if ((sweepAngle += 10) > 360) {
                    sweepAngle = 0;
                }
                // 重绘, 再一次执行onDraw 程序
                invalidate();
            }
        });
    }
}
