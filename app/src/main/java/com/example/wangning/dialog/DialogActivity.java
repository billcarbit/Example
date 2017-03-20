package com.example.wangning.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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

    private DeleteDialog deleteDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_del);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog.showDialog("您是否要解绑该银行卡", new DeleteDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        Log.d("aa", "onConfirm: ");
                    }
                });
            }
        });
        deleteDialog = new DeleteDialog(this);


    }
}
