package com.honliv.honlivhospital.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthMainFragment extends BaseFragment implements View.OnClickListener {
    private TextView login;

    public static FourthMainFragment newInstance() {

        Bundle args = new Bundle();

        FourthMainFragment fragment = new FourthMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        login = (TextView) view.findViewById(R.id.login);
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
}
