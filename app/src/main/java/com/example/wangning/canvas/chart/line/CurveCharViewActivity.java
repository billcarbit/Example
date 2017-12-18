package com.example.wangning.canvas.chart.line;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wangning.R;
import com.example.wangning.canvas.chart.columnar.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * 贝塞尔曲线
 *
 * @author wangning
 * @version 1.0 2017-09-18
 * @since JDK 1.8
 */
public class CurveCharViewActivity extends Activity {
    private RelativeLayout rlPop;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_curve_char_canvas);
        final CurveCharView lacv = (CurveCharView) findViewById(R.id.ccv);
        rlPop = (RelativeLayout) findViewById(R.id.rl_pop);
        final List<DataX> dataXList = new ArrayList<DataX>();
        final List<DataY> dataYList = new ArrayList<DataY>();
        for (int i = 0; i < 6; i++) {
            DataX dataX = new DataX();
            dataX.setData("周" + (i + 1));
            dataXList.add(dataX);
        }
        lacv.setXData(dataXList);
        for (int i = 0; i < 6; i++) {
            DataY dataY = new DataY();
            // dataY.setData(String.valueOf(55 * (i)));
            switch (i) {
                case 0:
                    dataY.setData("55");
                    break;
                case 1:
                    dataY.setData("69");
                    break;
                case 2:
                    dataY.setData("83");
                    break;
                case 3:
                    dataY.setData("97");
                    break;
                case 4:
                    dataY.setData("111");
                    break;
                case 5:
                    dataY.setData("125.3");
                    break;
            }

            dataYList.add(dataY);
        }
        lacv.setYData(dataYList);
        List<PathLine> linePathList = new ArrayList<PathLine>();
        PathLine linePath = new PathLine();
        linePath.setColor(R.color.orange);
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        List<String> dataList = new ArrayList<>();
        for (int k = 0; k < 6; k++) {
            //  dataList.add(50 * k);
            switch (k) {
                case 0:
                    dataList.add("55.10");
                    //dataList.add(160);
                    break;
                case 1:
                    dataList.add("66");
                    //dataList.add(269);
                    break;
                case 2:
                    dataList.add("77.89");
                    //dataList.add(520);
                    break;
                case 3:
                    dataList.add("62.45");
                    //dataList.add(790);
                    break;
                case 4:
                    dataList.add("99.11");
                    //dataList.add(233);
                    break;
                case 5:
                    dataList.add("88.55");
                    //dataList.add(333);
                    break;
            }
        }
        for (String order120Item : dataList) {
            Coordinate coordinate = new Coordinate();
            coordinate.setValY(order120Item.toString());
            coordinateList.add(coordinate);
        }
        linePath.setCoordinateList(coordinateList);
        linePathList.add(linePath);
        lacv.setLinePathList(linePathList);

        lacv.setOnTurnCircleClickListener(new CurveCharView.OnTurnCircleClickListener() {
            @Override
            public void onTurnCircleClick(int position, int x, int y) {
                Coordinate coordinate = lacv.getLinePathList().get(0).getCoordinateList().get(position);
                rlPop.removeAllViews();
                TextView textView = new TextView(context);
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundResource(R.drawable.turn_point_pop_bg);
                textView.setPadding(10, 10, 10, 15);
                textView.setGravity(Gravity.CENTER);
                textView.setText("" + coordinate.getValY());
                RelativeLayout.LayoutParams rlPopLp = (RelativeLayout.LayoutParams) rlPop.getLayoutParams();
                textView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                int rlPopMeasuredHeight = textView.getMeasuredHeight();
                int rlPopMeasuredWidth = textView.getMeasuredWidth();
                rlPopLp.leftMargin = (int) coordinate.getX() - rlPopMeasuredWidth / 2;
                rlPopLp.topMargin = (int) coordinate.getY() - rlPopMeasuredHeight - 30;


                rlPop.setLayoutParams(rlPopLp);
                rlPop.addView(textView);
            }
        });
    }
}
