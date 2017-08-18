package com.example.wangning.canvas.chart.pie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 饼图报表
 *
 * @author wangning
 * @version 1.0 2017-08-18
 * @since JDK 1.8
 * <p/>
 * <p/>
 * 画布坐标系
 * \
 * 第三象限         \   第四象限
 * \
 * -------------（0，0）---------------------> X
 * \
 * 第二象限         \    第一象限
 * \
 * >
 * Y
 * <p/>
 * 绘制起始角度为第一象限
 */
public class PieView extends ViewGroup {
    private static final String TAG = "PieView";
    final float mDensity;
    private float mRadius = 300.0f;//报表半径
    private List<LineSign> mLineSigns = new ArrayList<LineSign>();
    private float mPointOvalSpacing = 50.0f;
    private float mStartAngle;

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
        for (LineSign lineSign : mLineSigns) {
            drawArc(canvas, lineSign.getLineColor(), mStartAngle, 360 * lineSign.getPercent(), mRadius);
            drawPoint(lineSign, canvas, mStartAngle, 360 * lineSign.getPercent() / 2, mRadius + mPointOvalSpacing);
            mStartAngle = mStartAngle + 360 * lineSign.getPercent();

        }

    }

    /**
     * @param canvas
     * @param sweepAngle 角度
     * @param r          半径
     */
    private void drawPoint(LineSign lineSign, Canvas canvas, float startAngle, float sweepAngle, float r) {
        //30° 角度 的弧度 = 2*PI/360*30
        float y = (float) (r * Math.sin((startAngle + sweepAngle) * Math.PI / 180));
        float x = (float) (r * Math.cos((startAngle + sweepAngle) * Math.PI / 180));

        Paint paint = new Paint();
        paint.setColor(getResources().getColor(lineSign.getLineColor()));
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawCircle(x, y, lineSign.getPointRadius(), paint);
        drawLineSign(canvas, lineSign, x, y, paint);
    }

    private void drawArc(Canvas canvas, int color, float startAngle, float sweepAngle, float radius) {
        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(color));
        int center = 0;
        RectF rect = new RectF(center - radius, center - radius, center
                + radius, center + radius);
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
        mRadius = arcRadius;
    }


    public void drawLineSign(Canvas canvas, LineSign lineSign, float x, float y, Paint paint) {
        float turnX = 0.0f;//拐点X坐标
        float turnY = 0.0f;//拐点Y坐标

        Paint textPaint = new Paint();
        textPaint.setStrokeWidth(1);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(getResources().getColor(R.color.text_333333));
        textPaint.setTextSize(dp2px(13));//设置字体大小

        if (x > 0 && y > 0) {//绘制坐标系第一象限
            turnX = x + lineSign.getTurnXLength();
            turnY = y + lineSign.getTurnYLength();
            canvas.drawLine(x, y, turnX, turnY, paint);
            canvas.drawLine(turnX,
                    turnY,
                    getWidth(),
                    turnY,
                    paint);

            String aboveText = lineSign.getLineAboveText();
            String belowText = lineSign.getLineBelowText();
     /*       float textStartX = turnX+getTextWidth(text,textPaint);
            float textStartY = turnY - getTextHeight(text,textPaint);*/

            float aboveTextStartX = getWidth() / 2 - getTextWidth(aboveText, textPaint);
            float aboveTextStartY = turnY - getTextHeight(aboveText, textPaint);

            float belowTextStartX = getWidth() / 2 - getTextWidth(belowText, textPaint);
            float belowTextStartY = turnY + getTextHeight(belowText, textPaint);

            canvas.drawText(aboveText, aboveTextStartX, aboveTextStartY, textPaint);
            canvas.drawText(belowText, belowTextStartX, belowTextStartY, textPaint);

        } else if (x < 0 && y > 0) {//绘制坐标系第二象限
            turnX = x - lineSign.getTurnXLength();
            turnY = y + lineSign.getTurnYLength();
            canvas.drawLine(x, y, turnX, turnY, paint);
            canvas.drawLine(turnX,
                    turnY,
                    -getWidth(),
                    turnY,
                    paint);

            String aboveText = lineSign.getLineAboveText();
            String belowText = lineSign.getLineBelowText();

            float aboveTextStartX = -getWidth() / 2;
            float aboveTextStartY = turnY - getTextHeight(aboveText, textPaint);

            float belowTextStartX = -getWidth() / 2;
            float belowTextStartY = turnY + getTextHeight(belowText, textPaint);

            canvas.drawText(aboveText, aboveTextStartX, aboveTextStartY, textPaint);
            canvas.drawText(belowText, belowTextStartX, belowTextStartY, textPaint);

        } else if (x < 0 && y < 0) {//绘制坐标系第三象限
            turnX = x - lineSign.getTurnXLength();
            turnY = y - lineSign.getTurnYLength();
            canvas.drawLine(x, y, turnX, turnY, paint);
            canvas.drawLine(turnX,
                    turnY,
                    -getWidth(),
                    turnY,
                    paint);

            String aboveText = lineSign.getLineAboveText();
            String belowText = lineSign.getLineBelowText();

            float aboveTextStartX = -getWidth() / 2;
            float aboveTextStartY = turnY + getTextHeight(aboveText, textPaint);

            float belowTextStartX = -getWidth() / 2;
            float belowTextStartY = turnY - getTextHeight(belowText, textPaint);

            canvas.drawText(aboveText, aboveTextStartX, aboveTextStartY, textPaint);
            canvas.drawText(belowText, belowTextStartX, belowTextStartY, textPaint);


        } else if (x > 0 && y < 0) {//绘制坐标系第四象限
            turnX = x + lineSign.getTurnXLength();
            turnY = y - lineSign.getTurnYLength();
            canvas.drawLine(x, y, turnX, turnY, paint);
            canvas.drawLine(turnX,
                    turnY,
                    getWidth(),
                    turnY,
                    paint);

            String aboveText = lineSign.getLineAboveText();
            String belowText = lineSign.getLineBelowText();

            float aboveTextStartX = getWidth() / 2 - getTextWidth(aboveText, textPaint);
            float aboveTextStartY = turnY + getTextHeight(aboveText, textPaint);

            float belowTextStartX =  getWidth() / 2 - getTextWidth(aboveText, textPaint);
            float belowTextStartY = turnY - getTextHeight(belowText, textPaint);

            canvas.drawText(aboveText, aboveTextStartX, aboveTextStartY, textPaint);
            canvas.drawText(belowText, belowTextStartX, belowTextStartY, textPaint);
        }


    }


    public void setData(List<LineSign> lineSigns) {
        mLineSigns = lineSigns;
    }


    public void setPointOvalSpacing(float spacing) {
        mPointOvalSpacing = spacing;
    }


    /**
     * 获得文字宽度
     *
     * @param text
     * @param paint
     * @return
     */
    private int getTextWidth(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.width();
    }

    /**
     * 获得文字高度
     *
     * @param text
     * @param paint
     * @return
     */
    private int getTextHeight(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }
}
