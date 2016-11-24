package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.BaseActivity;
import com.honliv.honlivmall.activity.MainActivity;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.fragment.global.GlobalLoginFragment;
import com.honliv.honlivmall.model.fifth.child.FifthEditPersonInfoModel;
import com.honliv.honlivmall.model.fifth.child.FifthEditPwdModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthEditPersonInfoPresenter;
import com.honliv.honlivmall.presenter.fifth.child.FifthEditPwdPresenter;
import com.honliv.honlivmall.util.BeanFactory;
import com.honliv.honlivmall.util.PromptManager;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthEditPwdFragment extends BaseFragment<FifthEditPwdPresenter, FifthEditPwdModel> implements FifthContract.FifthEditPwdView, View.OnClickListener {
    @BindView(R.id.oldPassword_et)
    EditText oldPasswordET;
    @BindView(R.id.new_password_et)
    EditText newPasswordET;
    @BindView(R.id.config_password_et)
    EditText newConfigPasswordET;
    @BindView(R.id.callPassword)
    TextView callPassword;
    @BindView(R.id.backTv)
    TextView backTv;
    @BindView(R.id.login_text)
    TextView login_text;

    public static FifthEditPwdFragment newInstance() {
        Bundle args = new Bundle();

        FifthEditPwdFragment fragment = new FifthEditPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_changepassword;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        callPassword.setOnClickListener(this);
        backTv.setOnClickListener(this);
        login_text.setOnClickListener(this);
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
            case R.id.callPassword:
                start(FifthFindPwdFragment.newInstance());
                break;
            case R.id.backTv:
                pop();
                break;
            case R.id.login_text:
                String oldPasswordStr = oldPasswordET.getText().toString().trim();
                if (StringUtils.isBlank(oldPasswordStr)) {
                    showToast("原密码不能为空");
                    return;
                }

                String newPasswordStr = newPasswordET.getText().toString().trim();
                if (StringUtils.isBlank(newPasswordStr)) {
                    showToast("新密码不能为空");
                    return;
                }
                if (newPasswordStr.length() < 6) {
                    showToast("密码长度至少为6位");
                    return;
                }

                String configPasswordStr = newConfigPasswordET.getText().toString().trim();
                if (StringUtils.isBlank(configPasswordStr)) {
                    showToast("确认密码不能为空");
                    return;
                }

                if (!configPasswordStr.equals(newPasswordStr)) {
                    showToast("两次新密码输入不一致");
                    return;
                }
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(GloableParams.USERID);
                userInfo.setPassword(oldPasswordStr);
                userInfo.setNewpassword(newPasswordStr);
                mPresenter.updateServicePassword(userInfo);
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    @Override
    public void updateView(Boolean result) {
        if (result != null) {
            //有返回东西 ,解析出来数据，设置给屏幕
            boolean isSuccess = (Boolean) result;
            if (isSuccess) {
                showToast("修改成功！！！");
                GloableParams.toCloseActivity.finish();
                GloableParams.toCloseActivity = null;
                GloableParams.USERID = -1;
                DbUtils db = DbUtils.create(getContext());
                try {
                    db.dropTable(UserInfo.class);
                } catch (DbException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                initShopCarNumber();
                pop();
                start(GlobalLoginFragment.newInstance(new Bundle()));
            } else {//获取失败
                showToast("修改失败！！！");
            }
        }
    }
}
