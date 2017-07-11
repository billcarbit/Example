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


       /* final BaseConfirmDialog dialog = new BaseConfirmDialog(this);
        dialog.setContent("AADDDD")
                .setConfirmText("我知道了")
                .setBtnNum(2)
                .setOnConfirmListener(new BaseConfirmDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        dialog.dismiss();
                    }
                });
        dialog.show();*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });


    }

    private void showAlertDialog() {
        new BaseAlertDialog(this)
                .setMessage("是否确定将所选3个客资分配给杨四郎？")
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .show();
    }
}
