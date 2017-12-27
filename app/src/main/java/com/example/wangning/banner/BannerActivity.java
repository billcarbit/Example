package com.example.wangning.banner;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;
import com.example.wangning.fragment.Fragment2;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态banner
 *
 * @author wangning
 * @version 1.0 2017-10-18
 * @since JDK 1.8
 */
public class BannerActivity extends FragmentActivity {
    private static final String TAG = "BannerActivity";
    ViewPager vp;
    List<BannerFragment> viewList = new ArrayList<>();
    FragmentAdapter adapter;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Log.e(TAG, "handleMessage: v=" + vp.getCurrentItem());
                if (vp.getCurrentItem() >= vp.getChildCount()-1) {
                    vp.setCurrentItem(0,false);
                } else {
                    vp.setCurrentItem(vp.getCurrentItem() + 1);
                }
                mHandler.sendEmptyMessageDelayed(1, 2000);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        initView();
        initData();
    }

    void initView() {
        vp = (ViewPager) findViewById(R.id.vp);

    }

    void initData() {
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    BannerFragment aaa = new BannerFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("url", "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=76d0d7d9adefce1bfe26c089c73899ab/9c16fdfaaf51f3de52b02d4b9eeef01f3a29799b.jpg");
                    aaa.setArguments(bundle);
                    viewList.add(aaa);
                    break;
                case 1:
                    BannerFragment bbb = new BannerFragment();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("url", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1305/23/c9/21233806_21233806_1369310564359.jpg");
                    bbb.setArguments(bundle1);
                    viewList.add(bbb);
                    break;
                case 2:
                    BannerFragment ccc = new BannerFragment();
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("url", "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=6572cde373310a55d029d6b7df2c29dc/b219ebc4b74543a91062addc14178a82b90114a0.jpg");
                    ccc.setArguments(bundle2);
                    viewList.add(ccc);
                    break;
                default:
                    break;
            }


        }

        adapter = new FragmentAdapter(getSupportFragmentManager(), viewList);
        vp.setAdapter(adapter);
        //vp.setPageTransformer(true, new DepthPageTransformer());
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }
}
