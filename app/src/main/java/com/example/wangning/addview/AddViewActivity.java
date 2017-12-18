package com.example.wangning.addview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONArray;
import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-11-30
 * @since JDK 1.8
 */
public class AddViewActivity extends Activity {
    private static final String TAG = "AddViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);
        final SalesItemView salesItemView = (SalesItemView) findViewById(R.id.salesItemView);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArray jsonArray = salesItemView.getInputData();
                Log.e(TAG, "onClick: " + jsonArray.toString());
            }
        });

        final ImageView iv = (ImageView) findViewById(R.id.iv);

        iv.setOnClickListener(new View.OnClickListener() {
            boolean check;

            @Override
            public void onClick(View v) {
                iv.setSelected(!iv.isSelected());

            }
        });
    }


}
