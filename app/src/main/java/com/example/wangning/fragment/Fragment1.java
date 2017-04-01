package com.example.wangning.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-31
 * @since JDK 1.8
 */
public class Fragment1 extends Fragment implements View.OnClickListener {

    private static final String TAG = "Fragment1";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment1_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        initView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_f1:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.frame_layout,new Fragment2());
                ft.commit();
                break;
            default:
                break;
        }
    }

    private void initView(View view) {
        TextView mTvF1 = (TextView) view.findViewById(R.id.tv_f1);
        mTvF1.setOnClickListener(this);

        Bundle bundle=getArguments();
        String aaa = bundle.getString("a");
        Log.d(TAG, "initView: aaa="+aaa);
    }
}
