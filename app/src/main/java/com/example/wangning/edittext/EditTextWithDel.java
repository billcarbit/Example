package com.example.wangning.edittext;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.wangning.R;


/**
 * @author sunday
 *         2013-12-04
 */
public class EditTextWithDel extends EditText {
    private final static String TAG = "EditTextWithDel";
    private Drawable imgInable;
    private Context mContext;

    public EditTextWithDel(Context context) {
        super(context);
        mContext = context.getApplicationContext();
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context.getApplicationContext();
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context.getApplicationContext();
        init();
    }

    private void init() {
        imgInable = mContext.getResources().getDrawable(R.drawable.delete_gray);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });
        setDrawable();
    }

    //设置删除图片
    private void setDrawable() {
        if (length() < 1) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgInable, null);
        }
    }

    int Dx = 0;
    int Dy = 0;
    Rect rect1 = new Rect();

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Dx = (int) event.getX();//(int) event.getRawX();
            Dy = (int) event.getY();//(int) event.getRawY();
            Log.e(TAG, "ACTION_DOWN: Dx=" + Dx + ",Dy=" + Dy);
            //getGlobalVisibleRect(rect1);
            getLocalVisibleRect(rect1);
            rect1.left = rect1.right - imgInable.getIntrinsicWidth();
        }

        if (imgInable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getX();//(int) event.getRawX();
            int eventY = (int) event.getY();//(int) event.getRawY();
            Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            //getGlobalVisibleRect(rect);
            getLocalVisibleRect(rect);
            Log.e(TAG, "getGlobalVisibleRect,rect.left = " + rect.left + ",rect.right=" + rect.right + "," +
                    "imgInable.getIntrinsicWidth()=" + imgInable.getIntrinsicWidth()
                    + ",rect.top=" + rect.top + "，rect.bottom=" + rect.bottom);
            rect.left = rect.right - imgInable.getIntrinsicWidth();

            Log.e(TAG, "ACTION_UP: rect.contains(eventX, eventY)=" + rect.contains(eventX, eventY)
                    + ",rect1.contains(Dx, Dy)=" + rect1.contains(Dx, Dy));
            if (rect.contains(eventX, eventY) && rect1.contains(Dx, Dy))
                setText("");
        }
        return super.onTouchEvent(event);
    }

}