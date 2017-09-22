package com.example.wangning.canvas.chart.line;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;

import com.example.wangning.R;
import com.example.wangning.canvas.chart.columnar.ColumnarChartView;
import com.example.wangning.canvas.chart.columnar.Coordinate;
import com.example.wangning.canvas.chart.columnar.TurnPointPopWindow;
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
    private TurnPointPopWindow mTurnPointPopWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curve_char_canvas);
        final CurveCharView lacv = (CurveCharView) findViewById(R.id.ccv);
        mTurnPointPopWindow = new TurnPointPopWindow(this);
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
            dataY.setData(String.valueOf((i + 1) * 123456789));
            dataYList.add(dataY);
        }
        lacv.setYData(dataYList);
        List<PathLine> linePathList = new ArrayList<PathLine>();
        PathLine linePath = new PathLine();
        linePath.setColor(R.color.orange);
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        List<Integer> dataList = new ArrayList<>();
        for (int k = 0; k < 8; k++) {

            switch (k) {
                case 0:
                    dataList.add(350456789);
                    break;
                case 1:
                    dataList.add(450496789);
                    break;
                case 2:
                    dataList.add(250496789);
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

        lacv.setOnTurnCircleClickListener(new CurveCharView.OnTurnCircleClickListener() {
            @Override
            public void onTurnCircleClick(int position, int x, int y) {
                Coordinate coordinate = lacv.getLinePathList().get(0).getCoordinateList().get(position);

                mTurnPointPopWindow.setContent(String.valueOf((int) coordinate.getValY()));

                int popHeight = mTurnPointPopWindow.getHeight();
                int popWidth = mTurnPointPopWindow.getWidth();
                int[] location = new int[2];
                int marginTurnPointTop = 20;
                lacv.getLocationOnScreen(location);
                int anchorY = location[1] + y - popHeight - marginTurnPointTop;
                int anchorX = location[0] + x - popWidth / 2;
                Log.e("onTurnCircleClick", "anchorY= " + anchorY + ",anchorX=" + anchorX +
                        ",popWidth=" + popWidth
                        + ",popHeight=" + popHeight);
                mTurnPointPopWindow.showAtLocation(lacv, anchorX, anchorY);
            }
        });
    }
}
