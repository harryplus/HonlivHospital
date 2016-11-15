package com.honliv.honlivhospital.fragment.global;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.contract.GlobalContract;
import com.honliv.honlivhospital.fragment.global.child.ContentFragment;
import com.honliv.honlivhospital.fragment.global.child.MenuListFragment;
import com.honliv.honlivhospital.model.global.GlobalOfficeSelectModel;
import com.honliv.honlivhospital.presenter.global.GlobalOfficeSelectPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Rodin on 2016/10/29.
 */
public class GlobalOfficeSelectFragment extends BaseFragment<GlobalOfficeSelectPresenter,GlobalOfficeSelectModel> implements SwipeRefreshLayout.OnRefreshListener , GlobalContract.GlobalOfficeSelectView{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static GlobalOfficeSelectFragment newInstance() {

        Bundle args = new Bundle();

        GlobalOfficeSelectFragment fragment = new GlobalOfficeSelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_global_office_select;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
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
            replaceLoadRootFragment(R.id.fl_content_container, ContentFragment.newInstance(listMenus), false);
        }
    }

    @Override
    public void onRefresh() {

    }

    /**
     * 替换加载 内容Fragment
     *
     * @param fragment
     */
    public void switchContentFragment(ContentFragment fragment) {
        SupportFragment contentFragment = findChildFragment(ContentFragment.class);
        if (contentFragment != null) {
            contentFragment.replaceFragment(fragment, false);
        }
    }

    @Override
    public void showError(String msg) {

    }
}
