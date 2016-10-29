package com.honliv.honlivhospital.fragment.global;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.fragment.global.child.ContentFragment;
import com.honliv.honlivhospital.fragment.global.child.MenuListFragment;

import java.util.ArrayList;

/**
 * Created by Rodin on 2016/10/29.
 */
public class GlobalOfficeSelectFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private Toolbar mToolbar;

    public static GlobalOfficeSelectFragment newInstance() {

        Bundle args = new Bundle();

        GlobalOfficeSelectFragment fragment = new GlobalOfficeSelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_global_office_select, container, false);
        initView(view,savedInstanceState);
        return view;
    }

    private void initView(View view, Bundle savedInstanceState) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.office_select));
        initToolbarNav(mToolbar);
        if (savedInstanceState == null) {
            ArrayList<String> listMenus = new ArrayList<>();
            listMenus.add("销量排行");
            listMenus.add("当季特选");
            listMenus.add("炒菜");
            listMenus.add("汤面类");
            listMenus.add("煲类");
            listMenus.add("汤");
            listMenus.add("小菜");
            listMenus.add("酒水饮料");
            listMenus.add("盖浇饭类");
            listMenus.add("炒面类");
            listMenus.add("拉面类");
            listMenus.add("盖浇面类");
            listMenus.add("特色菜");
            listMenus.add("加料");
            listMenus.add("馄饨类");
            listMenus.add("其他");

            MenuListFragment menuListFragment = MenuListFragment.newInstance(listMenus);
            loadRootFragment(R.id.fl_list_container, menuListFragment);
            replaceLoadRootFragment(R.id.fl_content_container, ContentFragment.newInstance("销量排行"), false);
        }
    }

    @Override
    public void onRefresh() {

    }
}
