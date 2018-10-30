package com.example.wangning.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;
import com.example.wangning.glide.GlideActivity;

public class MainActivity extends AppCompatActivity {
    View v_indicator;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.e("AAA", "onWindowFocusChanged: hasFocus=" + hasFocus + ",sas=" + v_indicator.getWidth());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll_tabs = findViewById(R.id.ll_tabs);
        v_indicator = findViewById(R.id.v_indicator);
        final TextView firstChild = (TextView) ll_tabs.getChildAt(0);
        ViewTreeObserver vto2 = firstChild.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                firstChild.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int childWidth = firstChild.getWidth();
                LinearLayout.LayoutParams sa = (LinearLayout.LayoutParams) v_indicator.getLayoutParams();
                sa.leftMargin = firstChild.getLeft() + childWidth / 2 - v_indicator.getWidth() / 2;
                v_indicator.setLayoutParams(sa);
            }
        });


        for (int i = 0; i < ll_tabs.getChildCount(); i++) {
            final TextView child = (TextView) ll_tabs.getChildAt(i);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    child.setTextSize(35);
                    child.post(new Runnable() {
                        @Override
                        public void run() {
                              LinearLayout.LayoutParams sa = (LinearLayout.LayoutParams) v_indicator.getLayoutParams();
                            int childWidth = child.getWidth();
                            sa.leftMargin = child.getLeft() + childWidth / 2 - v_indicator.getWidth() / 2;
                            v_indicator.setLayoutParams(sa);
                        }
                    });
                }
            });
        }

    }
}
