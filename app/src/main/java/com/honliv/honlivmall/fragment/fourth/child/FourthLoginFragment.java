package com.honliv.honlivmall.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.base.BaseLazyMainFragment;
import com.honliv.honlivmall.contract.FourthContract;
import com.honliv.honlivmall.model.fourth.child.FourthLoginModel;
import com.honliv.honlivmall.presenter.fourth.child.FourthLoginPresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthLoginFragment extends BaseFragment<FourthLoginPresenter, FourthLoginModel> implements BaseLazyMainFragment.OnBackToFirstListener, View.OnClickListener, FourthContract.FourthLoginView {
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.login)
    Button login;

    public static FourthLoginFragment newInstance() {
        Bundle args = new Bundle();
        FourthLoginFragment fragment = new FourthLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fourth_login;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onBackToFirstFragment() {
        _mActivity.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
//                FourthPersonFragment personFragment = FourthPersonFragment.newInstance();
//                start(personFragment);

                break;
            case R.id.register:
                FourthRegisterFragment registerFragment = FourthRegisterFragment.newInstance();
                start(registerFragment);
                break;
        }
    }

    @Override
    public void showError(String msg) {

    }
}
