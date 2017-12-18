package com.example.wangning.canvas.chart.columnar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.wangning.R;

import java.math.BigDecimal;
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
    private int mYFontSize = 12;
    private int mColFontSize = 10;
    private int mScaleLength = 10;//刻度长度
    private int mYDataMarginScale = 20;//Y轴数据与刻度的距离
    private int mMaxValueY;
    private List<AColumnar> mColumnarList = new ArrayList<AColumnar>();
    private float mSpacing = 10;//柱子间隔
    private List<PathLine> mLinePathList = new ArrayList<PathLine>();
    private OnTurnCircleClickListener mOnTurnCircleClickListener;
    private int mTurnPointRadius;
    private int mTurnPointStroke;
    private int mTurnPointCenterRadius;
    private Paint mTurnPointCenterPaint;

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
        initTurnPoint();
        initPaint();
        canvas.drawColor(Color.WHITE);
        mLineX.setPaddingBottom(dp2px(10));

        mLineY.setPaddingTop(10);
        mLineY.setPaddingBottom(dp2px(10));

        drawCoordinateAxis(canvas, mLineX, mLineY);
        drawColumnar(mColumnarList, canvas, mLineX, mLineY);
        drawColumnarText(mColumnarList, canvas, mLineX, mLineY);
        drawPathLine(canvas, mLinePathList, mLineX, mLineY);
        drawGraticule(canvas, mLineX, mLineY);//画平行于X轴的标线
        drawTurnCircle(canvas, mLinePathList, mLineX, mLineY);
    }

    private void initTurnPoint() {
        mTurnPointRadius = dp2px(3);
        mTurnPointStroke = dp2px(1);
        mTurnPointCenterRadius = mTurnPointRadius - mTurnPointStroke / 2;
    }

    private void initPaint() {
        mTurnPointCenterPaint = new Paint();
        mTurnPointCenterPaint.setStrokeWidth(1);
        mTurnPointCenterPaint.setColor(getResources().getColor(R.color.white));
        mTurnPointCenterPaint.setAntiAlias(true);
        mTurnPointCenterPaint.setStyle(Paint.Style.FILL);

    }

    private void clearAllTurnCircleSelected(List<Coordinate> coordinateList) {
        for (Coordinate coordinate : coordinateList) {
            coordinate.setSelected(false);
        }
    }


    /**
     * 画转折圆点
     *
     * @param canvas
     * @param pathLines
     * @param lineX
     * @param lineY
     */
    private void drawTurnCircle(Canvas canvas, List<PathLine> pathLines, LineX lineX, LineY lineY) {
        Paint circlePaint = new Paint();
        Paint circleCenterPaint = new Paint();
        for (PathLine pathLine : pathLines) {
            List<Coordinate> coordinateList = pathLine.getCoordinateList();

            circlePaint.setStrokeWidth(mTurnPointStroke);
            circlePaint.setColor(getResources().getColor(pathLine.getColor()));
            circlePaint.setAntiAlias(true);
            circlePaint.setStyle(Paint.Style.STROKE);

            circleCenterPaint.setStrokeWidth(pathLine.getStrokeWidth());
            circleCenterPaint.setColor(getResources().getColor(R.color.white));
            circleCenterPaint.setAntiAlias(true);
            circleCenterPaint.setStyle(Paint.Style.FILL);

            int spacing = dp2px(mSpacing);
            float columnarWidth = calculateColumnarWidth(mColumnarList);
            float originX = lineX.getPaddingLeft() + lineY.getWidth() / 2 + spacing + columnarWidth / 2;
            for (int i = 0; i < coordinateList.size(); i++) {
                Coordinate coordinate = coordinateList.get(i);
                float y = convertValueToY(Float.parseFloat(coordinate.getValY()), mMaxValueY, lineY);
                coordinate.setY(y);
                circleCenterPaint.setColor(
                        coordinate.isSelected()
                                ? circlePaint.getColor()
                                : getResources().getColor(R.color.white));
                if (i == 0) {
                    canvas.drawCircle(originX, y, mTurnPointRadius, circlePaint);
                    canvas.drawCircle(originX, y, mTurnPointCenterRadius, circleCenterPaint);
                } else {
                    originX = originX + spacing + columnarWidth;
                    canvas.drawCircle(originX, y, mTurnPointRadius, circlePaint);
                    canvas.drawCircle(originX, y, mTurnPointCenterRadius, circleCenterPaint);
                }
                int touchExpand = dp2px(6);
                int radius = mTurnPointRadius + mTurnPointStroke / 2;
                coordinate.setTouchXStart(originX - radius - touchExpand);
                coordinate.setTouchYStart(y - radius - touchExpand);
                coordinate.setTouchXEnd(originX + radius + touchExpand);
                coordinate.setTouchYEnd(y + radius + touchExpand);
                coordinate.setX(originX);
            }
        }
    }

    private void drawPathLine(Canvas canvas, List<PathLine> pathLines, LineX lineX, LineY lineY) {
        Paint paint = new Paint();
        for (PathLine pathLine : pathLines) {
            List<Coordinate> coordinateList = pathLine.getCoordinateList();

            paint.setStrokeWidth(dp2px(pathLine.getStrokeWidth()));
            paint.setColor(getResources().getColor(pathLine.getColor()));
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);

            int spacing = dp2px(mSpacing);
            float columnarWidth = calculateColumnarWidth(mColumnarList);
            Path path = new Path();
            float originX = lineX.getPaddingLeft() + lineY.getWidth() / 2 + spacing + columnarWidth / 2;
            for (int i = 0; i < coordinateList.size(); i++) {
                Coordinate coordinate = coordinateList.get(i);
                float y = convertValueToY(Float.parseFloat(coordinate.getValY()), mMaxValueY, lineY);
                coordinate.setY(y);
                if (i == 0) {
                    path.moveTo(originX, y);//将画笔移动至起点
                } else {
                    originX = originX + spacing + columnarWidth;
                    path.lineTo(originX, y);
                }
            }
            canvas.drawPath(path, paint);
        }
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
                (lengthY * value/max + yLine.getPaddingBottom());
        return y;
    }

    /**
     * 画平行于X轴的y刻度标线
     *
     * @param canvas
     * @param xLine
     * @param yLine
     */
    private void drawGraticule(Canvas canvas, LineX xLine, LineY yLine) {
        List<ScaleY> scaleYList = yLine.getScaleYList();
        if (scaleYList == null) {
            return;
        }

        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setColor(getResources().getColor(R.color.divider_ccc));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        for (ScaleY scaleY : scaleYList) {
            canvas.drawLine(scaleY.getX(), scaleY.getY(), scaleY.getX() + xLine.getLength(), scaleY.getY(), paint);
        }
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
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.FILL);

        Paint textPaint = new Paint();
        textPaint.setStrokeWidth(1);
        textPaint.setColor(getResources().getColor(R.color.text_666666));
        textPaint.setTextSize(dp2px(mYFontSize));//设置字体大小
        textPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//设置字体类型
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);


        //根据实际数据计算出X,Y轴应该得到的paddingLeft
        int paddingLeft = getTextWidth(String.valueOf(mMaxValueY), textPaint) + mScaleLength + mYDataMarginScale;
        xLine.setPaddingLeft(paddingLeft);
        yLine.setPaddingLeft(paddingLeft);

        canvas.drawLine(xLine.getPaddingLeft(),
                getHeight() - xLine.getPaddingBottom(),
                getWidth() - xLine.getPaddingRight(),
                getHeight() - xLine.getPaddingBottom(),
                linePaint);//画X轴

        xLine.setLength(getWidth() - xLine.getPaddingRight() - xLine.getPaddingLeft());//设置X轴长度

        canvas.drawLine(xLine.getPaddingLeft(),
                getHeight() - xLine.getPaddingBottom(),
                xLine.getPaddingLeft(),
                getHeight() - xLine.getPaddingBottom() + mScaleLength,
                linePaint);//画X刻度

        canvas.drawLine(yLine.getPaddingLeft(),
                yLine.getPaddingTop(),
                yLine.getPaddingLeft(),
                getHeight() - yLine.getPaddingBottom(),
                linePaint);//画Y轴

        drawYLineScale(canvas, textPaint, linePaint, yLine);//画Y轴刻度及数据
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
        if (scaleYList == null || scaleYList.size() == 0) {
            return;
        }
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

            String data = scaleYList.get(i).getDataY().getValue();

            //每个刻度的数据
            canvas.drawText(data,
                    x - mScaleLength - mYDataMarginScale - getTextWidth(data, textPaint),
                    y + getTextHeight(data, textPaint) / 2,
                    textPaint);

        }

    }

    /**
     * 画柱子上的数字
     *
     * @param columnarList
     * @param canvas
     * @param xLine
     * @param yLine
     */
    private void drawColumnarText(List<AColumnar> columnarList, Canvas canvas, LineX xLine, LineY yLine) {
        Paint textPaint = new Paint();
        textPaint.setStrokeWidth(1);
        textPaint.setTextSize(dp2px(mColFontSize));//设置字体大小
        textPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//设置字体类型
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        float originX = 0.0f;
        int spacing = dp2px(mSpacing);
        float columnarWidth = calculateColumnarWidth(columnarList);
        for (int i = 0, length = columnarList.size(); i < length; i++) {
            if (i == 0) {
                originX = yLine.getPaddingLeft() + yLine.getWidth() / 2 + spacing;
            } else {
                originX = originX + columnarWidth + spacing;
            }
            String data = String.valueOf(columnarList.get(i).getData());
            textPaint.setColor(getResources().getColor(columnarList.get(i).getTopTextColor()));
            float y = getHeight()
                    - xLine.getPaddingBottom()
                    - xLine.getWidth() / 2
                    - columnarList.get(i).getHeight()
                    - dp2px(columnarList.get(i).getTopTextMargin())
                    - getTextHeight(data, textPaint);
            float x = originX + columnarWidth / 2 - getTextWidth(data, textPaint) / 2;
            canvas.drawText(data, x, y, textPaint);
        }
    }

    /**
     * 画柱子
     *
     * @param canvas
     * @param yLine
     */
    private void drawColumnar(List<AColumnar> columnarList, Canvas canvas, LineX xLine, LineY yLine) {
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        float columnarWidth = calculateColumnarWidth(columnarList);
        int maxVal = calculateColumnarMaxValue(columnarList);
        //等差数列通项公式 an=a1+(n-1)*d
        int n = 10;
        int a1 = 0;
        float d = (maxVal - a1) / (n - 1);
        int d2 = (int) (d + 0.9f);//向上取整，得到整数公差
        float maxVal2 = a1 + (n - 1) * d2;//得到新的最大值
        calculateColumnarHeight(maxVal2 / (9.0f / 10.0f), columnarList);

        Path path = new Path();
        int spacing = dp2px(mSpacing);
        float originX = 0.0f;
        float originY = getHeight() - xLine.getPaddingBottom() - xLine.getWidth();
        for (int i = 0, length = columnarList.size(); i < length; i++) {
            //将画笔移动至柱子的左下角
            if (i == 0) {
                originX = yLine.getPaddingLeft() + yLine.getWidth() / 2 + spacing;
            } else {
                originX = originX + columnarWidth + spacing;
            }
            path.moveTo(originX, originY);
            float height = columnarList.get(i).getHeight();
            float y = getHeight() - xLine.getPaddingBottom() - xLine.getWidth() - height;
            path.lineTo(originX, y);
            path.lineTo(originX + columnarWidth, y);
            path.lineTo(originX + columnarWidth, originY);//形成闭合
            paint.setColor(getResources().getColor(columnarList.get(i).getColor()));
            canvas.drawPath(path, paint);
            path.moveTo(originX + i * (columnarWidth + spacing), originY);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (PathLine pathLine : mLinePathList) {
                List<Coordinate> coordinateList = pathLine.getCoordinateList();
                for (int i = 0, length = coordinateList.size(); i < length; i++) {
                    Coordinate coordinate = coordinateList.get(i);
                    float touchXStart = coordinate.getTouchXStart();
                    float touchXEnd = coordinate.getTouchXEnd();
                    float touchYStart = coordinate.getTouchYStart();
                    float touchYEnd = coordinate.getTouchYEnd();

                    if (event.getX() > touchXStart &&
                            event.getX() < touchXEnd &&
                            event.getY() > touchYStart &&
                            event.getY() < touchYEnd) {
                        clearAllTurnCircleSelected(coordinateList);
                        coordinate.setSelected(true);
                        invalidate();
                        if (mOnTurnCircleClickListener != null) {
                            mOnTurnCircleClickListener.onTurnCircleClick(i, (int) coordinate.getX(), (int) coordinate.getY());
                        }
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public void setOnTurnCircleClickListener(OnTurnCircleClickListener listener) {
        mOnTurnCircleClickListener = listener;
    }

    /**
     * 计算出每个柱子的在Y轴对应的高度
     *
     * @param maxVal
     * @param aColumnarList
     */
    private void calculateColumnarHeight(float maxVal, List<AColumnar> aColumnarList) {
        for (AColumnar aColumnar : aColumnarList) {
            float percentVal = aColumnar.getData() / maxVal;
            aColumnar.setHeight(percentVal * mLineY.getLength());
        }
    }

    /**
     * 计算柱子在X轴的自适应宽度
     */
    private float calculateColumnarWidth(List<AColumnar> aColumnarList) {
        int columnarNum = aColumnarList.size();
        if (columnarNum <= 0) {
            return 0.0f;
        }
        //X轴的长度 = 柱子宽度 * columnarNum + mSpacing * (columnarNum + 1)
        float width = (mLineX.getLength() - dp2px(mSpacing) * (columnarNum + 1)) / columnarNum;
        for (AColumnar aColumnar : aColumnarList) {
            aColumnar.setWidth(width);
        }
        return width;
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

    public int getYFontSize() {
        return mYFontSize;
    }

    public void setYFontSize(int yFontSize) {
        this.mYFontSize = yFontSize;
    }

    public int getColFontSize() {
        return mColFontSize;
    }

    public void setColFontSize(int colFontSize) {
        this.mColFontSize = colFontSize;
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

    public void setYData(List<DataY> yDataList) {
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
        if (scaleYList == null || scaleYList.size() == 0) {
            return;
        }
        ScaleY scaleY = scaleYList.get(scaleYList.size() - 1);
        mMaxValueY =Integer.parseInt(scaleY.getDataY().getValue());
    }

    public void setColumnarList(List<AColumnar> columnarList) {
        mColumnarList.clear();
        mColumnarList.addAll(columnarList);
    }

    public float getSpacing() {
        return mSpacing;
    }

    public void setSpacing(float spacing) {
        mSpacing = spacing;
    }

    public List<PathLine> getLinePathList() {
        return mLinePathList;
    }

    public void setLinePathList(List<PathLine> linePathList) {
        mLinePathList = linePathList;
    }

    public interface OnTurnCircleClickListener {
        void onTurnCircleClick(int position, int x, int y);
    }
}
