package com.honliv.honlivmall.fragment.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseLazyMainFragment;
import com.honliv.honlivmall.contract.ThirdContract;
import com.honliv.honlivmall.fragment.third.child.ThirdMainFragment;
import com.honliv.honlivmall.model.third.ThirdModel;
import com.honliv.honlivmall.presenter.third.ThirdPresenter;

/**
 * Created by Rodin on 2016/10/25.
 */
public class ThirdFragment extends BaseLazyMainFragment<ThirdPresenter,ThirdModel> implements ThirdContract.ThirdView {
    public static ThirdFragment newInstance() {

        Bundle args = new Bundle();

        ThirdFragment fragment = new ThirdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_third;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

//    @Override
//    protected void initLazyView(@Nullable Bundle savedInstanceState) {
//        if (savedInstanceState == null) {
//            loadRootFragment(R.id.fl_third_container, FirstHomeFragment.newInstance());
//        }
//    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadFragment();
        } else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(ThirdMainFragment.class) == null) {
                loadFragment();
            }
        }

//        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar);
//        mToolbar.setTitle("我的");
//        initToolbarMenu(mToolbar);
    }

    private void loadFragment() {
        loadRootFragment(R.id.fl_third_container, ThirdMainFragment.newInstance());
//        loadRootFragment(R.id.fl_fourth_container_lower, MeFragment.newInstance());
    }

    public void onBackToFirstFragment() {
        _mBackToFirstListener.onBackToFirstFragment();
    }

    @Override
    public void showError(String msg) {

    }
}
