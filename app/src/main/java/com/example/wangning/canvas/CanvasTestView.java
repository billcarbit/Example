package com.example.wangning.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.wangning.R;
import com.example.wangning.utils.AppUtil;

public class CanvasTestView extends View {

    public CanvasTestView(Context context) {
        this(context, null);
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int blue0063f3 = getResources().getColor(R.color.blue0063f3);
        int white = getResources().getColor(R.color.white);
        LinearGradient gradient = new LinearGradient(0, 400, 400, 400, blue0063f3, white, Shader.TileMode.CLAMP);

        Path path = new Path();
        path.moveTo(0,400);
        path.lineTo(0,1500);
        path.lineTo(400,1500);
        path.lineTo(400,400);
        path.close();

        Paint paint = new Paint();
        paint.setShader(gradient);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //canvas.drawLine(0, 400, 400, 400, paint);
        canvas.drawPath(path,paint);

    }


}
