package com.honliv.honlivhospital.fragment.global.child;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.adapter.ContentAdapter;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.contract.GlobalContract;
import com.honliv.honlivhospital.listener.OnItemClickListener;
import com.honliv.honlivhospital.model.global.child.ContentModel;
import com.honliv.honlivhospital.presenter.global.ContentPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by YoKeyword on 16/2/9.
 */
public class ContentFragment extends BaseFragment<ContentPresenter, ContentModel> implements GlobalContract.ContentView {
    private static final String ARG_MENUS = "arg_menus";
    private static final String SAVE_STATE_POSITION = "save_state_position";
    @BindView(R.id.recy)
    RecyclerView mRecy;
    private ContentAdapter mAdapter;

    private ArrayList<String> mSubMenus;
    private int mCurrentPosition = -1;

    public static ContentFragment newInstance(ArrayList<String> menus) {

        Bundle args = new Bundle();
        args.putStringArrayList(ARG_MENUS, menus);

        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mSubMenus = args.getStringArrayList(ARG_MENUS);
        }
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_content;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mAdapter = new ContentAdapter(_mActivity);
        mRecy.setAdapter(mAdapter);
        mAdapter.setDatas(mSubMenus);

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
