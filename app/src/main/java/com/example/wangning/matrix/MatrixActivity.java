package com.example.wangning.matrix;

import android.app.Activity;
import android.os.Bundle;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/4/24.
 */
public class MatrixActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        MatrixTransformView transformView = (MatrixTransformView) findViewById(R.id.matrixTransformView);
        transformView.setDrawable(R.mipmap.ic_launcher);
        transformView.postMatrixSkew(0,200,100,100);
    }
}
