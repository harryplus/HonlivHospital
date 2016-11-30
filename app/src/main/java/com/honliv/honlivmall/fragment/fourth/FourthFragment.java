package com.honliv.honlivmall.fragment.fourth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseLazyMainFragment;
import com.honliv.honlivmall.contract.FourthContract;
import com.honliv.honlivmall.model.fourth.FourthModel;
import com.honliv.honlivmall.presenter.fourth.FourthPresenter;

/**
 * Created by Rodin on 2016/10/25.
 */
public class FourthFragment extends BaseLazyMainFragment<FourthPresenter, FourthModel> implements FourthContract.FourthView {
    public static FourthFragment newInstance() {

        Bundle args = new Bundle();

        FourthFragment fragment = new FourthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fourth;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden){
            showToast("onHiddenChanged");
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadFragment();
        } else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(FourthMainFragment.class) == null) {
                loadFragment();
            }
        }

//        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar);
//        mToolbar.setTitle("我的");
//        initToolbarMenu(mToolbar);
    }

    private void loadFragment() {
        loadRootFragment(R.id.fl_fourth_container, FourthMainFragment.newInstance());
//        loadRootFragment(R.id.fl_fourth_container_lower, MeFragment.newInstance());
    }

    public void onBackToFirstFragment() {
        _mBackToFirstListener.onBackToFirstFragment();
    }

    @Override
    public void showError(String msg) {

    }
}
