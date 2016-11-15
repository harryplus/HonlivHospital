package com.honliv.honlivhospital.fragment.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseLazyMainFragment;
import com.honliv.honlivhospital.contract.FirstContract;
import com.honliv.honlivhospital.fragment.first.child.FirstHomeFragment;
import com.honliv.honlivhospital.model.first.FirstModel;
import com.honliv.honlivhospital.presenter.first.FirstPresenter;

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
}
