package com.example.wangning.edittext;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

/**
 * 银行卡数字空格输入框
 *
 * @author wangning
 * @version 1.0 2017-03-21
 * @since JDK 1.8
 */
public class NumSpaceEditText extends EditText {
    private static final String TAG = "NumSpaceEditText";

    // 几位插入一空格，默认4位
    private int mOffset = 4;

    //最大长度
    private int mMaxLength;

    public NumSpaceEditText(Context context) {
        this(context, null);
    }

    public NumSpaceEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumSpaceEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(true);
        setEnabled(true);
        setFocusableInTouchMode(true);
        addTextChangedListener(new NumSpaceTextWatcher(this));
    }

    /**
     * 设置多少位一个空格
     *
     * @param offset
     */
    public NumSpaceEditText setOffset(int offset) {
        mOffset = offset;
        return this;
    }

    /**
     * 设置可输入最大长度，一定要和android:maxLength保持一致
     * @param max
     * @return
     */
    public NumSpaceEditText setMaxLength(int max) {
        mMaxLength = max;
        return this;
    }


    @Override
    public void setInputType(int type) {
        super.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    }

    class NumSpaceTextWatcher implements TextWatcher {

        // 目标输入框
        private final EditText mDesTxt;

        // 记录目标字符串
        private StringBuffer mBuffer = new StringBuffer();
        // 改变之前的文本长度
        private int mBeforeTextLength;
        // 改变之后的文本长度
        private int mOnTextLength;
        // 改变之前去除空格的文本长度
        private int mBeforeNumTxtLength;
        // 改变之后去除空格的文本长度
        private int mNumTxtLength;
        // 目标 光标的位置
        private int mLocation = 0;
        // 之前 光标的位置(可判断用户是否做删除操作)
        private int mBeforeLocation = 0;
        // 改变前有多少空格
        private int mBeforeSpaceNumber = 0;

        // 是否选中空格覆盖
        private boolean isOverrideSpace;
        // 被覆盖的空格数
        private int mOverrideSpaceNum;
        // 是否是粘贴(此粘贴非彼粘贴)
        private boolean isPaste;
        // 复制的字符数(不包括空格)
        private int mPasteNum;
        // 是否需要进行格式化字符串操作
        private boolean isChanged = false;

        public NumSpaceTextWatcher(@NonNull EditText target, int offset) {
            mDesTxt = target;
            mOffset = offset;
        }

        public NumSpaceTextWatcher(@NonNull EditText target) {
            mDesTxt = target;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            mBeforeTextLength = s.length();
            mBeforeNumTxtLength = s.toString().replaceAll(" ", "").length();
            mBeforeLocation = mDesTxt.getSelectionEnd();

            // 重置mBuffer
            if (mBuffer.length() > 0) {
                mBuffer.delete(0, mBuffer.length());
            }
            // 计算改变前空格的个数
            mBeforeSpaceNumber = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    mBeforeSpaceNumber++;
                }
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mOnTextLength = s.length();
            mNumTxtLength = s.toString().replaceAll(" ", "").length();

            // 判断是否是粘贴,其中粘贴小于offset位的不做判断,并且offset>2判断才有意义
            if (mOffset >= 2 && count >= mOffset) {
                isPaste = true;
                mPasteNum = count;
            } else {
                isPaste = false;
                mPasteNum = 0;
            }

            // 若是经过afterTextChanged方法，则直接return
            if (isChanged) {
                isChanged = false;
                return;
            }

            // 若改变后长度小于等于mOffset - 1，则直接return
            if (mOnTextLength <= mOffset - 1) {
                isChanged = false;
                return;
            }

            // 若改变前后长度一致，并且数字位数相同，则isChanged为false
            // (数字位数相同是防止用户单选空格后输入数字)
            if (mBeforeTextLength == mOnTextLength && mBeforeNumTxtLength == mNumTxtLength) {
                isChanged = false;
                return;
            } else {
                isChanged = true;
            }

            // 若要进行格式化，则判断该情况
            // 判断是否选中空格覆盖(排除删除空格的情况)
            if (before == 1 && count == 0) {
                isOverrideSpace = false;
            } else {
                isOverrideSpace = mBeforeTextLength - mBeforeSpaceNumber - before + count != mNumTxtLength;
            }
            // 若是该情况，计算覆盖空格的个数
            if (isOverrideSpace) {
                mOverrideSpaceNum = mNumTxtLength - (mBeforeTextLength - mBeforeSpaceNumber - before + count);
            } else {
                mOverrideSpaceNum = 0;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (isChanged) {
                mLocation = mDesTxt.getSelectionEnd();
                // 去除空格
                mBuffer.append(s.toString().replace(" ", ""));

                // 格式化字符串，mOffset位加一个空格
                int index = 0;
                int mAfterSpaceNumber = 0;
                while (index < mBuffer.length()) {
                    if (index == mOffset * (1 + mAfterSpaceNumber) + mAfterSpaceNumber) {
                        mBuffer.insert(index, ' ');
                        mAfterSpaceNumber++;
                    }
                    index++;
                }

                // 判断是否是粘贴键入
                if (isPaste) {
                    mLocation += mPasteNum / mOffset;
                    isPaste = false;
                    // 判断是否是选中空格输入
                } else if (isOverrideSpace) {
                    mLocation += mOverrideSpaceNum;
                    // 判断此时光标是否在特殊位置上
                } else if (mLocation % (mOffset + 1) == 0) {
                    // 是键入OR删除
                    if (mBeforeLocation <= mLocation) {
                        mLocation++;
                    } else {
                        mLocation--;
                    }
                }


                // 若是删除数据刚好删除一位，前一位是空格，mLocation会超出格式化后字符串的长度(因为格
                // 式化后的长度没有不包括最后的空格)，将光标移到正确的位置
                String str = mBuffer.toString();
                if (mLocation > str.length()) {
                    mLocation = str.length();
                } else if (mLocation < 0) {
                    mLocation = 0;
                }
                mDesTxt.setText(str);
                Editable editable = mDesTxt.getText();
                Selection.setSelection(editable, mLocation > mMaxLength ? mMaxLength : mLocation);
                isChanged = false;
            }
        }
    }
}
