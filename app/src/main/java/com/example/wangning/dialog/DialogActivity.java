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


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseConfirmDialog dialog = new BaseConfirmDialog(DialogActivity.this);
                dialog.setContent("AAAAAAAA");
                dialog.setConfirmText("拨打");
                dialog.setOnConfirmListener(new BaseConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {

                    }
                });
                dialog.show();

            }
        });


    }
}
