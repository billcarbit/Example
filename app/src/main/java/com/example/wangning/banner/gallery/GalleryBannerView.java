package com.example.wangning.banner.gallery;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.wangning.R;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */
public class GalleryBannerView extends FrameLayout {
    private static final String TAG = "GalleryBannerView";
    private static final int MSG_LOOP = 1000;
    private static long LOOP_INTERVAL = 5000;
    private ViewPager mViewPager;
    private int sideWidth;
    private Context mContext;
    private RelativeLayout rlVpContainer;
    private BannerHandler mBannerHandler;
    private int mSelectedDrawable;
    private int mNormalDrawable;
    private List<View> viewList;
    private int viewSize;
    private LinearLayout mLinearPosition;


    public GalleryBannerView(Context context) {
        this(context, null);

    }

    public GalleryBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_gallery_banner, this);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        rlVpContainer = (RelativeLayout) findViewById(R.id.rl_vp_container);
    }

    private void initData() {
        initViewPager();
        initLinearPosition();
        this.addView(mLinearPosition);
    }

    private void initViewPager() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        rlVpContainer.measure(0, 0);
        int height = rlVpContainer.getMeasuredHeight();
        Log.e(TAG, "initViewPager: height=" + height + ",width=" + width);
        //设置ViewPager的布局
        sideWidth = (int) (((375 - 339) / 4) / 375.0f * width);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                (int) (width * 339 / 375.0),
                height);
        /**** 重要部分  ******/
        //clipChild用来定义他的子控件是否要在他应有的边界内进行绘制。 默认情况下，clipChild被设置为true。 也就是不允许进行扩展绘制。
        mViewPager.setClipChildren(false);
        //父容器一定要设置这个，否则看不出效果
        rlVpContainer.setClipChildren(false);
        mViewPager.setLayoutParams(params);
        //设置ViewPager切换效果，即实现画廊效果
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        //设置预加载数量
        mViewPager.setOffscreenPageLimit(3);
        //设置每页之间的左右间隔

        mViewPager.setPageMargin(0);// TODO: 2018/1/11

        //将容器的触摸事件反馈给ViewPager
        rlVpContainer.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // dispatch the events to the ViewPager, to solve the problem that we can swipe only the middle view.
                return mViewPager.dispatchTouchEvent(event);
            }

        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                updateLinearPosition();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mBannerHandler != null) {
                            if (mBannerHandler.hasMessages(MSG_LOOP)) {
                                mBannerHandler.removeMessages(MSG_LOOP);
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mBannerHandler != null) {
                            mBannerHandler.sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        if (mBannerHandler != null) {
                            mBannerHandler.sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public void setBottomDrawable(int selectedResId, int normalResId) {
        mSelectedDrawable = selectedResId;
        mNormalDrawable = normalResId;
    }

    public void setLoopInterval(long loopInterval) {
        LOOP_INTERVAL = loopInterval;
    }


    public void setViewList(List<View> viewList) {
        this.viewList = viewList;
        if (viewList != null && viewList.size() != 0) {
            viewSize = viewList.size();
            GalleryBannerAdapter bannerAdapter = new GalleryBannerAdapter(viewList);
            setAdapter(bannerAdapter);
        }
    }

    public void setAdapter(PagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        adapter.registerDataSetObserver(mDataObserver);
        updateLinearPosition();
    }


    public void startLoop(boolean flag) {
        if (flag) {
            if (mBannerHandler == null) {
                mBannerHandler = new BannerHandler(this);
            }
            mBannerHandler.sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
        } else {
            if (mBannerHandler != null) {
                if (mBannerHandler.hasMessages(MSG_LOOP)) {
                    mBannerHandler.removeMessages(MSG_LOOP);
                    mBannerHandler = null;
                }
            }
        }
    }

    private DataSetObserver mDataObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            updateLinearPosition();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    };

    private void initLinearPosition() {
        mLinearPosition = new LinearLayout(getContext());
        mLinearPosition.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.dimen_9dp);
        mLinearPosition.setPadding(getResources().getDimensionPixelSize(R.dimen.dimen_9dp), 0, 0, 0);
        mLinearPosition.setLayoutParams(layoutParams);
    }

    private void updateLinearPosition() {
        if (viewList != null && viewList.size() != 0) {
            if (mLinearPosition.getChildCount() != viewSize) {
                int diffCnt = mLinearPosition.getChildCount() - viewSize;
                boolean needAdd = diffCnt < 0;
                diffCnt = Math.abs(diffCnt);
                for (int i = 0; i < diffCnt; i++) {
                    if (needAdd) {
                        ImageView img = new ImageView(getContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.rightMargin = getResources().getDimensionPixelOffset(R.dimen.dimen_9dp);
                        img.setLayoutParams(layoutParams);
                        img.setBackgroundResource(mNormalDrawable);
                        mLinearPosition.addView(img);
                    } else {
                        mLinearPosition.removeViewAt(0);
                    }
                }
            }
            int curPos = mViewPager.getCurrentItem();
            for (int i = 0; i < mLinearPosition.getChildCount(); i++) {
                if (i == (curPos % viewSize)) {
                    mLinearPosition.getChildAt(i).setBackgroundResource(mSelectedDrawable);
                } else {
                    mLinearPosition.getChildAt(i).setBackgroundResource(mNormalDrawable);
                }
            }
        }
    }


    /**
     * 实现的原理是，在当前显示页面放大至原来的MAX_SCALE
     * 其他页面才是正常的的大小MIN_SCALE
     */
    class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MAX_SCALE = 1.0f;//TODO
        private static final float MIN_SCALE = 0.8f;

        @Override
        public void transformPage(View view, float position) {
            if (position < -1) {
                view.setScaleY(MIN_SCALE);
            } else if (position <= 1) //a页滑动至b页 ； a页从 0.0 ~ -1 ；b页从1 ~ 0.0
            { // [-1,1]
                float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);
                view.setScaleY(scaleFactor);
            } else { // (1,+Infinity]
                view.setScaleY(MIN_SCALE);
            }

        /*    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float rotate = 60 * Math.abs(position);
            if (position < -1) {

            } else if (position < 0) {
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setRotationY(rotate);
            } else if (position >= 0 && position < 1) {
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setRotationY(-rotate);
            } else if (position >= 1) {
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setRotationY(-rotate);
            }*/
        }
    }


    private static class BannerHandler extends Handler {
        private WeakReference<GalleryBannerView> weakReference = null;

        public BannerHandler(GalleryBannerView bannerView) {
            super(Looper.getMainLooper());
            this.weakReference = new WeakReference<GalleryBannerView>(bannerView);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.weakReference == null) {
                return;
            }
            GalleryBannerView bannerView = this.weakReference.get();
            if (bannerView == null || bannerView.mViewPager == null || bannerView.mViewPager.getAdapter() == null || bannerView.mViewPager.getAdapter().getCount() <= 0) {
//                sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
                return;
            }
            int curPos = bannerView.mViewPager.getCurrentItem();
            curPos = (curPos + 1) % bannerView.mViewPager.getAdapter().getCount();
            bannerView.mViewPager.setCurrentItem(curPos);
            if (hasMessages(MSG_LOOP)) {
                removeMessages(MSG_LOOP);
            }
            Log.e(TAG, "handleMessage: curPos=" + curPos);
            sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
        }
    }
}
