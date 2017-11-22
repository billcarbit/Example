package com.example.wangning.canvas.chart.line;

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
import com.example.wangning.canvas.chart.columnar.Coordinate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-18
 * @since JDK 1.8
 */
public class CurveCharView extends ViewGroup {
    final float mDensity;
    int perLength = 0;
    private LineX mLineX = new LineX();
    private LineY mLineY = new LineY();
    private int mFontSize = 12;
    private int mScaleLength = 20;//刻度长度
    private Context mContext;
    private int mXDataMarginScale = 10;//X轴数据与刻度的距离
    private int mYDataMarginScale = 30;//Y轴数据与刻度的距离
    private List<PathLine> mLinePathList = new ArrayList<PathLine>();
    private int mMaxValueY;
    private int mScaleXMarginLeftAndRight = 100;
    private int mTurnPointRadius;
    private int mTurnPointSelectedRadius;//选中后的拐点半径
    private int mTurnPointStroke;
    private int mTurnPointCenterRadius;
    private int mTurnPointCenterSelectedRadius;//选中后的拐点中心半径
    private Paint mTurnPointCenterPaint;
    private OnTurnCircleClickListener mOnTurnCircleClickListener;


    public CurveCharView(Context context) {
        this(context, null);
    }

    public CurveCharView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mDensity = mContext.getResources().getDisplayMetrics().density;
    }

    private void initTurnPoint() {
        mTurnPointRadius = dp2px(3);
        mTurnPointStroke = dp2px(1);

        mTurnPointCenterRadius = mTurnPointRadius - mTurnPointStroke / 2;

        mTurnPointSelectedRadius = mTurnPointRadius + dp2px(1);
        mTurnPointCenterSelectedRadius = mTurnPointCenterRadius + dp2px(1);
    }

    private void initPaint() {
        mTurnPointCenterPaint = new Paint();
        mTurnPointCenterPaint.setStrokeWidth(1);
        mTurnPointCenterPaint.setColor(getResources().getColor(R.color.white));
        mTurnPointCenterPaint.setAntiAlias(true);
        mTurnPointCenterPaint.setStyle(Paint.Style.FILL);

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
        mLineX.setPaddingBottom(dp2px(26));
        mLineX.setPaddingRight(70);
        mLineY.setPaddingTop(70);
        mLineY.setPaddingBottom(dp2px(26));
        drawCoordinateAxis(canvas, mLineX, mLineY);
        drawPathLine(canvas, mLinePathList, mLineX, mLineY);
        drawGraticule(canvas, mLineX, mLineY);//画平行于X轴的标线
        drawTurnCircle(canvas, mLinePathList, mLineX, mLineY);
    }

    private void drawPathLine(Canvas canvas, List<PathLine> pathLines, LineX lineX, LineY lineY) {
        Paint whitePaint = new Paint();
        whitePaint.setColor(getResources().getColor(R.color.white));
        whitePaint.setStrokeWidth(dp2px(2));
        whitePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        whitePaint.setAntiAlias(true);

        Paint areaPaint = new Paint();
        int orange_f76b1c = getResources().getColor(R.color.orange_f76b1c);
        int white_fdf6df = getResources().getColor(R.color.white_fdf6df);
        LinearGradient gradient = new LinearGradient(0, convertValueToY(mMaxValueY, mMaxValueY, mLineY), 0, getHeight(), orange_f76b1c, white_fdf6df, Shader.TileMode.CLAMP);
        areaPaint.setShader(gradient);
        areaPaint.setAntiAlias(true);
        areaPaint.setStrokeWidth(1);
        areaPaint.setStyle(Paint.Style.FILL);


        Paint pathLinePaint = new Paint();
        pathLinePaint.setAntiAlias(true);
        pathLinePaint.setStyle(Paint.Style.STROKE);

        for (PathLine pathLine : pathLines) {
            List<Coordinate> coordinateList = pathLine.getCoordinateList();
            int sLength = lineX.getScaleXList().size();
            int cLength = coordinateList.size();
            if (cLength > sLength) {
                for (int i = 0; i < cLength - sLength; i++) {
                    coordinateList.remove(cLength - i - 1);
                }
            }
            areaPaint.setStrokeWidth(dp2px(pathLine.getStrokeWidth()));

            pathLinePaint.setStrokeWidth(dp2px(pathLine.getStrokeWidth()));
            pathLinePaint.setColor(getResources().getColor(pathLine.getColor()));

            Path path = new Path();
            float originX = lineX.getPaddingLeft() + lineY.getWidth() / 2 + mScaleXMarginLeftAndRight - dp2px(1);
            float originY = getHeight() - lineX.getPaddingBottom() - lineX.getWidth() / 2;
            float firstX = 0.0f;
            float firstY = 0.0f;
            float lastX = 0.0f;
            float lastY = 0.0f;
            float closeX = 0.0f;
            float closeY = 0.0f;
            path.moveTo(originX, originY);//将画笔移动至X
            for (int i = 0; i < coordinateList.size() && i < lineX.getScaleXList().size(); i++) {
                Coordinate coordinate = coordinateList.get(i);
                float x = lineX.getScaleXList().get(i).getX() + lineY.getWidth() / 2 - dp2px(1);
                float y = convertValueToY(coordinate.getValY(), mMaxValueY, lineY);
                coordinate.setX(x);
                coordinate.setY(y);
                if (i == 0) {
                    firstX = x;
                    firstY = y;
                    path.lineTo(x, y);
                } else {
                    Coordinate prevCoordinate = coordinateList.get(i - 1);
                    float prevX = prevCoordinate.getX();
                    float prevY = prevCoordinate.getY();
                    float firstControlPointX = prevX + (x - prevX) / 2.0f;
                    float firstControlPointY = prevY;
                    float secondControlPointX = prevX + (x - prevX) / 2.0f;
                    float secondControlPointY = y;

                    if (i == coordinateList.size() - 1) {
                        path.cubicTo(firstControlPointX, firstControlPointY, secondControlPointX, secondControlPointY, x + dp2px(2), y);
                        lastX = coordinateList.get(coordinateList.size() - 1).getX() + dp2px(2);
                        lastY = coordinateList.get(coordinateList.size() - 1).getY();
                    } else {
                        path.cubicTo(firstControlPointX, firstControlPointY, secondControlPointX, secondControlPointY, x, y);
                    }
                }


            }
            //从最后一个点画一条垂直于X轴的直线，形成闭合
            int cLastIndex = coordinateList.size() - 1;
            if (cLastIndex < lineX.getScaleXList().size() && cLastIndex > 0) {
                closeX = lineX.getScaleXList().get(cLastIndex).getX() + lineY.getWidth() / 2 + dp2px(2);
                closeY = lineY.getLength() + lineY.getPaddingTop() - lineX.getWidth() / 2;
                path.lineTo(closeX, closeY);
            }

            //再画一条到原点的垂直于Y轴的直线，形成闭合
           /* path.lineTo(lineX.getPaddingLeft(),
                    lineY.getLength() + lineY.getPaddingTop());*/
            canvas.drawPath(path, areaPaint);
            canvas.drawPath(path, pathLinePaint);
            canvas.drawLine(originX, originY, firstX, firstY, whitePaint);
            canvas.drawLine(lastX, lastY, closeX, closeY, whitePaint);
        }
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
     * 画坐标轴
     */
    private void drawCoordinateAxis(Canvas canvas, LineX xLine, LineY yLine) {

        Paint xLinePaint = new Paint();
        int xLineWidth = dp2px(1);
        xLine.setWidth(xLineWidth);
        xLinePaint.setStrokeWidth(xLineWidth);
        xLinePaint.setColor(getResources().getColor(R.color.divider_ddd));

        Paint yLinePaint = new Paint();
        int yLineWidth = dp2px(1);
        yLine.setWidth(yLineWidth);
        yLinePaint.setStrokeWidth(yLineWidth);
        yLinePaint.setColor(getResources().getColor(R.color.transparent));


        Paint scalePaint = new Paint();
        scalePaint.setStrokeWidth(dp2px(1));
        scalePaint.setColor(getResources().getColor(R.color.text_333333));

        Paint textPaint = new Paint();
        textPaint.setStrokeWidth(1);
        textPaint.setColor(getResources().getColor(R.color.text_999999));
        textPaint.setTextSize(dp2px(mFontSize));//设置字体大小
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
                xLinePaint);//画X轴


        canvas.drawLine(yLine.getPaddingLeft(),
                yLine.getPaddingTop(),
                yLine.getPaddingLeft(),
                getHeight() - yLine.getPaddingBottom(),
                yLinePaint);//画Y轴

        drawXLineScale(canvas, textPaint, scalePaint, xLine);//画X轴刻度

        drawYLineScale(canvas, textPaint, scalePaint, yLine);//画Y轴刻度


    }

    /**
     * 画X轴刻度
     */
    private void drawXLineScale(Canvas canvas, Paint paint, Paint linePaint, LineX xLine) {
        List<ScaleX> scaleXList = xLine.getScaleXList();

        //得出X轴长度
        xLine.setLength(canvas.getWidth() - xLine.getPaddingLeft() - xLine.getPaddingRight());

        int betweenLength = xLine.getLength() - 2 * mScaleXMarginLeftAndRight;
        //将X轴分成scaleXList.size() - 1 等分,得出每个刻度长度

        if (scaleXList.size() <= 1) {
            perLength = betweenLength;
        } else {
            perLength = scaleXList.size() == 0
                    ? betweenLength
                    : betweenLength / (scaleXList.size() - 1);
        }


        for (int i = 0, length = scaleXList.size(); i < length; i++) {
            int x = xLine.getPaddingLeft() + perLength * i + mScaleXMarginLeftAndRight;
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

            xLine.getScaleXList().get(i).getDataX().setStartX(x - getTextWidth(data, paint) / 2);
            xLine.getScaleXList().get(i).getDataX().setEndX(x + getTextWidth(data, paint) / 2);
            xLine.getScaleXList().get(i).getDataX().setStartY(y + mScaleLength + mXDataMarginScale);
            xLine.getScaleXList().get(i).getDataX().setEndY(y + mScaleLength + mXDataMarginScale + getTextHeight(data, paint));
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


            for (int i = 0; i < coordinateList.size() &&
                    lineX.getScaleXList() != null &&
                    i < lineX.getScaleXList().size(); i++) {
                float originX = lineX.getScaleXList().get(i).getX();
                Coordinate coordinate = coordinateList.get(i);
                float y = convertValueToY(coordinate.getValY(), mMaxValueY, lineY);
                coordinate.setY(y);
                if (coordinate.isSelected()) {
                    circleCenterPaint.setColor(getResources().getColor(R.color.orange_ffa263));
                    canvas.drawCircle(originX, y, mTurnPointSelectedRadius, circlePaint);
                    canvas.drawCircle(originX, y, mTurnPointCenterSelectedRadius, circleCenterPaint);
                } else {
                    circleCenterPaint.setColor(getResources().getColor(R.color.white));
                    canvas.drawCircle(originX, y, mTurnPointRadius, circlePaint);
                    canvas.drawCircle(originX, y, mTurnPointCenterRadius, circleCenterPaint);
                }


                int touchExpand = dp2px(10);
                int radius = mTurnPointRadius + mTurnPointStroke / 2;
                coordinate.setTouchXStart(originX - radius - touchExpand);
                coordinate.setTouchYStart(y - radius - touchExpand);
                coordinate.setTouchXEnd(originX + radius + touchExpand);
                coordinate.setTouchYEnd(y + radius + touchExpand);
                coordinate.setX(originX);
            }
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
                    x - mScaleLength - mYDataMarginScale - getTextWidth(data, paint),
                    y + getTextHeight(data, paint) / 2,
                    paint);

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

    private void clearAllTurnCircleSelected(List<Coordinate> coordinateList) {
        for (Coordinate coordinate : coordinateList) {
            coordinate.setSelected(false);
        }
    }

    public List<PathLine> getLinePathList() {
        return mLinePathList;
    }

    public void setLinePathList(List<PathLine> linePathList) {
        mLinePathList = linePathList;
    }

    public void setOnTurnCircleClickListener(OnTurnCircleClickListener listener) {
        mOnTurnCircleClickListener = listener;
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
     * 将值转化为Y轴坐标值
     *
     * @param value
     * @param max
     * @return 所在Y轴画布上的位置
     */
    private float convertValueToY(float value, float max, LineY yLine) {
        int lengthY = yLine.getLength();
        int originY = yLine.getPaddingTop() + lengthY;
        if (max == 0) {
            return originY;
        }
        List<ScaleY> scaleYList = yLine.getScaleYList();
        if (scaleYList == null || scaleYList.size() == 0) {
            return originY;
        }
        int orginY = Integer.valueOf(scaleYList.get(0).getDataY().getData());
        float multiplyVal = value - orginY;
        // BigDecimal multiplyVal = value.subtract(new BigDecimal(scaleYList.get(0).getDataY().getData()));
        //float percent = multiplyVal.divide(max, 5, BigDecimal.ROUND_HALF_UP).floatValue();
        float percent = multiplyVal / max;
        float perValPx = (float)lengthY / (float) (max - orginY);
        float y = getHeight() -
                (multiplyVal * perValPx + yLine.getPaddingBottom());
        float y2 = yLine.getPaddingTop() + (max - value) * perValPx;
         Log.e("WN", "convertValueToY: lengthY=" + lengthY + ",percent=" + percent + ",multiplyVal=" + multiplyVal + ",value=" + value + ",max=" + max + ",y=" + y+",heightVal="+multiplyVal * perValPx);
        return y2;
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
        if (scaleYList != null && scaleYList.size() > 0) {
            ScaleY scaleY = scaleYList.get(scaleYList.size() - 1);
            mMaxValueY = Integer.parseInt(scaleY.getDataY().getData());
        }
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

    public interface OnTurnCircleClickListener {
        void onTurnCircleClick(int position, int x, int y);
    }

}
