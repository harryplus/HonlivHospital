package com.honliv.honlivmall.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.FourthContract;
import com.honliv.honlivmall.model.fourth.child.FourthMainModel;
import com.honliv.honlivmall.presenter.fourth.child.FourthMainPresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthMainFragment extends BaseFragment<FourthMainPresenter, FourthMainModel> implements View.OnClickListener, FourthContract.FourthMainView {
    @BindView(R.id.login)
    TextView login;

    public static FourthMainFragment newInstance() {

        Bundle args = new Bundle();

        FourthMainFragment fragment = new FourthMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fourth_main;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                FourthLoginFragment loginFragment = FourthLoginFragment.newInstance();
                start(loginFragment);
                break;
        }
    }

    @Override
    public void showError(String msg) {

    }
}
