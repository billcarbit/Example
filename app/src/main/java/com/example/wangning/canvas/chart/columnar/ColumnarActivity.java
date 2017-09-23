package com.example.wangning.canvas.chart.columnar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.PopupWindow;

import com.example.wangning.R;

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

    private TurnPointPopWindow mTurnPointPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_columnar);
        mTurnPointPopWindow = new TurnPointPopWindow(this);
        final ColumnarChartView ccv = (ColumnarChartView) findViewById(R.id.v_columnar);
        final List<DataY> dataYList = new ArrayList<DataY>();
        for (int i = 0; i < 8; i++) {
            DataY dataY = new DataY();
            dataY.setValue(String.valueOf(i * 10000));
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


        List<PathLine> pathLineList = new ArrayList<PathLine>();
        PathLine pathLine = new PathLine();
        pathLine.setColor(R.color.orange_ee775b);
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        for (int j = 0; j < 10; j++) {
            Coordinate coordinate = new Coordinate();
            switch (j) {
                case 0:
                    coordinate.setValY(10000);
                    break;
                case 1:
                    coordinate.setValY(20000);
                    break;
                case 2:
                    coordinate.setValY(2000);
                    break;
                case 3:
                    coordinate.setValY(30000);
                    break;
                case 4:
                    coordinate.setValY(15000);
                    break;
                default:
                    coordinate.setValY(25000);
                    break;
            }
            coordinateList.add(coordinate);
            pathLine.setCoordinateList(coordinateList);
        }
        pathLineList.add(pathLine);
        ccv.setLinePathList(pathLineList);
        ccv.setOnTurnCircleClickListener(new ColumnarChartView.OnTurnCircleClickListener() {
            @Override
            public void onTurnCircleClick(int position, int x, int y) {
                Coordinate coordinate = ccv.getLinePathList().get(0).getCoordinateList().get(position);

                mTurnPointPopWindow.setContent(String.valueOf((int) coordinate.getValY()));

                int popHeight = mTurnPointPopWindow.getHeight();
                int popWidth = mTurnPointPopWindow.getWidth();
                int[] location = new int[2];
                int marginTurnPointTop = 20;
                ccv.getLocationOnScreen(location);
                int anchorY = location[1] + y - popHeight - marginTurnPointTop;
                int anchorX = location[0] + x - popWidth / 2;
                Log.e("onTurnCircleClick", "anchorY= " + anchorY + ",anchorX=" + anchorX +
                        ",popWidth=" + popWidth
                        + ",popHeight=" + popHeight);
                mTurnPointPopWindow.showAtLocation(ccv, anchorX, anchorY);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTurnPointPopWindow.dismiss();
    }
}
