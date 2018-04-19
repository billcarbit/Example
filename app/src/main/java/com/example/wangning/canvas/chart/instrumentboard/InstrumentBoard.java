package com.example.wangning.canvas.chart.instrumentboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import com.example.wangning.R;

/**
 * 罗盘
 * Created by Administrator on 2018/4/2.
 */
public class InstrumentBoard extends ViewGroup {
    private Indicator mIndicator;
    private int mBeginVal;//起始值
    private int mEndVal;//终止值
    private Context mContext;
    private float mDensity;
    private final static int LOW = R.color.green_4eeaa6;
    private final static int MID = R.color.orange_4eeaa6;
    private final static int HIGH = R.color.red_f3352c;
    private final static int STROKE_WIDTH = 20;
    private final static int RADIUS = 400;
    private float maxVal = 270;
    private float minVal = 0;
    private float currentVal = 0;
    private float mIndicatorPadding = 30;//指针距离仪表盘的间距

    public InstrumentBoard(Context context) {
        this(context, null);
    }

    public InstrumentBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mDensity = mContext.getResources().getDisplayMetrics().density;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        drawDividingRule(canvas);
        drawHighLevelArc(canvas);
        drawLowLevelArc(canvas);
        drawMidLevelArc(canvas);
        drawWhiteCenter(canvas);
        drawOrangeDotCenter(canvas);
        drawDotCenterHalo(canvas);
        drawIndicator(canvas, currentVal);
    }

    private final static int ruleInnerPadding = 60;
    private final static int ruleStroke = 3;
    private final static int ruleWidth = 30;
    private final static int ruleScaleLength = 30;//刻度长度
    private final static int innerRuleRadius = RADIUS + ruleInnerPadding + STROKE_WIDTH / 2;
    private final static int outerRuleRadius = innerRuleRadius + ruleWidth;
    private final static int beginAngle = 135;
    private final static int ruleScaleSweepAngle = 10;//10度画一个刻度

    private void drawDividingRule(Canvas canvas) {
        drawArc(canvas, R.color.divider_ccc, beginAngle, 270, innerRuleRadius, ruleStroke);
        drawArc(canvas, R.color.divider_ccc, beginAngle, 270, outerRuleRadius, ruleStroke);
        drawArc(canvas, R.color.white_eee, beginAngle, 270, innerRuleRadius + ruleWidth / 2, ruleWidth);
        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.divider_ccc));

        Paint paint2 = new Paint();
        paint2.setStrokeWidth(3);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        paint2.setColor(getResources().getColor(R.color.white));


        for (int i = 0, size = 270 / ruleScaleSweepAngle; i < size; i++) {
            int angle = beginAngle + ruleScaleSweepAngle * i;
            int whiteLineRadius = innerRuleRadius - ruleScaleLength;
            float beginX = (float) (innerRuleRadius * Math.cos((angle) * Math.PI / 180));
            float beginY = (float) (innerRuleRadius * Math.sin((angle) * Math.PI / 180));
            float endX = (float) (whiteLineRadius * Math.cos((angle) * Math.PI / 180));
            float endY = (float) (whiteLineRadius * Math.sin((angle) * Math.PI / 180));
            canvas.drawLine(0, 0, beginX, beginY, paint);
            canvas.drawLine(0, 0, endX, endY, paint2);
        }

    }


    /**
     * 绘制高等级扇区
     *
     * @param canvas
     */
    private void drawHighLevelArc(Canvas canvas) {
        drawArc(canvas, HIGH, 0, 45, RADIUS, STROKE_WIDTH);
    }

    /**
     * 绘制低等级扇区
     *
     * @param canvas
     */
    private void drawLowLevelArc(Canvas canvas) {
        drawArc(canvas, LOW, 135, 45, RADIUS, STROKE_WIDTH);
    }

    /**
     * 绘制中等级扇区
     *
     * @param canvas
     */
    private void drawMidLevelArc(Canvas canvas) {
        drawArc(canvas, MID, 180, 180, RADIUS, STROKE_WIDTH);
    }

    /**
     * 绘制扇区
     *
     * @param canvas
     * @param color
     * @param startAngle
     * @param sweepAngle
     * @param radius
     */
    private void drawArc(Canvas canvas, int color, float startAngle, float sweepAngle, float radius, float strokeWidth) {
        Paint paint = new Paint();
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(color));
        int center = 0;
        RectF rect = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        //画扇形,startAngle=0时起点是从3点钟开始
        canvas.drawArc(rect, startAngle, sweepAngle, true, paint);
    }


    /**
     * 绘制罗盘白底
     *
     * @param canvas
     */
    private void drawWhiteCenter(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.white));
        canvas.drawCircle(0, 0, RADIUS - STROKE_WIDTH / 2, paint);
    }

    /**
     * 绘制橘色圆心
     */
    private void drawOrangeDotCenter(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(Indicator.COLOR));
        canvas.drawCircle(0, 0, Indicator.DOT_RADIUS, paint);
    }

    /**
     * 绘制指针圆心光晕
     *
     * @param canvas
     */
    private void drawDotCenterHalo(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(Indicator.HALO_COLOR));
        canvas.drawCircle(0, 0, Indicator.HALO_RADIUS, paint);
    }


    /**
     * 绘制指针
     */
    private void drawIndicator(Canvas canvas, float currentVal) {
        float indicatorRadius = RADIUS - STROKE_WIDTH / 2 - mIndicatorPadding;
        float beginAngle = 135;
        float percent = currentVal / maxVal;
        float sweepAngle = (360 - 90) * percent;
        float topX = (float) (indicatorRadius * Math.cos((beginAngle + sweepAngle) * Math.PI / 180));
        float topY = (float) (indicatorRadius * Math.sin((beginAngle + sweepAngle) * Math.PI / 180));
        float bottomAngle1 = beginAngle + sweepAngle - Indicator.TWO_SIDES_ANGLE;
        float bottomX1 = (float) (Indicator.DOT_RADIUS * Math.cos((bottomAngle1) * Math.PI / 180));
        float bottomY1 = (float) (Indicator.DOT_RADIUS * Math.sin((bottomAngle1) * Math.PI / 180));
        float bottomAngle2 = beginAngle + sweepAngle + Indicator.TWO_SIDES_ANGLE;
        float bottomX2 = (float) (Indicator.DOT_RADIUS * Math.cos((bottomAngle2) * Math.PI / 180));
        float bottomY2 = (float) (Indicator.DOT_RADIUS * Math.sin((bottomAngle2) * Math.PI / 180));
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(Indicator.COLOR));
        paint.setStrokeWidth(Indicator.WIDTH);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        Path path = new Path();
        path.moveTo(topX, topY);
        path.lineTo(bottomX1, bottomY1);
        path.lineTo(bottomX2, bottomY2);
        path.lineTo(topX, topY);
        canvas.drawPath(path, paint);
    }

    public void setMaxVal(float maxVal) {
        this.maxVal = maxVal;
    }

    public void setCurrentVal(float val) {
        this.currentVal = val;
    }


}
