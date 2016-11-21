package com.honliv.honlivmall.fragment.first.child;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.BargainGridAdapter;
import com.honliv.honlivmall.adapter.LikeGridAdapter;
import com.honliv.honlivmall.adapter.LimitGridAdapter;
import com.honliv.honlivmall.adapter.MainPagerAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.GalleryProduct;
import com.honliv.honlivmall.bean.HomeBanner;
import com.honliv.honlivmall.bean.HomeBrand;
import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.model.first.child.FirstHomeModel;
import com.honliv.honlivmall.presenter.first.child.FirstHomePresenter;
import com.honliv.honlivmall.util.ViewUtils;
import com.honliv.honlivmall.view.MSGallery;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;


/**
 * Created by rodin on 16/6/5.
 */
public class FirstHomeFragment extends BaseFragment<FirstHomePresenter, FirstHomeModel> implements View.OnClickListener, FirstContract.FirstHomeView {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.seach_keyword)
    Button seachkeywordET;
    @BindView(R.id.limit)
    MSGallery limit;
    @BindView(R.id.bargain)
    MSGallery bargain;
    @BindView(R.id.like)
    MSGallery like;
    @BindView(R.id.more_limit)
    TextView more_limit;
    @BindView(R.id.more_bargain)
    TextView more_bargain;

    private MainPagerAdapter viewPagerAdapter;
    private LimitGridAdapter limitAdapter;
    private BargainGridAdapter bargainAdapter;
    private LikeGridAdapter likeAdapter;
    private List<Product> limitproduct;
    private int currentItem = 0;
    private MyPagerTask myPagerTask;
    private Timer timer;
    private TimerTask task;
    private ScheduledExecutorService scheduledExecutor;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int resultNum = msg.what;
            if (resultNum == 20) {
                try {
                    for (int i = 0; i < limitproduct.size(); i++) {
                        TextView timeTV = (TextView) limit.findViewWithTag(i);
                        String dateStr = limitproduct.get(i).getLefttime();
                        if (timeTV != null) {
                            ViewUtils.updataTimeTV(timeTV, dateStr);
                        }
                    }
                } catch (Exception e) {
                }
            } else if (resultNum == 10) {
                viewPager.setCurrentItem(currentItem);
            }
        }
    };
    ;

    public static FirstHomeFragment newInstance() {
        Bundle args = new Bundle();

        FirstHomeFragment fragment = new FirstHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        scheduledExecutor.shutdown();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        seachkeywordET.setOnClickListener(this);
        more_limit.setOnClickListener(this);
        more_bargain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.seach_keyword:
//                FirstBargainFragment detailFragment = FirstBargainFragment.newInstance();
//                start(detailFragment);
                break;

            case R.id.more_limit:
                FirstMarketingFragment marketingFragment = FirstMarketingFragment.newInstance();
                start(marketingFragment);
                break;
            case R.id.more_bargain:
                FirstBargainFragment bargainFragment = FirstBargainFragment.newInstance();
                start(bargainFragment);
                break;
//            case R.id.main_body_registrate_day:
////                GlobalOfficeSelectFragment selectFragment1 = GlobalOfficeSelectFragment.newInstance();
////                start(selectFragment1);
//                break;
//            case R.id.main_body_registrate_recommend:
////                GlobalOfficeSelectFragment selectFragment2 = GlobalOfficeSelectFragment.newInstance();
////                start(selectFragment2);
//                break;
//            case R.id.main_body_report:
//
//                break;
//            case R.id.main_body_cost:
//
//                break;
//            case R.id.main_body_office:
//
//                break;
//            case R.id.main_body_handbook:
//
//                break;
//            case R.id.main_body_question:
//
//                break;
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public boolean onBackPressedSupport() {
//        loadRootFragment(R.id.fl_container, FirstHomeFragment.newInstance());
        showToast(getString(R.string.again_exit));
        return true;
    }

    @Override
    public void initData() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        viewPagerAdapter = new MainPagerAdapter(getContext());
        viewPager.setAdapter(viewPagerAdapter);
        limitproduct = new ArrayList<>();
        limitAdapter = new LimitGridAdapter(getContext(), limitproduct);
        limit.setAdapter(limitAdapter);
        bargainAdapter = new BargainGridAdapter(getContext());
        bargain.setAdapter(bargainAdapter);
        likeAdapter = new LikeGridAdapter(getContext());
        like.setAdapter(likeAdapter);
        mPresenter.getServiceHomeInfo("0");
        mPresenter.getServiceHomeMarketing();
        myPagerTask = new MyPagerTask();
        scheduledExecutor.scheduleAtFixedRate(myPagerTask, 5, 5,
                TimeUnit.SECONDS);
    }

    @Override
    public void updataHomeInfo(HomeInfo info) {
        List<HomeBanner> home_banner = info.getHome_banner();
        viewPagerAdapter.addAll(home_banner);
        viewPagerAdapter.notifyDataSetChanged();
        List<Product> cheapproductlist = info.getCheapproductlist();
        bargainAdapter.addAll(cheapproductlist);
        bargainAdapter.notifyDataSetChanged();

        likeAdapter.addAll(cheapproductlist);
        likeAdapter.notifyDataSetChanged();
    }

    @Override
    public void updataHomeMarketing(List<Product> result) {
        if (result != null) {
            limitproduct.addAll(result);
            limitAdapter.notifyDataSetChanged();
            initTimer();
        }
    }

    private void initTimer() {
        timer = new Timer();
        task = new TimerTask() {
            public void run() {
                Message message = Message.obtain();
                message.what = 20;
                handler.sendMessage(message);
            }
        };
        timer.schedule(task, 1000, 1000);
    }

    private class MyPagerTask implements Runnable {
        public void run() {
            currentItem++;
            Message msg = new Message();
            msg.what = 10;
            handler.sendMessage(msg);
        }
    }
}
