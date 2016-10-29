package com.honliv.honlivhospital.fragment.first.child;

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
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.listener.OnItemClickListener;

/**
 * Created by Rodin on 2016/10/29.
 */
public class FirstGuideFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private Toolbar mToolbar;
    private RecyclerView recyclerview;
    private SwipeRefreshLayout mRefreshLayout;
    private FirstProfessorAdapter mAdapter;

    public static FirstGuideFragment newInstance() {

        Bundle args = new Bundle();

        FirstGuideFragment fragment = new FirstGuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_guide, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.main_guide));
        initToolbarNav(mToolbar);

    }

    @Override
    public void onRefresh() {

    }
}
