package com.honliv.honlivhospital.fragment.second;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseLazyMainFragment;
import com.honliv.honlivhospital.fragment.second.child.SecondMainFragment;

/**
 * Created by Rodin on 2016/10/25.
 */
public class SecondFragment extends BaseLazyMainFragment {
    public static SecondFragment newInstance() {

        Bundle args = new Bundle();

        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        return view;
    }

//    @Override
//    protected void initLazyView(@Nullable Bundle savedInstanceState) {
//        if (savedInstanceState == null) {
//            loadRootFragment(R.id.fl_second_container, FirstHomeFragment.newInstance());
//        }
//    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadFragment();
        } else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(SecondMainFragment.class) == null) {
                loadFragment();
            }
        }

//        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar);
//        mToolbar.setTitle("我的");
//        initToolbarMenu(mToolbar);
    }

    private void loadFragment() {
        loadRootFragment(R.id.fl_second_container, SecondMainFragment.newInstance());
//        loadRootFragment(R.id.fl_fourth_container_lower, MeFragment.newInstance());
    }

    public void onBackToFirstFragment() {
        _mBackToFirstListener.onBackToFirstFragment();
    }
}
