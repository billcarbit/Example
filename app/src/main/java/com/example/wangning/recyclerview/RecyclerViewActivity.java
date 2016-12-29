package com.example.wangning.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2016-12-28
 * @since JDK 1.8
 */
public class RecyclerViewActivity extends Activity implements RecyclerViewLoadMoreListener.OnLoadMoreListener {
    PersonAdapter personAdapter;
    List<String> mList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // 绑定recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        for (int i = 0; i < 30; i++) {
            mList.add("aaa=" + i);
        }
        personAdapter = new PersonAdapter(mList);
        recyclerView.setAdapter(personAdapter);
        recyclerView.addOnScrollListener(new RecyclerViewLoadMoreListener(
                linearLayoutManager, this, 10));
    }

    @Override
    public void onLoadMore() {
        int listSize = mList.size();
        int i;
        for (i = listSize; i < listSize + 10 && i < 50; i++) {
            Log.e("ssss", "onLoadMore: " + ",aaa=" + i);
            mList.add("aaa=" + i);
        }
        if (i <= 49) {
            personAdapter.notifyItemRangeInserted(listSize - 10, 10);
        }
    }
}
