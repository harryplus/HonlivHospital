package com.honliv.honlivhospital.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.base.BaseLazyMainFragment;
import com.honliv.honlivhospital.contract.FourthContract;
import com.honliv.honlivhospital.model.fourth.child.FourthAddCardModel;
import com.honliv.honlivhospital.presenter.fourth.child.FourthAddCardPresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthAddCardFragment extends BaseFragment<FourthAddCardPresenter, FourthAddCardModel> implements BaseLazyMainFragment.OnBackToFirstListener, FourthContract.FourthAddCardView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static FourthAddCardFragment newInstance() {

        Bundle args = new Bundle();

        FourthAddCardFragment fragment = new FourthAddCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fourth_add_card;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mToolbar.setTitle(getString(R.string.text_add_card));
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
