package com.honliv.honlivmall.fragment.first.child;

import android.os.Bundle;
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
import com.honliv.honlivmall.view.MSGallery;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class FirstHomeFragment extends BaseFragment<FirstHomePresenter, FirstHomeModel> implements View.OnClickListener, FirstContract.FirstHomeView {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.seach_keyword)
    Button seachkeywordET;
    @BindView(R.id.more_limit)
    TextView more_limit;
    @BindView(R.id.more_bargain)
    TextView more_bargain;
    @BindView(R.id.limit)
    MSGallery limit;
    @BindView(R.id.bargain)
    MSGallery bargain;
    @BindView(R.id.like)
    MSGallery like;
    private MainPagerAdapter viewPagerAdapter;
    private LimitGridAdapter limitAdapter;
    private BargainGridAdapter bargainAdapter;
    private LikeGridAdapter likeAdapter;

    public static FirstHomeFragment newInstance() {
        Bundle args = new Bundle();

        FirstHomeFragment fragment = new FirstHomeFragment();
        fragment.setArguments(args);
        return fragment;
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
//                FirstDetailFragment detailFragment = FirstDetailFragment.newInstance();
//                start(detailFragment);
                break;

            case R.id.more_limit:
//                FirstProfessorFragment professorFragment = FirstProfessorFragment.newInstance();
//                start(professorFragment);
                break;
            case R.id.more_bargain:
//                FirstGuideFragment guideFragment = FirstGuideFragment.newInstance();
//                start(guideFragment);
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
        viewPagerAdapter = new MainPagerAdapter(getContext());
        viewPager.setAdapter(viewPagerAdapter);
        limitAdapter = new LimitGridAdapter(getContext());
        limit.setAdapter(limitAdapter);
        bargainAdapter = new BargainGridAdapter(getContext());
        bargain.setAdapter(bargainAdapter);
        likeAdapter = new LikeGridAdapter(getContext());
        like.setAdapter(likeAdapter);
        mPresenter.getServiceHomeInfo("0");
        mPresenter.getServiceHomeMarketing();
    }

    @Override
    public void updataHomeInfo(HomeInfo info) {
        List<HomeBrand> brandlist = info.getBrandlist();
        List<GalleryProduct> galleryproduct = info.getGalleryproduct();
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
            limitAdapter.addAll(result);
            limitAdapter.notifyDataSetChanged();
        }
    }
}
