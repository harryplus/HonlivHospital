package com.honliv.honlivmall.fragment.second.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.SecondContract;
import com.honliv.honlivmall.model.second.child.SecondMainModel;
import com.honliv.honlivmall.presenter.second.child.SecondMainPresenter;

/**
 * Created by Rodin on 2016/10/26.
 */
public class SecondMainFragment extends BaseFragment<SecondMainPresenter,SecondMainModel> implements SecondContract.SecondMainView {
    public static SecondMainFragment newInstance() {

        Bundle args = new Bundle();

        SecondMainFragment fragment = new SecondMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_second_main;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showError(String msg) {

    }
}
