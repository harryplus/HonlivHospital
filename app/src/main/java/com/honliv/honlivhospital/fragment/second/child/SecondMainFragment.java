package com.honliv.honlivhospital.fragment.second.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.contract.SecondContract;
import com.honliv.honlivhospital.model.second.child.SecondMainModel;
import com.honliv.honlivhospital.presenter.second.child.SecondMainPresenter;

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
