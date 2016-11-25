package com.honliv.honlivmall.fragment.fifth.child;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthFavoriteModel;
import com.honliv.honlivmall.model.fifth.child.FifthFindPwdModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthFavoritePresenter;
import com.honliv.honlivmall.presenter.fifth.child.FifthFindPwdPresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthFindPwdFragment extends BaseFragment<FifthFindPwdPresenter, FifthFindPwdModel> implements FifthContract.FifthFindPwdView, View.OnClickListener {

    @BindView(R.id.servicesphoneTV)
    TextView servicesphoneTV;
    @BindView(R.id.findPassword)
    TextView findPassword;
    @BindView(R.id.backTv)
    TextView backTv;
    String phone;

    public static FifthFindPwdFragment newInstance() {
        Bundle args = new Bundle();

        FifthFindPwdFragment fragment = new FifthFindPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_find_password;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        findPassword.setOnClickListener(this);
        phone = sp.getString("phone", "4008888 8888");
        servicesphoneTV.setText(phone);
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
            case R.id.findPassword:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);//拨号界面，不是马上打电话
                intent.setData(Uri.parse("tel:" + phone));

                startActivity(intent);
                pop();
                break;
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
