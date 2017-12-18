package com.example.wangning;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private final Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            // TODO Auto-generated method stub
            return false;
        }
    });

    private final static Handler mHandler2 =new  MyHandler();

    private final Thread thread = new Thread(){
        @Override
        public void run() {
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll_coo = (LinearLayout) findViewById(R.id.ll_coo);
        View popLayout = LayoutInflater.from(this).inflate(R.layout.popup_turn_point, ll_coo);
        View llPop = popLayout.findViewById(R.id.ll_pop);
        Log.e(TAG, "onCreate: llPop=" + llPop);
        mHandler.sendEmptyMessageDelayed(1,1);
        mHandler2.sendEmptyMessageDelayed(1,1);
        thread.start();

    }

   private static class MyHandler extends  Handler{
        @Override
        public void handleMessage(Message msg) {
        }
    }

}
