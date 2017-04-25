package com.example.wangning.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-17
 * @since JDK 1.8
 */
public class DialogActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_del);
        Button btn = (Button) findViewById(R.id.btn);
        final int[] location = new  int[2] ;
        //btn.getLocationInWindow(location); //获取在当前窗口内的绝对坐标
        btn.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PositionDialog dialog = new PositionDialog(DialogActivity.this,location[0],location[1]);
                dialog.show();
            }
        });


    }
}
