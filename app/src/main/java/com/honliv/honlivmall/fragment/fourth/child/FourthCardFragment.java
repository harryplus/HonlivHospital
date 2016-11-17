package com.honliv.honlivmall.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.base.BaseLazyMainFragment;
import com.honliv.honlivmall.contract.FourthContract;
import com.honliv.honlivmall.model.fourth.child.FourthCardModel;
import com.honliv.honlivmall.presenter.fourth.child.FourthCardPresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthCardFragment extends BaseFragment<FourthCardPresenter,FourthCardModel> implements BaseLazyMainFragment.OnBackToFirstListener, View.OnClickListener ,FourthContract.FourthCardView{
   @BindView(R.id.toolbar)
   Toolbar mToolbar;
    @BindView(R.id.add_card)
     View add_card;

    public static FourthCardFragment newInstance() {

        Bundle args = new Bundle();

        FourthCardFragment fragment = new FourthCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fourth_card;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mToolbar.setTitle(getString(R.string.text_complete_imfo));
        initToolbarNav(mToolbar);
        add_card.setOnClickListener(this);
    }

    @Override
    public void onBackToFirstFragment() {
        _mActivity.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_card:

                break;
        }
    }

    @Override
    public void showError(String msg) {

    }
}
