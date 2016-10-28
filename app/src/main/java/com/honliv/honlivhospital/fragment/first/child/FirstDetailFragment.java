package com.honliv.honlivhospital.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class FirstDetailFragment extends BaseFragment {

    public static FirstDetailFragment newInstance() {

        Bundle args = new Bundle();

        FirstDetailFragment fragment = new FirstDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_detail, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
