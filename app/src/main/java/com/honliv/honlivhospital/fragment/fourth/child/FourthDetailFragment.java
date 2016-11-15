package com.honliv.honlivhospital.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.base.BaseLazyMainFragment;
import com.honliv.honlivhospital.contract.FourthContract;
import com.honliv.honlivhospital.model.fourth.child.FourthDetailModel;
import com.honliv.honlivhospital.presenter.fourth.child.FourthDetailPresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthDetailFragment extends BaseFragment<FourthDetailPresenter,FourthDetailModel> implements BaseLazyMainFragment.OnBackToFirstListener,FourthContract.FourthDetailView {
  @BindView(R.id.toolbar)
  Toolbar mToolbar;

    public static FourthDetailFragment newInstance() {

        Bundle args = new Bundle();

        FourthDetailFragment fragment = new FourthDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fourth_detail;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mToolbar.setTitle(getString(R.string.text_complete_imfo));
        initToolbarNav(mToolbar);
    }

    @Override
    public void onBackToFirstFragment() {
        _mActivity.onBackPressed();
    }

    @Override
    public void showError(String msg) {

    }
}
