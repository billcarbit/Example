package com.example.wangning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-31
 * @since JDK 1.8
 */
public class Fragment2 extends Fragment {
    private static final String TAG = "Fragment2";
    TextView tv;
    EditText et;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment2_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        tv = (TextView) view.findViewById(R.id.tv);
        et = (EditText) view.findViewById(R.id.et);

        initData();
    }

    private void initData() {
        String a = getArguments().getString("a");
        Log.e(TAG, "initData: a=" + a);
        tv.setText(a);
    }
}
