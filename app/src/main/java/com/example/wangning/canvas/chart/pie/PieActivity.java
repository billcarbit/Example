package com.example.wangning.canvas.chart.pie;

import android.app.Activity;
import android.os.Bundle;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-08-18
 * @since JDK 1.8
 */
public class PieActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        PieView pieView = (PieView) findViewById(R.id.v_pie);
        List<LineSign> lineSigns = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            LineSign lineSign = new LineSign();
            lineSign.setLineAboveText("上" + i);
            lineSign.setLineBelowText("下123456" + i);
            if (i == 0) {
                lineSign.setIncreaseColor(R.color.red_f05a4a);
                //lineSign.setIncreaseNum(11);
                //lineSign.setIncrease(true);
                lineSign.setPercent(0.0f);
                lineSign.setLineColor(R.color.red_f05a4a);
            }
            if (i == 1) {
                lineSign.setReduceNum(22);
                lineSign.setIncrease(false);
                lineSign.setPercent(0.0f);
                lineSign.setLineColor(R.color.green_1dac91);
            }
            if (i == 2) {
                lineSign.setIncrease(false);
                lineSign.setReduceNum(33);
                lineSign.setPercent(0.3f);
                lineSign.setLineColor(R.color.blue_00a5e6);
            }
            if (i == 3) {
                lineSign.setIncreaseNum(44);
                lineSign.setIncrease(true);
                lineSign.setPercent(0.3f);
                lineSign.setLineColor(R.color.blue_3474c4);
            }
            if (i == 4) {
                lineSign.setIncreaseNum(55);
                lineSign.setIncrease(true);
                lineSign.setPercent(0.4f);
                lineSign.setLineColor(R.color.yellow_f59800);
            }
            lineSign.setPointRadius(10);
            lineSign.setTurnXLength(30);
            lineSign.setTurnYLength(30);

            lineSigns.add(lineSign);
        }
        pieView.setCoreRadius(pieView.dp2px(33.0f));
        pieView.setArcRadius(pieView.dp2px(85.0f));
        pieView.setData(lineSigns);
    }
}
