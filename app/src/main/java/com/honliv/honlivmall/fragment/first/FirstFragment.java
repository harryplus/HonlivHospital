package com.honliv.honlivmall.fragment.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseLazyMainFragment;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.fragment.first.child.FirstHomeFragment;
import com.honliv.honlivmall.model.first.FirstModel;
import com.honliv.honlivmall.presenter.first.FirstPresenter;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class FirstFragment extends BaseLazyMainFragment<FirstPresenter, FirstModel> implements FirstContract.FirstView {

    public static FirstFragment newInstance() {
        Bundle args = new Bundle();

        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_first_container, FirstHomeFragment.newInstance());
        }
    }


    public void onBackToFirstFragment() {
//        _mBackToFirstListener.onBackToFirstFragment();
        loadRootFragment(R.id.fl_first_container, FirstHomeFragment.newInstance());
    }
}
