package com.example.wangning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView    = (TextView)findViewById(R.id.tv);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("textView,onTouchEvent", "ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float y = ev.getY();
                        Log.e("textView,onTouchEvent", "ACTION_MOVE,y=" + y);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.e("textView,onTouchEvent", "ACTION_CANCEL");
                    case MotionEvent.ACTION_UP:
                        Log.e("textView,onTouchEvent", "ACTION_UP");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

/*        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("textView,onClick", "onClick");
            }
        });*/
    }


}
