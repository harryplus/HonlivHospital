package com.honliv.honlivmall.fragment.global.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.MenuAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.GlobalContract;
import com.honliv.honlivmall.fragment.global.GlobalOfficeSelectFragment;
import com.honliv.honlivmall.listener.OnItemClickListener;
import com.honliv.honlivmall.model.global.child.MenuListModel;
import com.honliv.honlivmall.presenter.global.MenuListPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by YoKeyword on 16/2/9.
 */
public class MenuListFragment extends BaseFragment<MenuListPresenter, MenuListModel> implements GlobalContract.MenuListView {
    private static final String ARG_MENUS = "arg_menus";
    private static final String SAVE_STATE_POSITION = "save_state_position";
    private static final String TAG = "MenuListFragment";
    @BindView(R.id.recy)
    RecyclerView mRecy;
    private MenuAdapter mAdapter;

    private ArrayList<String> mMenus;
    private int mCurrentPosition = -1;

    public static MenuListFragment newInstance(ArrayList<String> menus) {
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_MENUS, menus);

        MenuListFragment fragment = new MenuListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mMenus = args.getStringArrayList(ARG_MENUS);
        }
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_menu;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mAdapter = new MenuAdapter(_mActivity);
        mRecy.setAdapter(mAdapter);
        mAdapter.setDatas(mMenus);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                showContent(position);
            }
        });

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(SAVE_STATE_POSITION);
            mAdapter.setItemChecked(mCurrentPosition);
        } else {
            mCurrentPosition = 0;
            mAdapter.setItemChecked(0);
        }
    }

    private void showContent(int position) {
        if (position == mCurrentPosition) {
            return;
        }

        mCurrentPosition = position;

        mAdapter.setItemChecked(position);
        ArrayList<String> listMenus = new ArrayList<>();
        listMenus.add("销量排行" + position);
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
        ContentFragment fragment = ContentFragment.newInstance(listMenus);
        ((GlobalOfficeSelectFragment) getParentFragment()).switchContentFragment(fragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_STATE_POSITION, mCurrentPosition);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecy.setAdapter(null);
    }

    @Override
    public void showError(String msg) {

    }
}
