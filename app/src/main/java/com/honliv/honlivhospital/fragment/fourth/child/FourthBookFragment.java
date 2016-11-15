package com.honliv.honlivhospital.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.adapter.FourthBookLogAdapter;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.base.BaseLazyMainFragment;
import com.honliv.honlivhospital.contract.FourthContract;
import com.honliv.honlivhospital.listener.OnItemClickListener;
import com.honliv.honlivhospital.model.fourth.child.FourthBookModel;
import com.honliv.honlivhospital.presenter.fourth.child.FourthBookPresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthBookFragment extends BaseFragment<FourthBookPresenter,FourthBookModel> implements BaseLazyMainFragment.OnBackToFirstListener, SwipeRefreshLayout.OnRefreshListener,FourthContract.FourthBookView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private FourthBookLogAdapter mAdapter;

    public static FourthBookFragment newInstance() {

        Bundle args = new Bundle();

        FourthBookFragment fragment = new FourthBookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fourth_book;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mToolbar.setTitle(getString(R.string.text_book_log));
        initToolbarNav(mToolbar);

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);

        mAdapter = new FourthBookLogAdapter(_mActivity);
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
    public void onBackToFirstFragment() {
        _mActivity.onBackPressed();
    }

    @Override
    public void onRefresh() {

    }


    @Override
    public void showError(String msg) {

    }
}
