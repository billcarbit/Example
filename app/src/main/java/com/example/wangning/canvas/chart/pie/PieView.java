package com.example.wangning.canvas.chart.pie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-08-18
 * @since JDK 1.8
 */
public class PieView extends ViewGroup {
    private static final String TAG = "PieView";
    final float mDensity;
    private float mArcRadius = 300.0f;

    public PieView(Context context) {
        this(context, null);
    }


    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDensity = context.getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        setWillNotDraw(false);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(0, 0, 400, paint);

        drawArc(canvas, 180, 90);
        drawPoint(canvas, 180, 90 / 2, 400);
    }

    /**
     * @param canvas
     * @param sweepAngle 角度
     * @param r          半径
     */
    private void drawPoint(Canvas canvas, float startAngle, float sweepAngle, int r) {
        //30° 角度 的弧度 = 2*PI/360*30
        double y = r * Math.sin((startAngle + 90 - sweepAngle) * Math.PI / 180);
        double x = r * Math.cos((startAngle + 90 - sweepAngle) * Math.PI / 180);
        Log.e(TAG, "drawPoint: x=" + x + ",y=" + y);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        //画这个点，从6点钟开始，所以要用 startAngle + 90 - sweepAngle,得到从3点钟开始走过的度数,eg:
        //sweepAngle为30度，那么相当于它从3点钟开始就走过了 90 - 30 = 60度
        canvas.drawCircle((float) y, (float) x, 20, paint);
    }

    private void drawArc(Canvas canvas, float startAngle, float sweepAngle) {
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        int center = 0;
        RectF rect = new RectF(center - mArcRadius, center - mArcRadius, center
                + mArcRadius, center + mArcRadius);
        //画扇形,startAngle=0时起点是从3点钟开始
        canvas.drawArc(rect, startAngle, sweepAngle, true, paint);
    }

    /**
     * dp转成px
     *
     * @param dp dp数值
     * @return px数值
     */
    private int dp2px(float dp) {
        return (int) (dp * mDensity + 0.5f);
    }

    public void setArcRadius(float arcRadius) {
        mArcRadius = arcRadius;
    }


}
