package com.honliv.honlivmall.fragment.first.child;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.zxing.activity.CaptureActivity;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.MainActivity;
import com.honliv.honlivmall.adapter.BargainGridAdapter;
import com.honliv.honlivmall.adapter.BrandGridAdapter;
import com.honliv.honlivmall.adapter.LimitGridAdapter;
import com.honliv.honlivmall.adapter.MainPagerAdapter;
import com.honliv.honlivmall.application.MyApplication;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.HomeBanner;
import com.honliv.honlivmall.bean.HomeBrand;
import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.fragment.second.child.SecondMainFragment;
import com.honliv.honlivmall.listener.GVBrandItemListener;
import com.honliv.honlivmall.listener.GVItemListener;
import com.honliv.honlivmall.model.first.child.FirstHomeModel;
import com.honliv.honlivmall.presenter.first.child.FirstHomePresenter;
import com.honliv.honlivmall.util.ViewUtils;
import com.honliv.honlivmall.view.MSGallery;
import com.rd.PageIndicatorView;

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
    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;
    @BindView(R.id.seach_keyword)
    Button seachkeywordET;
    @BindView(R.id.marketing)
    MSGallery marketing;
    @BindView(R.id.bargain)
    MSGallery bargain;
    @BindView(R.id.like)
    MSGallery like;
    @BindView(R.id.more_marketing)
    TextView more_marketing;
    @BindView(R.id.more_bargain)
    TextView more_bargain;
    @BindView(R.id.searchImageButton)
    ImageButton searchImageButton;

    private MainPagerAdapter viewPagerAdapter;
    private LimitGridAdapter marketingAdapter;
    private BargainGridAdapter bargainAdapter;
    private BrandGridAdapter brandAdapter;
    private List<Product> marketingProduct;
    private int currentItem = 0;
    private MyPagerTask myPagerTask;
    private Timer timer;
    private TimerTask task;
    private ScheduledExecutorService scheduledExecutor;
    private List<Product> bargainproduct;
    private ArrayList<HomeBrand> brandProduct;
    private long firstClick = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int resultNum = msg.what;
            if (resultNum == 20) {
                try {
                    for (int i = 0; i < marketingProduct.size(); i++) {
                        TextView timeTV = (TextView) marketing.findViewWithTag(i);
                        String dateStr = marketingProduct.get(i).getLefttime();
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
        more_marketing.setOnClickListener(this);
        more_bargain.setOnClickListener(this);
        searchImageButton.setOnClickListener(this);
        pageIndicatorView.setViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.seach_keyword:
//                start(SecondMainFragment.newInstance());
                ((MainActivity)getActivity()).startAssignFragment(MainActivity.SECOND,SecondMainFragment.class);
                break;

            case R.id.more_marketing:
                FirstMarketingFragment marketingFragment = FirstMarketingFragment.newInstance();
                start(marketingFragment);
                break;
            case R.id.more_bargain:
                FirstBargainFragment bargainFragment = FirstBargainFragment.newInstance();
                start(bargainFragment);
                break;
            case R.id.searchImageButton:
                Intent intent=new Intent(getActivity(), CaptureActivity.class);
                startActivity(intent);
                break;
//            case R.id.main_body_registrate_recommend:
////                GlobalLoginFragment selectFragment2 = GlobalLoginFragment.newInstance();
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
        if (System.currentTimeMillis() - firstClick < 1500) {
            MyApplication.getInstance().exitApp();
            return true;
        }
        showToast(getString(R.string.again_exit));
        firstClick = System.currentTimeMillis();
        return true;
    }

    @Override
    public void initData() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        viewPagerAdapter = new MainPagerAdapter(getContext());
        viewPager.setAdapter(viewPagerAdapter);
        marketingProduct = new ArrayList<>();
        marketingAdapter = new LimitGridAdapter(getContext(), marketingProduct);
        marketing.setAdapter(marketingAdapter);
        bargainproduct = new ArrayList<>();
        bargainAdapter = new BargainGridAdapter(getContext(), bargainproduct);
        bargain.setAdapter(bargainAdapter);
        brandProduct = new ArrayList<>();
        brandAdapter = new BrandGridAdapter(getContext(), brandProduct);
        like.setAdapter(brandAdapter);
        mPresenter.getServiceHomeInfo("0");
        mPresenter.getServiceHomeMarketing();

        myPagerTask = new MyPagerTask();
        scheduledExecutor.scheduleAtFixedRate(myPagerTask, 5, 5,
                TimeUnit.SECONDS);
        marketing.setOnItemClickListener(new GVItemListener(getContext(), this, mPresenter.mRxManager, marketingProduct, true));
        bargain.setOnItemClickListener(new GVItemListener(getContext(), this, mPresenter.mRxManager, bargainproduct, false));
        like.setOnItemClickListener(new GVBrandItemListener(getContext(), this,  brandProduct, false));
    }

    @Override
    public void updataHomeInfo(HomeInfo info) {
        if (info != null) {
            List<HomeBanner> home_banner = info.getHome_banner();
            if (home_banner != null) {
                viewPagerAdapter.addAll(home_banner);
                viewPagerAdapter.notifyDataSetChanged();
            }
            List<Product> cheapproductlist = info.getCheapproductlist();
            if (cheapproductlist != null) {
                bargainproduct.addAll(cheapproductlist);
                bargainAdapter.notifyDataSetChanged();
                bargain.setSelection(100 * bargainproduct.size() + 1);
            }
            List<HomeBrand> brandlist = info.getBrandlist();
            if (brandlist != null) {
                brandAdapter.addAll(brandlist);
                brandAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void updataHomeMarketing(List<Product> result) {
        if (result != null) {
            marketingProduct.addAll(result);
            marketingAdapter.notifyDataSetChanged();
            marketing.setSelection(100 * marketingProduct.size() + 1);
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
