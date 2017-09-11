package com.example.wangning.canvas.chart.columnar;

import android.app.Activity;
import android.os.Bundle;

import com.example.wangning.R;
import com.example.wangning.canvas.chart.line.*;
import com.example.wangning.canvas.chart.line.DataY;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-11
 * @since JDK 1.8
 */
public class ColumnarActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_columnar);
        ColumnarChartView ccv = (ColumnarChartView) findViewById(R.id.v_columnar);
        final List<DataY> dataYList = new ArrayList<DataY>();
        for (int i = 0; i < 6; i++) {
            DataY dataY = new DataY();
            dataY.setData(String.valueOf(i * 1000));
            dataYList.add(dataY);
        }
        ccv.setYData(dataYList);

        List<AColumnar> aColumnarList = new ArrayList<AColumnar>();
        for (int i = 0; i < 10; i++) {
            AColumnar columnar = new AColumnar();
            columnar.setData((i + 1) * 100);
            aColumnarList.add(columnar);
        }
        ccv.setColumnarList(aColumnarList);


    }
}
