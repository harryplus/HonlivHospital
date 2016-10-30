package com.honliv.honlivhospital.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.base.BaseLazyMainFragment;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthLoginFragment extends BaseFragment implements BaseLazyMainFragment.OnBackToFirstListener, View.OnClickListener {
    private Button login;
    private Button register;

    public static FourthLoginFragment newInstance() {

        Bundle args = new Bundle();

        FourthLoginFragment fragment = new FourthLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth_login, container, false);
        initView(view);
        initMotion();
        return view;
    }

    private void initMotion() {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void initView(View view) {
        login = (Button) view.findViewById(R.id.login);
        register = (Button) view.findViewById(R.id.register);
    }

    @Override
    public void onBackToFirstFragment() {
        _mActivity.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                FourthPersonFragment personFragment = FourthPersonFragment.newInstance();
                start(personFragment);
                break;
            case R.id.register:
                FourthRegisterFragment registerFragment = FourthRegisterFragment.newInstance();
                start(registerFragment);
                break;
        }
    }
}
