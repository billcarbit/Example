package com.example.wangning.textswitcher;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.wangning.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-15
 * @since JDK 1.8
 */
public class TextSwitcherActivity extends Activity implements ViewSwitcher.ViewFactory{
    /** Called when the activity is first created. */
    TextSwitcher switcher;
    Handler handler;
    String [] resources={
            "身是菩提树，",
            "心如明镜台，",
            "时时勤拂拭，",
            "勿使惹尘埃。"
    };
    private Handler mHandler = new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    id = next(); //更新Id值
                    updateText();  //更新TextSwitcherd显示内容;
                    break;
            }
        };
    };
    int id= 0; //resources 数组的Id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_switcher);
        init();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTask(), 1, 3000);//每3秒更新
    }
    private void init(){
        switcher = (TextSwitcher) findViewById(R.id.ts);
        switcher.setFactory(this);
        switcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
    }
    private int next(){

        int flag = id+1;
        if(flag>resources.length-1){
            flag=flag-resources.length;
        }
        return flag;
    }
    private void updateText(){
        switcher.setText(resources[id]);
    }
    @Override
    public View makeView() {
        // TODO Auto-generated method stub
        TextView tv =new TextView(this);
        tv.setText(resources[id]);
        return tv;
    }
    private class MyTask extends TimerTask {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);

        }
    }
}
