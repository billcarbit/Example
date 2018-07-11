package com.example.wangning.scrollview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wangning.R;
import com.example.wangning.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/9.
 */
public class ScrollListViewActivity extends Activity implements AdapterView.OnItemClickListener {

    private MaxSpecListView lv;
    private List<String> mList = new ArrayList<>();
    private ArrayAdapter mAdapter;
    private PullUpToLoad mPullUpToLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_listview);
        mPullUpToLoad = new PullUpToLoad(getApplicationContext());
        lv = (MaxSpecListView) findViewById(R.id.lv);
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mList);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(this);
        lv.addFooterView(mPullUpToLoad);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fillData();
            }
        }, 1000);

    }

    private void fillData() {
        for (int i = 0; i < 10;i++) {
            mList.add("A" + i);
        }
        //ViewUtils.setListViewHeight(lv);
        mAdapter.notifyDataSetChanged();
        mPullUpToLoad.showClickLoadMore(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        View rlClickLoadMore = view.findViewById(R.id.rl_click_load_more);
        if (rlClickLoadMore != null) {//点击加载更多
            for (int i = 0; i < 10; i++) {
                mList.add("B" + i);
            }
            //ViewUtils.setListViewHeight(lv);
            mAdapter.notifyDataSetChanged();
        }


    }
}
