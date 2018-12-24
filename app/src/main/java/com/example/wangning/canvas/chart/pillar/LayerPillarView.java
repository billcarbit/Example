package com.example.wangning.canvas.chart.pillar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.wangning.R;
import com.example.wangning.canvas.chart.columnar.AColumnar;
import com.example.wangning.canvas.chart.columnar.DataX;
import com.example.wangning.canvas.chart.columnar.DataY;
import com.example.wangning.canvas.chart.columnar.LineX;
import com.example.wangning.canvas.chart.columnar.LineY;
import com.example.wangning.canvas.chart.columnar.ScaleX;
import com.example.wangning.canvas.chart.columnar.ScaleY;

import java.util.ArrayList;
import java.util.List;

/**
 * 分层柱状图
 */
public class LayerPillarView extends ViewGroup {

    private final float mDensity;
    private Context mContext;
    private LineX mLineX = new LineX();
    private LineY mLineY = new LineY();
    private int mYFontSize = 12;
    private int mColFontSize = 10;
    private int mScaleLength = 10;//刻度长度
    private int mYDataMarginScale = 20;//Y轴数据与刻度的距离
    private int mMaxValueY;
    private List<AColumnar> mUpperColumnarList = new ArrayList<AColumnar>();//上层柱子数据
    private List<AColumnar> mLowerColumnarList = new ArrayList<AColumnar>();//下层柱子数据
    private float mSpacing = 10;//柱子间隔
    private int pillarBottomColor = R.color.blue_285ac2;//柱子渐变初始颜色
    private int pillarTopColor = R.color.blue_0ac2ff;//柱子渐变终止颜色

    public LayerPillarView(Context context) {
        this(context, null);
    }

    public LayerPillarView(Context context, AttributeSet attrs) {
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
        mLineX.setPaddingBottom(dp2px(50));

        mLineY.setPaddingTop(10);
        mLineY.setPaddingBottom(dp2px(50));

        drawCoordinateAxis(canvas, mLineX, mLineY);
        drawLowerColumnar(mLowerColumnarList, canvas, mLineX, mLineY);
        drawUpperColumnar(mUpperColumnarList, canvas, mLineX, mLineY);
        drawColumnarText(mLowerColumnarList, canvas, mLineX, mLineY);
        drawGraticule(canvas, mLineX, mLineY);//画平行于X轴的标线
        drawXLineScale(canvas, mLineX, mLineY, mLowerColumnarList);
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
                (lengthY * value / max + yLine.getPaddingBottom());
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
     * 画X轴数据
     */
    private void drawXLineScale(Canvas canvas, LineX xLine, LineY yLine, List<AColumnar> columnarList) {
        Paint textPaint = new Paint();
        textPaint.setStrokeWidth(1);
        textPaint.setColor(getResources().getColor(R.color.text_666666));
        textPaint.setTextSize(dp2px(mYFontSize));//设置字体大小
        textPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//设置字体类型
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        List<ScaleX> scaleXList = xLine.getScaleXList();
        if (scaleXList == null || scaleXList.isEmpty()) {
            return;
        }
        float originX = 0.0f;//每个柱子的绘制起点，矩形左下角顶点
        int spacing = dp2px(mSpacing);
        float columnarWidth = calculateColumnarWidth(columnarList);
        int columnNum = columnarList.size();
        int scaleXNum = scaleXList.size();
        int minNum = columnNum > scaleXNum ? scaleXNum : columnNum;
        for (int i = 0, length = minNum; i < length; i++) {
            if (i == 0) {
                originX = yLine.getPaddingLeft() + yLine.getWidth() / 2 + spacing;
            } else {
                originX = originX + columnarWidth + spacing;
            }
            String data = scaleXList.get(i).getDataX().getValue();
            float x = originX + columnarWidth / 2 - getTextWidth(data, textPaint) / 2;

            Path path = new Path();
            path.moveTo(x, getHeight() - xLine.getPaddingBottom() + getTextHeight(data, textPaint));//移动至X轴文字中心点
            path.lineTo(originX + columnarWidth, getHeight());//x:柱子右下角,y:控件最大高度
            canvas.drawTextOnPath(data, path, 20, 0, textPaint);//将字体倾斜
        }


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
            float y = columnarList.get(i).getHeight() - dp2px(columnarList.get(i).getTopTextMargin());
            float x = originX + columnarWidth / 2 - getTextWidth(data, textPaint) / 2;
            canvas.drawText(data, x, y, textPaint);
        }
    }

