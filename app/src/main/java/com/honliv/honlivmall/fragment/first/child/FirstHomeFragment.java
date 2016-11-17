package com.honliv.honlivmall.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.MainPagerAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.fragment.global.GlobalOfficeSelectFragment;
import com.honliv.honlivmall.model.first.child.FirstHomeModel;
import com.honliv.honlivmall.presenter.first.child.FirstHomePresenter;
import com.rd.PageIndicatorView;
import com.rd.animation.AnimationType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class FirstHomeFragment extends BaseFragment<FirstHomePresenter, FirstHomeModel> implements View.OnClickListener, FirstContract.FirstHomeView {

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.main_body_detail:
//                FirstDetailFragment detailFragment = FirstDetailFragment.newInstance();
//                start(detailFragment);
//                break;
//            case R.id.main_body_professor:
//                FirstProfessorFragment professorFragment = FirstProfessorFragment.newInstance();
//                start(professorFragment);
//                break;
//            case R.id.main_body_guide:
//                FirstGuideFragment guideFragment = FirstGuideFragment.newInstance();
//                start(guideFragment);
//                break;
//            case R.id.main_body_registrate_day:
//                GlobalOfficeSelectFragment selectFragment1 = GlobalOfficeSelectFragment.newInstance();
//                start(selectFragment1);
//                break;
//            case R.id.main_body_registrate_recommend:
//                GlobalOfficeSelectFragment selectFragment2 = GlobalOfficeSelectFragment.newInstance();
//                start(selectFragment2);
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
}
