package com.example.wangning.mdtablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangning.R;

/**
 * Created by Administrator on 2016/9/19.
 */
public class MyFragment extends Fragment {
    private String mTitle;

    public MyFragment() {

    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_md_tablayout, container, false);
        TextView tv_fragment=(TextView)view.findViewById(R.id.tv_fragment);
        tv_fragment.setText(mTitle);
        return view;
    }
}
