package com.example.wangning.canvas.chart.line;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-08-21
 * @since JDK 1.8
 */
public class LineAreaChartActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        LineAreaChartView lacv = (LineAreaChartView) findViewById(R.id.lacv);

        List<DataX> dataXList = new ArrayList<DataX>();
        List<DataY> dataYList = new ArrayList<DataY>();
        for (int i = 0; i < 7; i++) {
            DataX dataX = new DataX();
            dataX.setData("周" + (i + 1));
            dataXList.add(dataX);
        }
        lacv.setXData(dataXList);

        for (int i = 0; i < 5; i++) {
            DataY dataY = new DataY();
            dataY.setData(String.valueOf((i + 1) * 1000));
            dataYList.add(dataY);
        }
        lacv.setYData(dataYList);
        List<PathLine> linePathList = new ArrayList<PathLine>();
        for (int i = 0; i < 4; i++) {
            PathLine linePath = new PathLine();
            if (i == 0) {
                linePath.setColor(R.color.cyan_8ab8be);
                List<Coordinate> coordinateList = new ArrayList<Coordinate>();
                List<Integer> dataList = new ArrayList<>();
                for (int k = 0; k < 10; k++) {
                    dataList.add(1000 + k * 10);
                }
                for (Integer order120Item : dataList) {
                    Coordinate coordinate = new Coordinate();
                    coordinate.setY(order120Item);
                    coordinateList.add(coordinate);
                }
                linePath.setCoordinateList(coordinateList);
            }
            if (i == 1) {
                linePath.setColor(R.color.green_add3c0);
                List<Coordinate> coordinateList = new ArrayList<Coordinate>();
                List<Integer> dataList = new ArrayList<>();
                for (int k = 0; k < 10; k++) {
                    dataList.add(800 + k * 10);
                }
                for (Integer order120Item : dataList) {
                    Coordinate coordinate = new Coordinate();
                    coordinate.setY(order120Item);
                    coordinateList.add(coordinate);
                }
                linePath.setCoordinateList(coordinateList);
            }
            if (i == 2) {
                linePath.setColor(R.color.red_dba38f);
                List<Coordinate> coordinateList = new ArrayList<Coordinate>();
                List<Integer> dataList = new ArrayList<>();
                for (int k = 0; k < 10; k++) {
                    dataList.add(600 + k * 10);
                }
                for (Integer order120Item : dataList) {
                    Coordinate coordinate = new Coordinate();
                    coordinate.setY(order120Item);
                    coordinateList.add(coordinate);
                }
                linePath.setCoordinateList(coordinateList);
            }
            if (i == 3) {
                linePath.setColor(R.color.red_cf6e6b);
                List<Coordinate> coordinateList = new ArrayList<Coordinate>();
                List<Integer> dataList = new ArrayList<>();
                for (int k = 0; k < 5; k++) {
                    dataList.add(200 + k * 10);
                }
                for (Integer order120Item : dataList) {
                    Coordinate coordinate = new Coordinate();
                    coordinate.setY(order120Item);
                    coordinateList.add(coordinate);
                }
                linePath.setCoordinateList(coordinateList);
            }
            linePathList.add(linePath);
        }
        lacv.setLinePathList(linePathList);
        lacv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.invalidate();
            }
        });
    }
}
