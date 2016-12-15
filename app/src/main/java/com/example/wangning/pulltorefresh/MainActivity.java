package com.example.wangning.pulltorefresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.wangning.R;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();

    private BGARefreshLayout mRefreshLayout;


    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 200) { // 结束下拉刷新
                mRefreshLayout.endRefreshing();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_resresh);
        initRefreshLayout();
    }


    /**
     * 初始化刷新组件
     */
    private void initRefreshLayout() {
        mRefreshLayout = (BGARefreshLayout)findViewById(R.id.rl_modulename_refresh);
        // 为BGARefreshLayout设置代理
        mRefreshLayout.setDelegate(new MyBGARefreshLayoutDelegate());
        mRefreshLayout.setRefreshScaleDelegate(new MyBGARefreshScaleDelegate());
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new HomePageRefreshViewHolder(this, false);
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    public class MyBGARefreshScaleDelegate implements BGARefreshLayout.BGARefreshScaleDelegate {

        @Override
        public void onRefreshScaleChanged(float scale, int moveYDistance) {
            Log.e("MyBGARefreshSca","onRefreshScaleChanged,scale="+scale+",moveYDistance="+moveYDistance);

            //titleLayout.setVisibility(View.GONE);
        }

        @Override
        public void onHideRefreshView() {
            Log.e("MyBGARefreshSca","onHideRefreshView");
            //titleLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 下拉刷新时的监听
     */
    public class MyBGARefreshLayoutDelegate implements BGARefreshLayout.BGARefreshLayoutDelegate {

        @Override
        public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {


            mRefreshLayout.beginRefreshing();
            handler.sendEmptyMessageDelayed(200, 2000);  // 刷新动作持续2秒
        }

        @Override
        public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
            return false;
        }
    }

}
