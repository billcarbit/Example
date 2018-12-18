package com.example.wangning.bezier;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wangning.R;

public class CanvasTestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_test);
        CanvasTestView canvasTestView = findViewById(R.id.canvasTestView);
        canvasTestView.setProgress(50);
    }
}
