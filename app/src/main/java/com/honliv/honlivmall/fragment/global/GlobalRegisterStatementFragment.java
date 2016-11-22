package com.honliv.honlivmall.fragment.global;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.GlobalContract;
import com.honliv.honlivmall.model.global.GlobalRegisterStatementModel;
import com.honliv.honlivmall.presenter.global.GlobalRegisterStatementPresenter;

import butterknife.BindView;

/**
 * Created by YoKeyword on 16/2/9.
 */
public class GlobalRegisterStatementFragment extends BaseFragment<GlobalRegisterStatementPresenter, GlobalRegisterStatementModel> implements GlobalContract.GlobalRegisterStatementView, View.OnClickListener {
    @BindView(R.id.registState_WV)
    WebView registStateWV;
    @BindView(R.id.backTv)
    TextView backTv;

    public static GlobalRegisterStatementFragment newInstance() {

        Bundle args = new Bundle();

        GlobalRegisterStatementFragment fragment = new GlobalRegisterStatementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_global_registerstatement;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        backTv.setOnClickListener(this);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void initData() {
        mPresenter.getServiceStateMent();
    }

    @Override
    public void updateView(String result) {
        if (result != null) {
            registStateWV.loadDataWithBaseURL(null, result, "text/html", "utf-8", null);
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backTv:
                pop();
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }
}
