package com.honliv.honlivhospital.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.adapter.FirstProfessorAdapter;
import com.honliv.honlivhospital.adapter.FourthBookLogAdapter;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.base.BaseLazyMainFragment;
import com.honliv.honlivhospital.listener.OnItemClickListener;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthBookFragment extends BaseFragment implements BaseLazyMainFragment.OnBackToFirstListener, SwipeRefreshLayout.OnRefreshListener {
    private Toolbar mToolbar;
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView recyclerview;
    private FourthBookLogAdapter mAdapter;

    public static FourthBookFragment newInstance() {

        Bundle args = new Bundle();

        FourthBookFragment fragment = new FourthBookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth_book, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.text_book_log));
        initToolbarNav(mToolbar);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
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
}