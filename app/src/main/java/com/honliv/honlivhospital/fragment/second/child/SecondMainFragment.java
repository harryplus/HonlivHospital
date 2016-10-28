package com.honliv.honlivhospital.fragment.second.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;

/**
 * Created by Rodin on 2016/10/26.
 */
public class SecondMainFragment extends BaseFragment {
    public static SecondMainFragment newInstance() {

        Bundle args = new Bundle();

        SecondMainFragment fragment = new SecondMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
    }
}
