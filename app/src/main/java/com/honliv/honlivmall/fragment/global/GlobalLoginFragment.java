package com.honliv.honlivmall.fragment.global;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.MainActivity;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.GlobalContract;
import com.honliv.honlivmall.fragment.first.child.FirstHomeFragment;
import com.honliv.honlivmall.model.global.GlobalLoginModel;
import com.honliv.honlivmall.presenter.global.GlobalLoginPresenter;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/29.
 */
public class GlobalLoginFragment extends BaseFragment<GlobalLoginPresenter, GlobalLoginModel> implements SwipeRefreshLayout.OnRefreshListener, GlobalContract.GlobalLoginView, View.OnClickListener {
    @BindView(R.id.backTv)
    TextView backTv;
    @BindView(R.id.username_et)
    EditText username_et;
    @BindView(R.id.password_et)
    EditText password_et;
    @BindView(R.id.login_register)
    TextView login_register;
    @BindView(R.id.login_text)
    TextView login_text;
    @BindView(R.id.forgin_password_TV)
    TextView forgin_password_TV;
    private int position = -1;
    private Class fragment = null;


    public static GlobalLoginFragment newInstance(Bundle args) {
        GlobalLoginFragment fragment = new GlobalLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_global_login;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        Bundle data = getArguments();
        position = data.getInt("position");
        fragment = (Class) data.getSerializable("fragment");
        backTv.setOnClickListener(this);
        login_register.setOnClickListener(this);
        login_text.setOnClickListener(this);
        forgin_password_TV.setOnClickListener(this);
    }

    @Override
    public void onRefresh() {

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
                ((MainActivity) getActivity()).onBackToFirstFragment();
                break;
            case R.id.login_register:
                GlobalRegisterFragment registerFragment = GlobalRegisterFragment.newInstance();
                start(registerFragment);
                break;
            case R.id.login_text:
                String username = username_et.getText().toString().trim();
                String password = password_et.getText().toString().trim();
                if (StringUtils.isBlank(username)) {
                    showToast("登录名不能为空");
                    return;
                }
                if (username.length() < 2) {
                    showToast("登录名2位以上");
                    return;
                }
                if (StringUtils.isBlank(password)) {
                    showToast("密码不能为空");
                    return;
                }
                if (password.length() < 6) {
                    showToast("密码6位以上");
                    return;
                }
                ///////到达这里，说明数据配置通过
                UserInfo userInfo = new UserInfo();
                userInfo.setUserName(username);
                userInfo.setPassword(password);
                mPresenter.getServiceLoginInfo(userInfo);
                break;
            case R.id.forgin_password_TV:
                start(GlobalFindPwdFragment.newInstance());
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        ((MainActivity) getActivity()).onBackToFirstFragment();
        return true;
    }

    @Override
    public void updateView(UserInfo result) {
        if (result != null && !TextUtils.isEmpty(result.getUserName())) {
            UserInfo userInfo2 = result;
            showToast(userInfo2.toString());
            GloableParams.USERID = userInfo2.getUserId();
            DbUtils db = DbUtils.create(getContext());
            try {
                db.save(userInfo2);
                ArrayList<Product> productlist = (ArrayList) db.findAll(Selector.from(Product.class).where("userId", "=", -100));
                if (productlist != null && productlist.size() > 0) {
                    Product dbproduct = null;
                    for (int i = 0; i < productlist.size(); i++) {
                        productlist.get(i).setUserId(GloableParams.USERID);
                        //("openSkuStr","=",product.getOpenSkuStr())
                        dbproduct = db.findFirst(Selector.from(Product.class).where("userId", "=", GloableParams.USERID).and("openSkuStr", "=", productlist.get(i).getOpenSkuStr()));
                        if (dbproduct != null) {
                            db.delete(dbproduct);
                            productlist.get(i).setNumber(dbproduct.getNumber() + productlist.get(i).getNumber());
                        }
                        db.update(productlist.get(i));
                    }
                }
                if (fragment == null || position < 0) {
                    ((MainActivity) getActivity()).onBackToFirstFragment();
                } else {
                    ((MainActivity) getActivity()).startAssignFragment(position, fragment);
                }
                pop();
            } catch (DbException e) {
                e.printStackTrace();
            }
        } else {
            showToast(getString(R.string.login_error));
        }
    }
}
