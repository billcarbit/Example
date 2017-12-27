package com.example.wangning.banner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangning.R;
import com.example.wangning.utils.ImageUtils;

/**
 * Created by Administrator on 2017/12/27.
 */

public class BannerFragment extends Fragment {
    private static final String TAG = "BannerFragment";
    ImageView iv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_banner, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        iv = (ImageView) view.findViewById(R.id.iv);
        initData();
    }

    private void initData() {
        String url = (String) getArguments().get("url");
        Log.e(TAG, "initData: url=" + url);
        ImageUtils.displayImage(url, iv);
    }
}
