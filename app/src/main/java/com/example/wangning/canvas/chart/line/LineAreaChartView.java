package com.example.wangning.canvas.chart.line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 折线面积图
 *
 * @author wangning
 * @version 3.0 2017-08-15
 * @since JDK 1.8
 */
public class LineAreaChartView extends ViewGroup {
    private static final String TAG = "LineAreaChartView";
    final float mDensity;
    private LineX mLineX = new LineX();
    private LineY mLineY = new LineY();
    private int mFontSize = 12;
    private int mScaleLength = 20;//刻度长度
    private Context mContext;

    public LineAreaChartView(Context context) {
        this(context, null);
    }


    public LineAreaChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mDensity = mContext.getResources().getDisplayMetrics().density;
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

    /**
     * px转成dp
     *
     * @param px 像素数值
     * @return dp数值
     */
    private int px2dp(float px) {
        return (int) (px / mDensity + 0.5f);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e(TAG, "onLayout: ");
        setWillNotDraw(false);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw: ");
        mLineX.setPaddingBottom(dp2px(26));
        mLineX.setPaddingRight(70);
        mLineX.setPaddingLeft(dp2px(48));

        mLineY.setPaddingTop(70);
        mLineY.setPaddingLeft(dp2px(48));
        mLineY.setPaddingBottom(dp2px(26));
        coordinate(canvas, mLineX, mLineY);

 /*       Paint paint = new Paint();
        paint.setStrokeWidth(5);//笔宽5像素
        paint.setColor(Color.GREEN);//设置为绿笔
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawColor(Color.GRAY);

        Path path = new Path();

        path.moveTo(0, 500);
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
        path2.moveTo(0, 600);
        path2.lineTo(400, 300);
        path2.lineTo(800, 500);
        path2.lineTo(800, 800);
        path2.lineTo(0, 800);

        canvas.drawPath(path2, paint2);*/


    }

    public void setYData(List<DataY> yDataList) {
        List<ScaleY> scaleYList = new ArrayList<>();
        for (DataY dataY : yDataList) {
            ScaleY scaleY = new ScaleY();
            scaleY.setDataY(dataY);
            scaleYList.add(scaleY);
        }
        mLineY.setScaleYList(scaleYList);
    }

    public void setXData(List<DataX> xDataList) {
        List<ScaleX> scaleXList = new ArrayList<>();
        for (DataX dataX : xDataList) {
            ScaleX scaleX = new ScaleX();
            scaleX.setDataX(dataX);
            scaleXList.add(scaleX);
        }
        mLineX.setScaleXList(scaleXList);
    }

    /**
     * 画坐标
     */
    private void coordinate(Canvas canvas, LineX xLine, LineY yLine) {
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setColor(Color.WHITE);
        paint.setTextSize(dp2px(mFontSize));//设置字体大小
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//设置字体类型
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawColor(Color.GRAY);

        canvas.drawLine(xLine.getPaddingLeft(),
                getHeight() - xLine.getPaddingBottom(),
                getWidth() - xLine.getPaddingRight(),
                getHeight() - xLine.getPaddingBottom(),
                paint);//画X轴
        canvas.drawLine(yLine.getPaddingLeft(), yLine.getPaddingTop(), yLine.getPaddingLeft(), getHeight() - yLine.getPaddingBottom(), paint);//画Y轴

        drawXLineScale(canvas, paint, xLine);


    }

    /**
     * 画X轴刻度
     */
    private void drawXLineScale(Canvas canvas, Paint paint, LineX xLine) {
        List<ScaleX> scaleXList = xLine.getScaleXList();

        //得出X轴长度
        xLine.setLength(canvas.getWidth() - xLine.getPaddingLeft() - xLine.getPaddingRight());

        //将X轴分成scaleXList.size() 等分,得出每个刻度长度
        int perLength = scaleXList.size() == 0
                ? xLine.getLength()
                : xLine.getLength() / (scaleXList.size() - 1);

        for (int i = 0, length = scaleXList.size(); i < length; i++) {
            canvas.drawLine(xLine.getPaddingLeft() + perLength * i,
                    getHeight() - xLine.getPaddingBottom(),
                    xLine.getPaddingLeft() + perLength * i,
                    getHeight() - xLine.getPaddingBottom() + mScaleLength,
                    paint);//画X轴刻度

            //每个刻度的数据
            canvas.drawText(scaleXList.get(i).getDataX().getData(),
                    xLine.getPaddingLeft() + perLength * i - 30,
                    getHeight() - xLine.getPaddingBottom() + mScaleLength + 40,
                    paint);
        }


    }

    public void setFontSize(int fontSizeDp) {
        mFontSize = fontSizeDp;
    }

    public void setScaleLength(int scaleLength) {
        mScaleLength = scaleLength;
    }
}
