package com.example.wangning.canvas.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.example.wangning.R;

/**
 * 贝赛尔曲线球形进度
 */
public class BallCurveView extends ViewGroup {

    private Context mContext;

    public BallCurveView(Context context) {
        super(context);
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
        Paint areaPaint = new Paint();
        int orange_f76b1c = getResources().getColor(R.color.orange_f76b1c);
        int white_fdf6df = getResources().getColor(R.color.white_fdf6df);
        LinearGradient gradient = new LinearGradient(0, 200, 0, getHeight(), orange_f76b1c, white_fdf6df, Shader.TileMode.CLAMP);
        areaPaint.setShader(gradient);
        areaPaint.setAntiAlias(true);
        areaPaint.setStrokeWidth(10);
        areaPaint.setStyle(Paint.Style.STROKE);


        Path path = new Path();
        path.moveTo(100, 500);//将画笔移动至X
        path.quadTo(100,0,700,500);
        //path.cubicTo(200,0,300,90,500,100);
        canvas.drawPath(path, areaPaint);

    }
}
