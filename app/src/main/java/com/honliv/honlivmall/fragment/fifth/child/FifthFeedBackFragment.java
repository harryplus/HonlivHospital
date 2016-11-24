package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthFeedBackModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthFeedBackPresenter;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthFeedBackFragment extends BaseFragment<FifthFeedBackPresenter, FifthFeedBackModel> implements FifthContract.FifthFeedBackView, View.OnClickListener {
    @BindView(R.id.backTv)
    TextView backTv;
    @BindView(R.id.submitTv)
    TextView submitTv;
    @BindView(R.id.contentEt)
    EditText contentEt;
    @BindView(R.id.emailEt)
    EditText emailEt;
    @BindView(R.id.mobileEt)
    EditText mobileEt;

    String contentEtString;
    String emailEtStr;
    String mobilePhoneStr;

    public static FifthFeedBackFragment newInstance() {
        Bundle args = new Bundle();

        FifthFeedBackFragment fragment = new FifthFeedBackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_feedback;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        backTv.setOnClickListener(this);
        submitTv.setOnClickListener(this);
    }

    @Override
    public void showError(String msg) {

    }


    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backTv:
                pop();
                break;
            case R.id.submitTv:
                // String contentEtString = contentEt.getText().toString().trim();
                submitOk();// 提交按钮
                break;
            default:
                break;
        }
    }

    /**
     * 提交
     */
    void submitOk() {
        contentEtString = contentEt.getText().toString().trim();
        if (StringUtils.isBlank(contentEtString)) {
            showToast("提交内容不能为空");
            return;
        } else if (contentEtString.length() < 10) {
            showToast("提交内容太少");
            return;
        }

        emailEtStr = emailEt.getText().toString().trim();

        mobilePhoneStr = mobileEt.getText().toString().trim();
        if (StringUtils.isBlank(mobilePhoneStr)) {
            showToast("手机号不能为空");
            return;
        }
        // 这里要加上一个匹配手机号的格式。。String regex = "1[3458][0-9]{9}";
//		String regPhone = "1[0-9]{10}"; // 定义正则  
        String regPhone = "^1([38][0-9]|4[57]|5[^4]|7[0678]|)\\d{8}$";
        boolean flagPhone = mobilePhoneStr.matches(regPhone);
        if (!flagPhone) {
            showToast("请填写正确的手机");
            return;
        }
        if (GloableParams.USERID < 0) {
            showToast("登录状态错误,请你登录!");
            return;
        }
        // 走到这里，说明上面输入正确。可以提交
        mPresenter.getServiceFeedBack(GloableParams.USERID, mobilePhoneStr, emailEtStr + "", contentEtString);
    }

    @Override
    public void updateView(Boolean result) {
        if (result != null) {
            Boolean isSuccess = (Boolean) result;
            if (isSuccess) {
                showToast("反馈成功");
                pop();
            } else {
                showToast("反馈失败");
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }
}
