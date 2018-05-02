package com.example.wangning.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/4/24.
 */
public class MatrixTransformView extends View {

    private Matrix mMatrix;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap;


    public MatrixTransformView(Context context) {
        super(context);
    }

    public MatrixTransformView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDrawable(int resId) {
        mBitmap = BitmapFactory.decodeResource(getContext().getResources(), resId);
    }

    /*
    * 设置矩阵，并重绘
    */
    public void setMatrixValues(float[] array) {
        if (mMatrix == null) {
            mMatrix = new Matrix();
        }
        mMatrix.reset();
        mMatrix.setValues(array);
        invalidate();
    }

    public void postMatrixScale(float scaleX, float scaleY, float centerX, float centerY) {
        if (mMatrix == null) {
            mMatrix = new Matrix();
        }
        mMatrix.preScale(scaleX, scaleY, centerX, centerY);
        invalidate();
    }

    public void postMatrixSkew(float skewX, float skewY, float centerX, float centerY) {
        if (mMatrix == null) {
            mMatrix = new Matrix();
        }
        mMatrix.postSkew(skewX, skewY, centerX, centerY);
        invalidate();
    }

    public void postMatrixTranslate(float translateX, float translateY) {
        if (mMatrix == null) {
            mMatrix = new Matrix();
        }
        mMatrix.postTranslate(translateX, translateY);
        invalidate();
    }

    public void postMatrixRotate(float degree, float centerX, float centerY) {
        if (mMatrix == null) {
            mMatrix = new Matrix();
        }
        mMatrix.postRotate(degree, centerX, centerY);
        invalidate();
    }

    public void resetMatrix() {
        if (mMatrix != null) {
            mMatrix.reset();
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mMatrix != null) {
            Paint paint = mPaint;
            canvas.drawBitmap(mBitmap, mMatrix, paint);
        }

        super.onDraw(canvas);
    }
}
