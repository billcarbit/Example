package com.example.wangning.swiperefreshlayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangning.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-01-10
 * @since JDK 1.8
 */
public class SwipRefreshActivity extends Activity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener,
        RecyclerViewLoadMoreListener.OnLoadMoreListener {
    private static final String TAG = "SwipRefreshActivity";
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private List<String> mList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiprefresh);
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setOnClickListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerViewLoadMoreListener(
                llm, this, Constant.PER_PAGE_COUNT));

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initData() {
        addData();
        mAdapter = new MyAdapter(this, mList);
        //mRecyclerView.setAdapter(mAdapter);
    }

    private void addData() {
        int originValue = mList.size();
        if (originValue > 40) {
            return;
        }
        for (int i = originValue; i < originValue + 10; i++) {
            Log.e(TAG, "addData: i="+i );
            mList.add("i=" + i);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recycler_view:
                Log.e(TAG, "onClick: recycler_view");
                break;
            case R.id.tv_banner:
                Log.e(TAG, "onClick: tv_banner");
                break;
            default:
                break;
        }

    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        },2000);


    }

    @Override
    public void onLoadMore() {
        Log.e(TAG, "onLoadMore: " );
        addData();
        mAdapter.notifyDataSetChanged();
    }
}
