package com.honliv.honlivmall.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.model.first.child.FirstDetailModel;
import com.honliv.honlivmall.presenter.first.child.FirstDetailPresenter;

import butterknife.BindView;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class FirstDetailFragment extends BaseFragment<FirstDetailPresenter, FirstDetailModel> implements FirstContract.FirstDetailView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static FirstDetailFragment newInstance() {
        Bundle args = new Bundle();

        FirstDetailFragment fragment = new FirstDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_detail;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mToolbar.setTitle(getString(R.string.hosipital_detail_detail));
        initToolbarNav(mToolbar);
    }


    @Override
    public void showError(String msg) {

    }

    @Override
    public boolean onBackPressedSupport() {
//        loadRootFragment(R.id.fl_first_container, FirstHomeFragment.newInstance());
//        _mBackToFirstListener.onBackToFirstFragment();
//        _mActivity.onBackPressed();
        return true;
    }
}
