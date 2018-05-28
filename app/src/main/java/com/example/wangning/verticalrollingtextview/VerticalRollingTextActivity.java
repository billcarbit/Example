package com.example.wangning.verticalrollingtextview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;
import com.xiaosu.DataSetAdapter;
import com.xiaosu.VerticalRollingTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */
public class VerticalRollingTextActivity extends Activity
        implements View.OnClickListener {
    VerticalRollingTextView verticalRollingView;
    Button btn;
    final List<String> textArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_rolling_text);
        verticalRollingView = (VerticalRollingTextView) findViewById(R.id.rollingView);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        for (int i = 0; i < 3; i++) {
            textArray.add("A" + i);
        }
        verticalRollingView.setDataSetAdapter(new DataSetAdapter<String>(textArray) {
            @Override
            protected String text(String s) {
                return s;
            }
        });

        // 开始滚动
        verticalRollingView.run();
        // 停止滚动
        //verticalRollingView.stop();
        // 设置点击监听
        verticalRollingView.setOnItemClickListener(new VerticalRollingTextView.OnItemClickListener() {
            @Override
            public void onItemClick(VerticalRollingTextView view, int index) {
                // your code
                Log.e("AA", "onItemClick: " + textArray.get(index));
            }
        });


    }

    public void btnOnClick() {
        textArray.remove(0);
        verticalRollingView.setDataSetAdapter(new DataSetAdapter<String>(textArray) {
            @Override
            protected String text(String s) {
                return s;
            }
        });
        verticalRollingView.run();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                btnOnClick();
                break;
            default:
                break;
        }
    }
}
