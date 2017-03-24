package com.example.wangning.edittext;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

/**
 * 数字空格输入框
 *
 * @author wangning
 * @version 1.0 2017-03-21
 * @since JDK 1.8
 */
public class NumSpaceEditText extends EditText {
    private static final String TAG = "NumSpaceEditText";
    private final Context mContext;
    private int mNum = 4;
    private int mMax = 22;
    private int mSpace;

    public NumSpaceEditText(Context context) {
        this(context, null);
    }

    public NumSpaceEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumSpaceEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setFocusable(true);
        setEnabled(true);
        setFocusableInTouchMode(true);
    }



    @Override
    public void setInputType(int type) {
        super.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    }

    /**
     * 设置多少个num一个space，最长输入max个数字
     *
     * @param num
     * @param space
     * @param max
     */
    public void setNumSpaceMax(int num, int space, int max) {
        mNum = num;
        mMax = max;
        mSpace = space;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (mNum <= 0) {
            return;
        }
        //如果长度大于4位的话,我才去格式化
        String inputString = text.toString();
        if (inputString.length() > mNum) {
            /**
             * 最多只能输入mMax位
             */
            if (inputString.length() <= mMax + mMax / mNum) {
                boolean isMatch = inputString.matches("^(\\d{4}\\s){1,5}(\\d{1,4}){1}$");
                //如果符合规定的正则表达式的话,就不去格式化文本
                if (!isMatch) {
                    formatCardNum(text);
                }
            } else {
                //超过之后就不能输入
                //setFocusable(false);
                //Log.e(TAG, "onTextChanged: 超过之后就不能输入");
            }
        }
    }

    private void formatCardNum(CharSequence text) {
        //先去掉所有的空格,因为有可能用户在输入的过程中有空格
        String originCardNum = text.toString().trim().replace(" ", "");
        int len = originCardNum.length();
        int courPos;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(originCardNum.charAt(i));
            if (i == mNum - 1 ||//3
                    i == mNum - 1 + mNum ||//7
                    i == mNum - 1 + mNum * 2 ||//11
                    i == mNum - 1 + mNum * 3 ||//15
                    i == mNum - 1 + mNum * 4) {//19
                //判断是不是最后一位,最后一位不加空格" "
                if (i != len - 1)
                    builder.append(" ");
            }
        }
        courPos = builder.length();
        setText(builder.toString());
        if (courPos > mMax + mMax/mNum) {//防止粘贴进来的数字
            Log.e(TAG, "formatCardNum1: courPos="+courPos );
            setSelection(mMax + mMax/mNum);
        }else{
            Log.e(TAG, "formatCardNum2: courPos="+courPos );
            setSelection(courPos);
        }
    }
}
