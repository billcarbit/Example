package com.example.wangning.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

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


        Path path = new Path();
        path.lineTo(300,600);



        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);
        paint.setTextSize(AppUtil.dp2px(getContext(), 16));
        canvas.drawLine(0, 400, 400, 400, paint);

       // canvas.rotate(45, 0, 400);

        canvas.drawText("Hello", 0, 400, paint);


        canvas.drawTextOnPath("AAA",path,0,0,paint);

    }


}
