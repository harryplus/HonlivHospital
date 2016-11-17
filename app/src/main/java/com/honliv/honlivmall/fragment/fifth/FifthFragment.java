package com.honliv.honlivmall.fragment.fifth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseLazyMainFragment;
import com.honliv.honlivmall.contract.SecondContract;
import com.honliv.honlivmall.fragment.fifth.child.FifthHomeFragment;
import com.honliv.honlivmall.model.second.SecondModel;
import com.honliv.honlivmall.presenter.second.SecondPresenter;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthFragment extends BaseLazyMainFragment<SecondPresenter,SecondModel> implements SecondContract.SecondView {
    public static FifthFragment newInstance() {

        Bundle args = new Bundle();

        FifthFragment fragment = new FifthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }
    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadFragment();
        } else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(FifthHomeFragment.class) == null) {
                loadFragment();
            }
        }
    }

    private void loadFragment() {
        loadRootFragment(R.id.fl_fifth_container, FifthHomeFragment.newInstance());
    }

    public void onBackToFirstFragment() {
        _mBackToFirstListener.onBackToFirstFragment();
    }

    @Override
    public void showError(String msg) {

    }
}
