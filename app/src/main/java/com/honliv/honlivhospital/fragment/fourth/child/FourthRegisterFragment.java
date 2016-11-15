package com.honliv.honlivhospital.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.base.BaseLazyMainFragment;
import com.honliv.honlivhospital.contract.FourthContract;
import com.honliv.honlivhospital.domain.UserBean;
import com.honliv.honlivhospital.model.fourth.child.FourthRegisterModel;
import com.honliv.honlivhospital.presenter.fourth.child.FourthRegisterPresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthRegisterFragment extends BaseFragment<FourthRegisterPresenter, FourthRegisterModel> implements BaseLazyMainFragment.OnBackToFirstListener, View.OnClickListener, FourthContract.FourthRegisterView {
    private static final String TAG = "FourthRegisterFragment";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.et_cellphone)
    EditText et_cellphone;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.et_note)
    EditText et_note;
    @BindView(R.id.bt_send_note)
    Button bt_send_note;

    public static FourthRegisterFragment newInstance() {
        Bundle args = new Bundle();
        FourthRegisterFragment fragment = new FourthRegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fourth_register;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        bt_send_note.setOnClickListener(this);
        register.setOnClickListener(this);

        mToolbar.setTitle(getString(R.string.title_register));

        initToolbarNav(mToolbar);
    }

    @Override
    public void onBackToFirstFragment() {
        _mActivity.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                UserBean bean = new UserBean();
                String vCode = et_note.getText().toString();
                mPresenter.register(bean, vCode);
                break;
            case R.id.bt_send_note:
                mPresenter.sendNote(et_cellphone.getText().toString());
                break;
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public boolean onBackPressedSupport() {
//        loadRootFragment(R.id.fl_first_container, FirstHomeFragment.newInstance());
//        _mBackToFirstListener.onBackToFirstFragment();
//        _mActivity.onBackPressed();
        return true;
    }
}
