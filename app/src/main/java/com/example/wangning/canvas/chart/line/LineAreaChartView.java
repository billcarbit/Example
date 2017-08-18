package com.example.wangning.canvas.chart.line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import com.example.wangning.R;

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
    private int mXDataMarginScale = 10;//X轴数据与刻度的距离
    private int mYDataMarginScale = 30;//Y轴数据与刻度的距离
    private List<PathLine> mLinePathList = new ArrayList<PathLine>();
    private int mMaxValueY = 1;

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
        canvas.drawColor(Color.WHITE);
        mLineX.setPaddingBottom(dp2px(26));
        mLineX.setPaddingRight(70);
        mLineX.setPaddingLeft(dp2px(48));

        mLineY.setPaddingTop(70);
        mLineY.setPaddingLeft(dp2px(48));
        mLineY.setPaddingBottom(dp2px(26));
        drawCoordinateAxis(canvas, mLineX, mLineY);
        drawPathLine(canvas, mLinePathList, mLineX, mLineY);
        drawGraticule(canvas, mLineX, mLineY);//画平行于X轴的标线
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
     * 画坐标轴
     */
    private void drawCoordinateAxis(Canvas canvas, LineX xLine, LineY yLine) {

        Paint linePaint = new Paint();
        int width = dp2px(1);
        xLine.setWidth(width);
        yLine.setWidth(width);
        linePaint.setStrokeWidth(width);
        linePaint.setColor(getResources().getColor(R.color.text_333333));


        Paint textPaint = new Paint();
        textPaint.setStrokeWidth(1);
        textPaint.setColor(getResources().getColor(R.color.text_666666));
        textPaint.setTextSize(dp2px(mFontSize));//设置字体大小
        textPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//设置字体类型
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);


        canvas.drawLine(xLine.getPaddingLeft(),
                getHeight() - xLine.getPaddingBottom(),
                getWidth() - xLine.getPaddingRight(),
                getHeight() - xLine.getPaddingBottom(),
                linePaint);//画X轴

        canvas.drawLine(yLine.getPaddingLeft(),
                yLine.getPaddingTop(),
                yLine.getPaddingLeft(),
                getHeight() - yLine.getPaddingBottom(),
                linePaint);//画Y轴

        drawXLineScale(canvas, textPaint, linePaint, xLine);//画X轴刻度

        drawYLineScale(canvas, textPaint, linePaint, yLine);//画Y轴刻度


    }

    private void drawPathLine(Canvas canvas, List<PathLine> pathLines, LineX lineX, LineY lineY) {
        for (PathLine pathLine : pathLines) {
            Paint paint = new Paint();
            paint.setStrokeWidth(pathLine.getStrokeWidth());
            paint.setColor(getResources().getColor(pathLine.getColor()));
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            Path path = new Path();
            List<Coordinate> coordinateList = pathLine.getCoordinateList();
            path.moveTo(lineX.getPaddingLeft() + lineY.getWidth()/2 ,
                    getHeight() - lineX.getPaddingBottom() - lineX.getWidth()/2);//将画笔移动至坐标原点
            for (int i = 0, length = coordinateList.size(); i < length; i++) {
                float y = convertValueToY(coordinateList.get(i).getY(), mMaxValueY, lineY);
                path.lineTo(lineX.getScaleXList().get(i).getX() +  lineY.getWidth()/2,
                        y);
            }
            //从最后一个点画一条垂直于X轴的直线，形成闭合
            path.lineTo(lineX.getScaleXList().get(coordinateList.size() - 1).getX() +  lineY.getWidth()/2,
                    lineY.getLength() + lineY.getPaddingTop() - lineX.getWidth()/2);
            //再画一条到原点的垂直于Y轴的直线，形成闭合
           /* path.lineTo(lineX.getPaddingLeft(),
                    lineY.getLength() + lineY.getPaddingTop());*/
            canvas.drawPath(path, paint);
        }
    }


    /**
     * 画X轴刻度
     */
    private void drawXLineScale(Canvas canvas, Paint paint, Paint linePaint, LineX xLine) {
        List<ScaleX> scaleXList = xLine.getScaleXList();

        //得出X轴长度
        xLine.setLength(canvas.getWidth() - xLine.getPaddingLeft() - xLine.getPaddingRight());

        //将X轴分成scaleXList.size() - 1 等分,得出每个刻度长度
        int perLength = scaleXList.size() == 0
                ? xLine.getLength()
                : xLine.getLength() / (scaleXList.size() - 1);

        for (int i = 0, length = scaleXList.size(); i < length; i++) {
            int x = xLine.getPaddingLeft() + perLength * i;
            int y = getHeight() - xLine.getPaddingBottom();
            //存入刻度绘制的起点位置
            scaleXList.get(i).setX(x);
            scaleXList.get(i).setY(y);

            canvas.drawLine(x,
                    y,
                    x,
                    y + mScaleLength,
                    linePaint);//画X轴刻度

            String data = scaleXList.get(i).getDataX().getData();
            //每个刻度的数据
            canvas.drawText(data,
                    x - getTextWidth(data, paint) / 2,
                    y + mScaleLength + mXDataMarginScale + getTextHeight(data, paint),
                    paint);
        }


    }


    /**
     * 画Y轴刻度及数据
     *
     * @param canvas
     * @param paint
     * @param yLine
     */
    private void drawYLineScale(Canvas canvas, Paint paint, Paint linePaint, LineY yLine) {
        List<ScaleY> scaleYList = yLine.getScaleYList();
        //得出Y轴长度
        yLine.setLength(canvas.getHeight() - yLine.getPaddingBottom() - yLine.getPaddingTop());

        //将Y轴分成 scaleYList.size() 等分，得出每个刻度长度
        int perLength = scaleYList.size() == 0
                ? yLine.getLength()
                : yLine.getLength() / scaleYList.size();

        //画0刻度
        canvas.drawLine(yLine.getPaddingLeft(),
                getHeight() - yLine.getPaddingBottom(),
                yLine.getPaddingLeft() - mScaleLength,
                getHeight() - yLine.getPaddingBottom(),
                linePaint);
        //画0刻度数据
        canvas.drawText("0",
                yLine.getPaddingLeft() - mScaleLength - mYDataMarginScale - getTextWidth("0", paint),
                getHeight() - yLine.getPaddingBottom() + getTextHeight("0", paint) / 2,
                paint);

        for (int i = 0, length = scaleYList.size(); i < length; i++) {
            int x = yLine.getPaddingLeft();
            int y = getHeight() - yLine.getPaddingBottom() - perLength * (i + 1);
            //存入刻度绘制的起点位置
            scaleYList.get(i).setX(x);
            scaleYList.get(i).setY(y);

            canvas.drawLine(x,
                    y,
                    x - mScaleLength,
                    y,
                    linePaint);//画Y轴刻度

            String data = scaleYList.get(i).getDataY().getData();

            //每个刻度的数据
            canvas.drawText(data,
                    x - mScaleLength - mYDataMarginScale - getTextWidth(data, paint),
                    y + getTextHeight(data, paint) / 2,
                    paint);

        }

    }


    private void drawGraticule(Canvas canvas, LineX xLine, LineY yLine) {
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setColor(getResources().getColor(R.color.divider_ccc));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        List<ScaleY> scaleYList = yLine.getScaleYList();
        for (ScaleY scaleY : scaleYList) {
            canvas.drawLine(scaleY.getX(), scaleY.getY(), scaleY.getX() + xLine.getLength(), scaleY.getY(), paint);
        }
    }

    /**
     * 获得文字宽度
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
     * @param text
     * @param paint
     * @return
     */
    private int getTextHeight(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }


    /**
     * 设置字体的大小
     *
     * @param fontSizeDp
     */
    public void setFontSize(int fontSizeDp) {
        mFontSize = fontSizeDp;
    }

    /**
     * 设置刻度线的长度
     *
     * @param scaleLength
     */
    public void setScaleLength(int scaleLength) {
        mScaleLength = scaleLength;
    }

    /**
     * 设置刻度数据距离画布左边缘和下边缘的距离
     *
     * @param xDataMarginScale
     * @param yDataMarginScale
     */
    public void setScaleDataPadding(int xDataMarginScale, int yDataMarginScale) {
        mXDataMarginScale = xDataMarginScale;//X轴数据与刻度的距离
        mYDataMarginScale = yDataMarginScale;//Y轴数据与刻度的距离
    }

    public void setLinePathList(List<PathLine> linePathList) {
        mLinePathList = linePathList;
    }

    /**
     * 将值转化为Y轴坐标值
     *
     * @param value
     * @param max
     * @return 所在Y轴画布上的位置
     */
    private float convertValueToY(float value, float max, LineY yLine) {
        int lengthY = yLine.getLength();
        float y = getHeight() -
                (lengthY * (value / max) + yLine.getPaddingBottom());
        return y;
    }

    public void setMaxValueY(int maxY) {
        mMaxValueY = maxY;
    }
}
