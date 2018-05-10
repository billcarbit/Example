package com.example.wangning.parkkeyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/5/10.
 */
public class CustomKeyboard {
    private static final String TAG = "CustomKeyboard";
    private EditText mEdittext;
    private MyKeyboardView mKeyboardView;
    private Keyboard mKeyboard;

    public CustomKeyboard(Context context, MyKeyboardView keyboardView, EditText editText) {
        this.mEdittext = editText;
        mKeyboard = new Keyboard(context, R.xml.province_abbreviation);//从xml中加载自定义的键盘
        mKeyboardView = keyboardView;
        mKeyboardView.setContext(context);
        mKeyboardView.setKeyboard(mKeyboard);
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setOnKeyboardActionListener(actionListener);
    }

    private KeyboardView.OnKeyboardActionListener actionListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {
            Log.e(TAG, "onPress: primaryCode=" + primaryCode);
        }

        @Override
        public void onRelease(int primaryCode) {
            Log.e(TAG, "onRelease: primaryCode=" + primaryCode);
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = mEdittext.getText();
            int index = mEdittext.getSelectionStart();//光标位置
            switch (primaryCode) {
                case Keyboard.KEYCODE_DELETE://回退
                    if (editable != null && editable.length() > 0) {
                        if (index > 0) {
                            editable.delete(index - 1, index);
                        }
                    }
                    break;
                case 9995://重输
                    mEdittext.setText("");
                    break;
                case 9994://左移
                    if (index > 0) {
                        mEdittext.setSelection(index - 1);
                    }
                    break;
                case 9996://右移
                    if (index < mEdittext.length()) {
                        mEdittext.setSelection(index + 1);
                    }
                    break;
                default:
                    editable.insert(index, Character.toString((char) primaryCode));
                    break;
            }
        }

        @Override
        public void onText(CharSequence text) {
            Log.e(TAG, "onText: text=" + text);
        }

        @Override
        public void swipeLeft() {
            Log.e(TAG, "swipeLeft");
        }

        @Override
        public void swipeRight() {
            Log.e(TAG, "swipeRight");
        }

        @Override
        public void swipeDown() {
            Log.e(TAG, "swipeDown");
        }

        @Override
        public void swipeUp() {
            Log.e(TAG, "swipeUp");
        }
    };

    public void showKeyboard() {
        if (mKeyboardView.getVisibility() != View.VISIBLE) {
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        if (mKeyboardView.getVisibility() == View.VISIBLE) {
            mKeyboardView.setVisibility(View.GONE);
        }
    }

    public boolean isShowKeyboard() {
        return mKeyboardView.getVisibility() == View.VISIBLE;
    }


}
