package com.example.wangning.canvas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.wangning.R;
import com.example.wangning.canvas.chart.line.Coordinate;
import com.example.wangning.canvas.chart.line.DataX;
import com.example.wangning.canvas.chart.line.DataY;
import com.example.wangning.canvas.chart.line.LineAreaChartView;
import com.example.wangning.canvas.chart.line.PathLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-16
 * @since JDK 1.8
 */
public class CanvasActivity extends Activity {


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
            dataY.setData(String.valueOf((i + 1) * 500));
            dataYList.add(dataY);
        }
        lacv.setYData(dataYList);
        List<PathLine> linePathList = new ArrayList<PathLine>();
        for (int i = 0; i < 4; i++) {
            PathLine linePath = new PathLine();
            if(i==0){
                linePath.setColor(R.color.cyan_8ab8be);
            }
            if(i==1){
                linePath.setColor(R.color.green_add3c0);
            }
            if(i==2){
                linePath.setColor(R.color.red_dba38f);
            }
            if(i==3){
                linePath.setColor(R.color.red_cf6e6b);
            }
            linePath.setStrokeWidth(1);
            List<Coordinate> coordinateList = new ArrayList<Coordinate>();
            for (int j = 0; j < 7; j++) {
                Coordinate coordinate = new Coordinate();
                coordinate.setX(100);
                //new Random().nextInt(2500)
                if(i==0){
                    coordinate.setY(1700);
                }
                if(i==1){
                    coordinate.setY(1300);
                }
                if(i==2){
                    coordinate.setY(700);
                }
                if(i==3){
                    coordinate.setY(200);
                }
                coordinateList.add(coordinate);

            }
            linePath.setCoordinateList(coordinateList);
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
