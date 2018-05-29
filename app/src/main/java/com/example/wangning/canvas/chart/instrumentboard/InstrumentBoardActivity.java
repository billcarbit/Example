package com.example.wangning.canvas.chart.instrumentboard;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.wangning.R;

/**
 * 仪表盘报表
 * Created by Administrator on 2018/4/2.
 */
public class InstrumentBoardActivity extends Activity {
    private InstrumentBoard v_instrument_board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass_table);
        v_instrument_board = (InstrumentBoard) findViewById(R.id.v_instrument_board);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                v_instrument_board.setMaxVal(500);
                v_instrument_board.setCurrentVal(210);
                v_instrument_board.invalidate();
            }
        },2000);
    }


}
