package com.honliv.honlivhospital.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.base.BaseLazyMainFragment;
import com.honliv.honlivhospital.contract.FourthContract;
import com.honliv.honlivhospital.model.fourth.child.FourthLoginModel;
import com.honliv.honlivhospital.presenter.fourth.child.FourthLoginPresenter;

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
