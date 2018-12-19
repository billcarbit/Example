package com.example.wangning.canvas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import com.example.wangning.R;
import com.example.wangning.canvas.chart.Algorithm;
import com.example.wangning.canvas.chart.columnar.AColumnar;
import com.example.wangning.canvas.chart.columnar.Coordinate;
import com.example.wangning.canvas.chart.columnar.DataX;
import com.example.wangning.canvas.chart.columnar.DataY;
import com.example.wangning.canvas.chart.columnar.PathLine;

import java.util.ArrayList;
import java.util.List;


/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-16
 * @since JDK 1.8
 */
public class CanvasLabActivity extends Activity {

    Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_lab);

       PillarView ccv = findViewById(R.id.ccv);
        final List<DataY> dataYList = new ArrayList<DataY>();
        for (int i = 0; i < 9; i++) {
            DataY dataY = new DataY();
            dataY.setValue(String.valueOf(i * 100));
            dataYList.add(dataY);
        }
        ccv.setYData(dataYList);


        List<AColumnar> aColumnarList = new ArrayList<AColumnar>();
        for (int i = 0; i < 5; i++) {
            AColumnar columnar = new AColumnar();
            columnar.setData((i + 1) * 150);
            aColumnarList.add(columnar);
        }
        ccv.setColumnarList(aColumnarList);

        final List<DataX> dataXList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DataX dataX = new DataX();
            dataX.setValue("AAAADSFASFASFAAA" + i);
            dataXList.add(dataX);
        }
        ccv.setXData(dataXList);

        ccv.invalidate();
    }


}
