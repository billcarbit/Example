package com.example.wangning.switchbtn;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/8/15.
 */
public class SwitchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_switch);
        final Switch aSwitch = (Switch) findViewById(R.id.s_v);
        aSwitch.setTextOff("隐藏");
        aSwitch.setTextOn("公开");
        aSwitch.setChecked(false);
        aSwitch.setSwitchTextAppearance(SwitchActivity.this,R.style.s_false);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("AA", "onCheckedChanged: b=" + b);
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(SwitchActivity.this,R.style.s_true);
                }else {
                    aSwitch.setSwitchTextAppearance(SwitchActivity.this,R.style.s_false);
                }
            }
        });


    }
}
