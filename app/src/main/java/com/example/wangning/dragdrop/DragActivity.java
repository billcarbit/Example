package com.example.wangning.dragdrop;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/9/6.
 */
public class DragActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_view);
        final DragView dragView = (DragView) findViewById(R.id.dragView);
        dragView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("dragView", "onClick: dragView.isDrag()=" + dragView.isDrag());
            }
        });
    }
}
