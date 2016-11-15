package com.honliv.honlivhospital.fragment.second;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseLazyMainFragment;
import com.honliv.honlivhospital.contract.SecondContract;
import com.honliv.honlivhospital.fragment.second.child.SecondMainFragment;
import com.honliv.honlivhospital.model.second.SecondModel;
import com.honliv.honlivhospital.presenter.second.SecondPresenter;

/**
 * Created by Rodin on 2016/10/25.
 */
public class SecondFragment extends BaseLazyMainFragment<SecondPresenter,SecondModel> implements SecondContract.SecondView {
    public static SecondFragment newInstance() {

        Bundle args = new Bundle();

        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

//    @Override
//    protected void initLazyView(@Nullable Bundle savedInstanceState) {
//        if (savedInstanceState == null) {
//            loadRootFragment(R.id.fl_second_container, FirstHomeFragment.newInstance());
//        }
//    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadFragment();
        } else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(SecondMainFragment.class) == null) {
                loadFragment();
            }
        }

//        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar);
//        mToolbar.setTitle("我的");
//        initToolbarMenu(mToolbar);
    }

    private void loadFragment() {
        loadRootFragment(R.id.fl_second_container, SecondMainFragment.newInstance());
//        loadRootFragment(R.id.fl_fourth_container_lower, MeFragment.newInstance());
    }

    public void onBackToFirstFragment() {
        _mBackToFirstListener.onBackToFirstFragment();
    }

    @Override
    public void showError(String msg) {

    }
}
