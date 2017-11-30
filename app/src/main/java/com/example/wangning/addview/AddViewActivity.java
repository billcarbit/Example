package com.example.wangning.addview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-11-30
 * @since JDK 1.8
 */
public class AddViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);
        SalesItemView salesItemView = (SalesItemView)findViewById(R.id.salesItemView);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
