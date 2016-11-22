package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthEditPersonInfoModel;
import com.honliv.honlivmall.model.fifth.child.FifthMyCouponModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthEditPersonInfoPresenter;
import com.honliv.honlivmall.presenter.fifth.child.FifthMyCouponPresenter;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthMyCouponFragment extends BaseFragment<FifthMyCouponPresenter, FifthMyCouponModel> implements FifthContract.FifthMyCouponView, View.OnClickListener {

    public static FifthMyCouponFragment newInstance() {
        Bundle args = new Bundle();

        FifthMyCouponFragment fragment = new FifthMyCouponFragment();
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
