package com.honliv.honlivmall.fragment.third.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.ThirdContract;
import com.honliv.honlivmall.model.third.child.ThirdMainModel;
import com.honliv.honlivmall.presenter.third.child.ThirdMainPresenter;

/**
 * Created by Rodin on 2016/10/26.
 */
public class ThirdMainFragment extends BaseFragment<ThirdMainPresenter, ThirdMainModel> implements ThirdContract.ThirdMainView {
    public static ThirdMainFragment newInstance() {

        Bundle args = new Bundle();

        ThirdMainFragment fragment = new ThirdMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_third_main;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showError(String msg) {

    }
}