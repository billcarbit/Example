package com.example.wangning.dialog;

import android.app.Activity;
import android.content.DialogInterface;
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
    private static final String TAG = "DialogActivity";
    ProcessDialog processDialog;
    SlideDownDialog slideDownDialog;
    AddPhotoDialog addPhotoDialog;
    ProgressBarDialog mProgressBarDialog;
    Button btn1, btn2, btn3, btn4,btn_pic;
    ImageDialog imageDialog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_del);
        initView();
        initData();


    }

    void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn_pic = (Button) findViewById(R.id.btn_pic);

    }

    void initData() {
        initProcessDialog();
        initSlideDownDialog();
        initAddPhotoDialog();
        initNormalDialog();
        initImageDialog();
    }

    void initImageDialog() {
        imageDialog = new ImageDialog(this);
        imageDialog.setCancelable(true);
        imageDialog.setCanceledOnTouchOutside(true);
        btn_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    void initNormalDialog() {
        mProgressBarDialog = new ProgressBarDialog(this);
        mProgressBarDialog.setCancelable(true);
        mProgressBarDialog.setCanceledOnTouchOutside(true);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBarDialog.show();
            }
        });
    }

    void initAddPhotoDialog() {
        addPhotoDialog = new AddPhotoDialog(this);
        addPhotoDialog.setCancelable(true);
        addPhotoDialog.setCanceledOnTouchOutside(true);

        addPhotoDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //addPhotoDialog.animationExit();
            }
        });

        addPhotoDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.e(TAG, "onDismiss: ");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhotoDialog.show();
            }

        });
    }

    void initSlideDownDialog() {
        slideDownDialog = new SlideDownDialog(this, 0, 0);
        slideDownDialog.setCancelable(true);
        slideDownDialog.setCanceledOnTouchOutside(true);
        slideDownDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        slideDownDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideDownDialog.show();
            }
        });


    }

    void initProcessDialog() {
        processDialog = new ProcessDialog(this);

        processDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processDialog.show();
                processDialog.setDuration(3000);
                processDialog.setFromPercent(0.0f);
                processDialog.setToPercent(1.0f);
                processDialog.notifyData();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e(TAG, "onBackPressed: ");
    }

}
