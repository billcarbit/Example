package com.example.wangning.popwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-02-27
 * @since JDK 1.8
 */
public class PopupWindowActivity extends Activity {

    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popwindow);

        LastRowCenterGridView lastRowCenterGridView = (LastRowCenterGridView) findViewById(R.id.lastRowCenterGridView);

        final List<String> datas = new ArrayList<String>();
        for (int i = 0; i <11; i++) {
            datas.add("A" + i);
        }
        lastRowCenterGridView.initData(datas,4);
        lastRowCenterGridView.setHorizontalSpacing(50);
        lastRowCenterGridView.setVerticalSpacing(100);

        lastRowCenterGridView.setOnItemClickListener(new LastRowCenterGridView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(PopupWindowActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
                new SignInItemPopupWindow(PopupWindowActivity.this,datas).show(view);
            }
        });


        mButton = (Button) findViewById(R.id.btn_popupwin);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new MyPopupWindow(PopupWindowActivity.this).show(v);
            }
        });
    }
}
