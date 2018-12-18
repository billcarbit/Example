package com.example.wangning.bezier;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wangning.R;

public class WaveProgressActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_progress);
        WaveProgressView waveProgressView = findViewById(R.id.waveProgressView);
        waveProgressView.setProgress(70);
    }
}
