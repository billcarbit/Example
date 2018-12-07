package com.example.wangning.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2016-12-29
 * @since JDK 1.8
 */
public class ProgressBarActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        final ProgressBar my_progress = findViewById(R.id.my_progress);
  /*      my_progress.setMax(100);
        my_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_progress.setProgress(my_progress.getProgress() + 10);
            }
        });*/



    }
}
