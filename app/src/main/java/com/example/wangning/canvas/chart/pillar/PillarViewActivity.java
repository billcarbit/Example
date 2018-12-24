package com.example.wangning.canvas.chart.pillar;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;

import com.example.wangning.R;
import com.example.wangning.canvas.chart.columnar.AColumnar;
import com.example.wangning.canvas.chart.columnar.DataX;
import com.example.wangning.canvas.chart.columnar.DataY;

import java.util.ArrayList;
import java.util.List;


/**
 * 简单的柱状报表
 *
 * @author wangning
 * @version 1.0 2017-03-16
 * @since JDK 1.8
 */
public class PillarViewActivity extends Activity {

    Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_pillar);

        LayerPillarView ccv = findViewById(R.id.ccv);
        final List<DataY> dataYList = new ArrayList<DataY>();
        for (int i = 0; i < 9; i++) {
            DataY dataY = new DataY();
            dataY.setValue(String.valueOf(i * 100));
            dataYList.add(dataY);
        }
        ccv.setYData(dataYList);

        List<AColumnar> aColumnarList = new ArrayList<AColumnar>();
        for (int i = 0; i < 8; i++) {
            AColumnar columnar = new AColumnar();
            columnar.setData((i + 1) * 150);
            columnar.setColor(R.color.blue_a4d9f9);
            columnar.setTopTextColor(R.color.blue_1382ff);
            aColumnarList.add(columnar);
        }
        ccv.setLowerColumnarList(aColumnarList);

        List<AColumnar> bColumnarList = new ArrayList<AColumnar>();
        for (int i = 0; i < 8; i++) {
            AColumnar columnar = new AColumnar();
            columnar.setData((i + 1) * 80);
            //columnar.setColor(R.color.blue_285ac2);
           // columnar.setTopTextColor(R.color.orange);
            bColumnarList.add(columnar);
        }
        ccv.setUpperColumnarList(bColumnarList);

        final List<DataX> dataXList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            DataX dataX = new DataX();
            dataX.setValue("ABC" + i);
            dataXList.add(dataX);
        }
        ccv.setXData(dataXList);
        ccv.setSpacing(20);
        ccv.invalidate();
    }


}
