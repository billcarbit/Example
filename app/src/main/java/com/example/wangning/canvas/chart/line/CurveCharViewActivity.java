package com.example.wangning.canvas.chart.line;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;

import com.example.wangning.R;
import com.example.wangning.canvas.chart.columnar.Coordinate;
import com.example.wangning.popwindow.LineAreaPopWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-18
 * @since JDK 1.8
 */
public class CurveCharViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curve_char_canvas);
        final CurveCharView lacv = (CurveCharView) findViewById(R.id.ccv);

        final List<DataX> dataXList = new ArrayList<DataX>();
        final List<DataY> dataYList = new ArrayList<DataY>();
        for (int i = 0; i < 7; i++) {
            DataX dataX = new DataX();
            dataX.setData("周" + (i + 1));
            dataXList.add(dataX);
        }
        lacv.setXData(dataXList);
        for (int i = 0; i < 5; i++) {
            DataY dataY = new DataY();
            dataY.setData(String.valueOf((i + 1) * 100));
            dataYList.add(dataY);
        }
        lacv.setYData(dataYList);
        List<PathLine> linePathList = new ArrayList<PathLine>();
        PathLine linePath = new PathLine();
        linePath.setColor(R.color.orange);
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        List<Integer> dataList = new ArrayList<>();
        for (int k = 0; k < 10; k++) {

            switch (k) {
                case 0:
                    dataList.add(350);
                    break;
                case 1:
                    dataList.add(200);
                    break;
                case 2:
                    dataList.add(450);
                    break;
                case 3:
                    dataList.add(400);
                    break;
                default:
                    dataList.add(100);
                    break;
            }
        }
        for (Integer order120Item : dataList) {
            Coordinate coordinate = new Coordinate();
            coordinate.setValY(order120Item);
            coordinateList.add(coordinate);
        }
        linePath.setCoordinateList(coordinateList);
        linePathList.add(linePath);
        lacv.setLinePathList(linePathList);
        lacv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.invalidate();
            }
        });

    }
}
