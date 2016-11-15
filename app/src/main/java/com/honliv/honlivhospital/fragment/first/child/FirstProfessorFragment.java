package com.honliv.honlivhospital.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.adapter.FirstProfessorAdapter;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.contract.FirstContract;
import com.honliv.honlivhospital.listener.OnItemClickListener;
import com.honliv.honlivhospital.model.first.child.FirstProfessorModel;
import com.honliv.honlivhospital.presenter.first.child.FirstProfessorPresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/29.
 */
public class FirstProfessorFragment extends BaseFragment<FirstProfessorPresenter, FirstProfessorModel> implements SwipeRefreshLayout.OnRefreshListener, FirstContract.FirstProfessorView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private SwipeRefreshLayout mRefreshLayout;
    private FirstProfessorAdapter mAdapter;

    public static FirstProfessorFragment newInstance() {

        Bundle args = new Bundle();

        FirstProfessorFragment fragment = new FirstProfessorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_professor;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mToolbar.setTitle(getString(R.string.main_professor));
        initToolbarNav(mToolbar);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);

        mAdapter = new FirstProfessorAdapter(_mActivity);
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
