package com.example.wangning.canvas.chart.columnar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.example.wangning.R;
import com.example.wangning.canvas.chart.line.*;
import com.example.wangning.canvas.chart.line.DataY;
import com.example.wangning.canvas.chart.line.LineY;
import com.example.wangning.canvas.chart.line.ScaleY;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱状报表
 *
 * @author wangning
 * @version 1.0 2017-09-11
 * @since JDK 1.8
 */
public class ColumnarChartView extends ViewGroup {
    private final float mDensity;
    private Context mContext;
    private LineX mLineX = new LineX();
    private LineY mLineY = new LineY();
    private int mFontSize = 12;
    private int mScaleLength = 20;//刻度长度
    private int mYDataMarginScale = 30;//Y轴数据与刻度的距离
    private int mMaxValueY = 1;
    private List<AColumnar> mColumnarList = new ArrayList<AColumnar>();
    private int mSpacing = 10;//柱子间隔
    private float mColumnarWidth;//柱子宽度

    public ColumnarChartView(Context context) {
        this(context, null);
    }

    public ColumnarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mDensity = mContext.getResources().getDisplayMetrics().density;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        setWillNotDraw(false);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        mLineX.setPaddingBottom(dp2px(26));
        mLineX.setPaddingRight(70);
        mLineX.setPaddingLeft(dp2px(48));

        mLineY.setPaddingTop(70);
        mLineY.setPaddingLeft(dp2px(48));
        mLineY.setPaddingBottom(dp2px(26));

        drawCoordinateAxis(canvas, mLineX, mLineY);
        drawColumnar(canvas, mLineX, mLineY);
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
        linePaint.setColor(getResources().getColor(R.color.text_666666));

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

        xLine.setLength(getWidth() - xLine.getPaddingRight() - xLine.getPaddingLeft());//设置X轴长度

        canvas.drawLine(yLine.getPaddingLeft(),
                yLine.getPaddingTop(),
                yLine.getPaddingLeft(),
                getHeight() - yLine.getPaddingBottom(),
                linePaint);//画Y轴


        drawYLineScale(canvas, textPaint, linePaint, yLine);//画Y轴刻度


    }

    /**
     * 画Y轴刻度及数据
     *
     * @param canvas
     * @param textPaint
     * @param yLine
     */
    private void drawYLineScale(Canvas canvas, Paint textPaint, Paint linePaint, LineY yLine) {
        List<ScaleY> scaleYList = yLine.getScaleYList();
        //得出Y轴长度
        yLine.setLength(canvas.getHeight() - yLine.getPaddingBottom() - yLine.getPaddingTop());

        //将Y轴分成 scaleYList.size() -1 等分，得出每个刻度长度
        int perLength = scaleYList.size() - 1 == 0
                ? yLine.getLength()
                : yLine.getLength() / (scaleYList.size() - 1);

        for (int i = 0, length = scaleYList.size(); i < length; i++) {
            int x = yLine.getPaddingLeft();
            int y = getHeight() - yLine.getPaddingBottom() - perLength * i;
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
                    x - mScaleLength - mYDataMarginScale - getTextWidth(data, textPaint),
                    y + getTextHeight(data, textPaint) / 2,
                    textPaint);

        }

    }

    /**
     * 画柱子
     *
     * @param canvas
     * @param yLine
     */
    private void drawColumnar(List<AColumnar> columnarList, Canvas canvas, LineX xLine, LineY yLine) {
        float columnarWidth = calculateColumnarWidth(columnarList);
        int max = calculateColumnarMaxValue(columnarList);

    }

    /**
     * 计算柱子的自适应宽度
     */
    private float calculateColumnarWidth(List<AColumnar> aColumnarList) {
        int columnarNum = aColumnarList.size();
        //X轴的长度 = 柱子宽度 * columnarNum + mSpacing * (columnarNum + 1)
        return (mLineX.getLength() - mSpacing * (columnarNum + 1)) / columnarNum;
    }

    /**
     * 计算所有柱子中的最大值
     *
     * @param aColumnarList
     */
    private int calculateColumnarMaxValue(List<AColumnar> aColumnarList) {
        if (aColumnarList.size() <= 0) {
            return 0;
        }
        int max = aColumnarList.get(0).getData();
        for (int i = 1, length = aColumnarList.size(); i < length; i++) {
            if (aColumnarList.get(i).getData() > max) {
                max = aColumnarList.get(i).getData();
            }
        }
        return max;
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

    public int getFontSize() {
        return mFontSize;
    }

    public void setFontSize(int size) {
        mFontSize = size;
    }

    public int getScaleLength() {
        return mScaleLength;
    }

    public void setScaleLength(int scaleLength) {
        mScaleLength = scaleLength;
    }

    public int getYDataMarginScale() {
        return mYDataMarginScale;
    }

    public void setYDataMarginScale(int yDataMarginScale) {
        mYDataMarginScale = yDataMarginScale;
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

    public void setYData(List<com.example.wangning.canvas.chart.line.DataY> yDataList) {
        List<ScaleY> scaleYList = new ArrayList<>();
        for (DataY dataY : yDataList) {
            ScaleY scaleY = new ScaleY();
            scaleY.setDataY(dataY);
            scaleYList.add(scaleY);
        }
        mLineY.setScaleYList(scaleYList);
        setMaxValueY(mLineY);
    }

    private void setMaxValueY(LineY lineY) {
        List<ScaleY> scaleYList = lineY.getScaleYList();
        ScaleY scaleY = scaleYList.get(scaleYList.size() - 1);
        mMaxValueY = Integer.valueOf(scaleY.getDataY().getData());
    }

    public void setColumnarList(List<AColumnar> columnarList) {
        mColumnarList.clear();
        mColumnarList.addAll(columnarList);
    }

    public int getSpacing() {
        return mSpacing;
    }

    public void setSpacing(int spacing) {
        mSpacing = spacing;
    }
}
