package com.example.wangning.edittext;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-22
 * @since JDK 1.8
 */
public class ValidateFormLayout extends ViewGroup {
    private static final String TAG = "ValidateFormLayout";
    public ValidateFormLayout(Context context) {
        super(context);
    }

    public ValidateFormLayout(Context context, AttributeSet attrs) {
        super(context, attrs);


    }


    public ValidateFormLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int k, int i1, int i2, int i3) {
        Log.e(TAG, "ValidateFormLayout,getChildCount="+getChildCount() );
        for(int i=0; i< getChildCount();i++){
            View childView = getChildAt(i);
            if(childView instanceof LinearLayout){
                Log.e(TAG, "ValidateFormLayout: LinearLayout" );
                for(int j=0;j<((LinearLayout) childView).getChildCount();j++){
                    Log.e(TAG, "ValidateFormLayout: childView" );
                    if(((LinearLayout) childView).getChildAt(j) instanceof EditText){
                        Log.e(TAG, "ValidateFormLayout: EditText" );
                    }
                }
            }
        }
    }

    public void findChildEditText(){
        if(getChildCount() == 0){
            return;
        }
        for(int i=0; i< getChildCount();i++){

        }
    }
}
