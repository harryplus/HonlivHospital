package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthAboutModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthAboutPresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthAboutFragment extends BaseFragment<FifthAboutPresenter, FifthAboutModel> implements FifthContract.FifthAboutView, View.OnClickListener {
@BindView(R.id.head_back_text)
TextView head_back_text;

    public static FifthAboutFragment newInstance() {
        Bundle args = new Bundle();

        FifthAboutFragment fragment = new FifthAboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_about;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        head_back_text.setOnClickListener(this);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_back_text:
                pop();
                break;
        }
    }
}
