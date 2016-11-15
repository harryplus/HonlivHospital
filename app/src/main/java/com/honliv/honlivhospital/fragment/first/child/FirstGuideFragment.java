package com.honliv.honlivhospital.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.adapter.FirstProfessorAdapter;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.contract.FirstContract;
import com.honliv.honlivhospital.model.first.child.FirstGuideModel;
import com.honliv.honlivhospital.presenter.first.child.FirstGuidePresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/29.
 */
public class FirstGuideFragment extends BaseFragment<FirstGuidePresenter, FirstGuideModel> implements SwipeRefreshLayout.OnRefreshListener, FirstContract.FirstGuideView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    SwipeRefreshLayout mRefreshLayout;
    FirstProfessorAdapter mAdapter;

    public static FirstGuideFragment newInstance() {

        Bundle args = new Bundle();

        FirstGuideFragment fragment = new FirstGuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_guide;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.main_guide));
        initToolbarNav(mToolbar);
    }

    @Override
    public void onRefresh() {

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
