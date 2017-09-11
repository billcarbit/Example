package com.example.wangning.swipemenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-06
 * @since JDK 1.8
 */
public class SwipeMenuActivity extends Activity {
    private static final String TAG = "SwipeMenuActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipemenu);
        final SwipeMenuLayout sml =  (SwipeMenuLayout)findViewById(R.id.sml);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "btn _ onClick: " );
            }
        });
        sml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "sml_ onClick: " );
            }
        });

        findViewById(R.id.main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "main_ onClick: " );
            }
        });



    }
}
