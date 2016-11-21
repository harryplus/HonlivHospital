package com.honliv.honlivmall.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.FirstSelectDoctorAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.listener.OnItemClickListener;
import com.honliv.honlivmall.model.first.child.FirstSelectDoctorModel;
import com.honliv.honlivmall.presenter.first.child.FirstSelectDoctorPresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/29.
 */
public class FirstSelectDoctorFragment extends BaseFragment<FirstSelectDoctorPresenter, FirstSelectDoctorModel> implements SwipeRefreshLayout.OnRefreshListener, FirstContract.FirstSelectDoctorView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    private FirstSelectDoctorAdapter mAdapter;

    public static FirstSelectDoctorFragment newInstance() {

        Bundle args = new Bundle();

        FirstSelectDoctorFragment fragment = new FirstSelectDoctorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_product_detail;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mToolbar.setTitle("外科");
        initToolbarNav(mToolbar);

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);

        mAdapter = new FirstSelectDoctorAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {

            }
        });
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
