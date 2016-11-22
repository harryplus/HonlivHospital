package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthMyHxtModel;
import com.honliv.honlivmall.model.fifth.child.FifthSettingModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthMyHxtPresenter;
import com.honliv.honlivmall.presenter.fifth.child.FifthSettingPresenter;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthSettingFragment extends BaseFragment<FifthSettingPresenter, FifthSettingModel> implements FifthContract.FifthSettingView, View.OnClickListener {

    public static FifthSettingFragment newInstance() {
        Bundle args = new Bundle();

        FifthSettingFragment fragment = new FifthSettingFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutId() {
        return 0;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showError(String msg) {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
