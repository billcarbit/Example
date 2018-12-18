package com.example.wangning.bezier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.wangning.R;

public class CanvasTestView extends View {

    private int topColor = R.color.blue_44f0ff;
    private int bottomColor = R.color.blue_5c6fd3;
    private int outerRingColor = R.color.white_eee;

    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 路径的集合
     */
    private Path mPath;

    /**
     * 控件宽度
     */
    private int mContentWidth;

    /**
     * 控件高度
     */
    private int mContentHeight;

    /**
     * 移动的步长
     */
    private int mStep = 0;

    /**
     * 一个波纹的宽度
     */
    private int mWaveLength = 0;

    /**
     * 一起有多少个波纹
     */
    private int mWaveCount = 4;

    /**
     * 进度
     */
    private int mProgress = 1;

    /**
     * 进度的坐标
     */
    private int mProgressY;

    /**
     * 圆的半径
     */
    private int mRadius;

    /**
     * 波动的复读
     */
    private int mWaveRange = 20;

    public CanvasTestView(Context context) {
        super(context);
    }

    public CanvasTestView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public CanvasTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mContentWidth = w;
        mContentHeight = h;
        //取得高宽小的那一个的一半作为一个弧度的宽度
        mWaveLength = Math.min(w, h) / 2;
        //同时这个弧度的长度也是圆的半径
        mRadius = mWaveLength;
        //开始动画
        run();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //计算幅度 >80  <20的时候   就固定为40的幅度
/*        if (mProgress < 50) {
            if (mProgress < 20)
                mWaveRange = 40;
            else
                mWaveRange = mProgress * 2;
        } else {
            if (100 - mProgress > 20)
                mWaveRange = (100 - mProgress) * 2;
            else
                mWaveRange = 40;
        }*/
        //根据进度计算Y的坐标
        mProgressY = mContentHeight / 2 + mRadius - (int) ((mRadius * 2) / (100 / (float) mProgress));

        //实心
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //笔触
        mPaint.setStrokeWidth(1);
        //画笔颜色
        mPaint.setColor(Color.BLUE);

        int beginColor = getResources().getColor(topColor);
        int endColor = getResources().getColor(bottomColor);

        LinearGradient gradient = new LinearGradient(0, mProgressY, 0, mContentHeight, beginColor, endColor, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);

        //重置并绘制一个圆
        mPath.reset();
        canvas.clipPath(mPath); // makes the clip empty
        mPath.addCircle(mContentWidth / 2, mContentHeight / 2, mRadius, Path.Direction.CCW);
        canvas.clipPath(mPath, Region.Op.REPLACE);

        //重置队列
        mPath.reset();
        mPath.moveTo(0 - mStep, mProgressY);

        //确定各个点的位置
        for (int i = 1; i <= mWaveCount; i++) {
            if (i % 2 != 0)
                mPath.quadTo(mWaveLength * i - (mWaveLength / 2) - mStep, mProgressY - mWaveRange, mWaveLength * i - mStep, mProgressY);
            else
                mPath.quadTo(mWaveLength * i - (mWaveLength / 2) - mStep, mProgressY + mWaveRange, mWaveLength * i - mStep, mProgressY);
        }
        mPath.lineTo(mWaveLength * mWaveCount, mContentHeight);
        mPath.lineTo(0, mContentHeight);
        //闭合得到一个封闭的区域
        mPath.close();
        //绘制出来
        canvas.drawPath(mPath, mPaint);
        //canvas.restore();

        Paint ringPaint = new Paint();
        //绘制一个笔触为10的边框
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setStrokeWidth(30);
        ringPaint.setAntiAlias(true);
        ringPaint.setColor(getResources().getColor(outerRingColor));
        canvas.drawCircle(mContentWidth / 2, mContentHeight / 2, mWaveLength, ringPaint);
    }

    /**
     * 一秒移动一个周期不停去改变步长的值
     */
    protected void run() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mWaveLength * 2);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mStep = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    /**
     * 设置进度
     *
     * @param progress 进度值
     *                 给定100毫秒作为进度的过度
     */
    public void setProgress(int progress) {
        if (progress > 100) {
            progress = 100;
        }
        if (progress < 0) {
            progress = 0;
        }
        ValueAnimator animator = ValueAnimator.ofInt(this.mProgress, progress);
        animator.setDuration(100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

}
