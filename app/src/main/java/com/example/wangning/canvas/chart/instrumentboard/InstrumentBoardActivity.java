package com.example.wangning.canvas.chart.instrumentboard;

import android.app.Activity;
import android.os.Bundle;

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
        v_instrument_board.setMaxVal(500);
        v_instrument_board.setCurrentVal(490);
        v_instrument_board.invalidate();
    }


}
