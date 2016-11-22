package com.honliv.honlivmall.fragment.global;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.GlobalContract;
import com.honliv.honlivmall.model.global.GlobalFindModel;
import com.honliv.honlivmall.model.global.GlobalRegisterModel;
import com.honliv.honlivmall.presenter.global.GlobalFindPresenter;
import com.honliv.honlivmall.presenter.global.GlobalRegisterPresenter;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by YoKeyword on 16/2/9.
 */
public class GlobalFindPwdFragment extends BaseFragment<GlobalFindPresenter, GlobalFindModel> implements GlobalContract.GlobalFindView, View.OnClickListener {
    @BindView(R.id.backTv)
    TextView backTv;

    public static GlobalFindPwdFragment newInstance() {
        Bundle args = new Bundle();
        GlobalFindPwdFragment fragment = new GlobalFindPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_global_findpwd;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        backTv.setOnClickListener(this);
    }

    @Override
    public void showError(String msg) {

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