    /**
     * 画上层柱子
     *
     * @param canvas
     * @param yLine
     */
    private void drawUpperColumnar(List<AColumnar> columnarList, Canvas canvas, LineX xLine, LineY yLine) {
        int spacing = dp2px(mSpacing);
        float originX = 0.0f;
        float originY = getHeight() - xLine.getPaddingBottom() - xLine.getWidth();
        for (int i = 0, length = columnarList.size(); i < length; i++) {//遍历每个柱子对象
            float columnarWidth = calculateColumnarWidth(columnarList);
            calculateColumnarHeight(mMaxValueY, columnarList, yLine);//计算每个柱子在坐标系中的高度
            //将画笔移动至柱子的左下角
            if (i == 0) {
                originX = yLine.getPaddingLeft() + yLine.getWidth() / 2 + spacing;
            } else {
                originX = originX + columnarWidth + spacing;
            }

            float height = columnarList.get(i).getHeight();//每个柱子在坐标系中的高度
            LinearGradient gradient = new LinearGradient(originX, originY, originX, height,
                    getResources().getColor(pillarBottomColor),
                    getResources().getColor(pillarTopColor),
                    Shader.TileMode.CLAMP);

            Path path = new Path();
            path.moveTo(originX, originY);//移动至柱子左下角
            path.lineTo(originX, height);
            path.lineTo(originX + columnarWidth, height);
            path.lineTo(originX + columnarWidth, originY);
            path.close();//形成闭合

            Paint paint = new Paint();
            paint.setStrokeWidth(1);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setShader(gradient);//绘制渐变

            canvas.drawPath(path, paint);
        }
    }


    /**
     * 画上层柱子
     *
     * @param canvas
     * @param yLine
     */
    private void drawLowerColumnar(List<AColumnar> columnarList, Canvas canvas, LineX xLine, LineY yLine) {
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        float columnarWidth = calculateColumnarWidth(columnarList);

        calculateColumnarHeight(mMaxValueY, columnarList, yLine);//计算每个柱子在坐标系中的高度

        Path path = new Path();
        int spacing = dp2px(mSpacing);
        float originX = 0.0f;
        float originY = getHeight() - xLine.getPaddingBottom() - xLine.getWidth();
        for (int i = 0, length = columnarList.size(); i < length; i++) {//遍历每个柱子对象
            //将画笔移动至柱子的左下角
            if (i == 0) {
                originX = yLine.getPaddingLeft() + yLine.getWidth() / 2 + spacing;
            } else {
                originX = originX + columnarWidth + spacing;
            }
            path.moveTo(originX, originY);
            float height = columnarList.get(i).getHeight();//每个柱子在坐标系中的高度
            path.lineTo(originX, height);
            path.lineTo(originX + columnarWidth, height);
            path.lineTo(originX + columnarWidth, originY);
            path.close();//形成闭合
            paint.setColor(getResources().getColor(columnarList.get(i).getColor()));
            canvas.drawPath(path, paint);
            path.moveTo(originX + i * (columnarWidth + spacing), originY);//把画笔移动至下一个柱子的左下角
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return super.onTouchEvent(event);
    }


    /**
     * 计算出每个柱子的在Y轴对应的高度
     *
     * @param maxVal
     * @param aColumnarList
     */
    private void calculateColumnarHeight(float maxVal, List<AColumnar> aColumnarList, LineY yLine) {
        for (AColumnar aColumnar : aColumnarList) {
            float y = convertValueToY(aColumnar.getData(), maxVal, yLine);
            aColumnar.setHeight(y);
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

    /**
     * 设置Y轴数据
     *
     * @param yDataList
     */
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

    /**
     * 设置X轴数据
     *
     * @param xDataList
     */
    public void setXData(List<DataX> xDataList) {
        List<ScaleX> scaleXList = new ArrayList<>();
        for (DataX dataX : xDataList) {
            ScaleX scaleX = new ScaleX();
            scaleX.setDataX(dataX);
            scaleXList.add(scaleX);
        }
        mLineX.setScaleXList(scaleXList);
    }

    private void setMaxValueY(LineY lineY) {
        List<ScaleY> scaleYList = lineY.getScaleYList();
        if (scaleYList == null || scaleYList.size() == 0) {
            return;
        }
        ScaleY scaleY = scaleYList.get(scaleYList.size() - 1);
        mMaxValueY = Integer.parseInt(scaleY.getDataY().getValue());
    }

    /**
     * 上层柱子
     *
     * @param columnarList
     */
    public void setUpperColumnarList(List<AColumnar> columnarList) {
        mUpperColumnarList.clear();
        mUpperColumnarList.addAll(columnarList);
    }

    /**
     * 下层柱子
     *
     * @param columnarList
     */
    public void setLowerColumnarList(List<AColumnar> columnarList) {
        mLowerColumnarList.clear();
        mLowerColumnarList.addAll(columnarList);
    }

    public float getSpacing() {
        return mSpacing;
    }

    public void setSpacing(float spacing) {
        mSpacing = spacing;
    }


    /**
     * 柱子渐变底部颜色
     * @param color
     */
    public void setPillarBottomColor(int color) {
        this.pillarBottomColor = color;
    }


    /**
     * 柱子渐变顶部颜色
     * @param color
     */
    public void setPillarTopColor(int color) {
        this.pillarTopColor = color;
    }
}
