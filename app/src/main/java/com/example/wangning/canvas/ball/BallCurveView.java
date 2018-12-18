package com.example.wangning.canvas.ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.example.wangning.R;

/**
 * 贝赛尔曲线球形进度
 */
public class BallCurveView extends ViewGroup {

    private Context mContext;
    private final int BALL_RADIUS = 200;
    private final float WATER_HEIGHT_PERCENT = 0.3f;

    public BallCurveView(Context context) {
        this(context, null);
    }


    public BallCurveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        setWillNotDraw(false);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
      /*  Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);

        Path path = new Path();
        path.moveTo(100,300);
        path.quadTo(200,200,300,300);
        path.quadTo(400,400,500,300);*/


/*        Path path = new Path();
        path.moveTo(100,300);
        path.rQuadTo(100,-100,200,0);
        path.rQuadTo(100,100,200,0);
        canvas.drawPath(path,paint);*/

        // canvas.drawPath(path,paint);

        canvas.translate(getWidth() / 2, getHeight() / 2);//移动绘制坐标系原点至控件中央
        drawBall(canvas);
        drawWaterHeight(canvas);
        // drawWave(canvas);


    }

    /**
     * 画个球
     *
     * @param canvas
     */
    private void drawBall(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawCircle(0, 0, BALL_RADIUS, paint);
    }


    /**
     * 画水位
     */
    private void drawWaterHeight(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.orange));
        int center = 0;
        RectF rect = new RectF(center - BALL_RADIUS, center - BALL_RADIUS, center
                + BALL_RADIUS, center + BALL_RADIUS);
        //画扇形,startAngle=0时起点是从3点钟开始
        canvas.drawArc(rect, 0, 180, true, paint);
    }

    /**
     * 画个波
     *
     * @param canvas
     */
    private void drawWave(Canvas canvas) {

        int mItemWaveLength = 400;
        int dx = 10;

        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.blue0063f3));
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        Path path = new Path();


        path.quadTo(200, 200, 300, 300);
        path.quadTo(400, 400, 500, 300);



/*        int originY = 300;
        int halfWaveLen = mItemWaveLength / 2;
        path.moveTo(-mItemWaveLength + dx, originY);
        for (int i = -mItemWaveLength; i <= getWidth() + mItemWaveLength; i += mItemWaveLength) {
            path.rQuadTo(halfWaveLen / 2, -100, halfWaveLen, 0);
            path.rQuadTo(halfWaveLen / 2, 100, halfWaveLen, 0);
        }
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();*/

        canvas.drawPath(path, paint);
    }

/*    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                Log.e("AAA", "onAnimationUpdate: dx=" + dx);
                postInvalidate();
            }
        });
        animator.start();
    }*/


}
