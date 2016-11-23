package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthMyHxtPwdModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthMyHxtPwdPresenter;
import com.honliv.honlivmall.task.HxtBindTask;
import com.honliv.honlivmall.util.BuilderTools;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthMyHxtPwdFragment extends BaseFragment<FifthMyHxtPwdPresenter, FifthMyHxtPwdModel> implements FifthContract.FifthMyHxtPwdView, View.OnClickListener {
    @BindView(R.id.bind)
    Button bind;
    @BindView(R.id.et_cardno)
    EditText et_cardno;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    OrderInfo orderInfo = null;
    String cardNo = null;
    @BindView(R.id.title)
    TextView title;

    public static FifthMyHxtPwdFragment newInstance() {
        Bundle args = new Bundle();

        FifthMyHxtPwdFragment fragment = new FifthMyHxtPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_my_hxt_bind;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
//        orderInfo = (OrderInfo) getIntent().getSerializableExtra("orderInfo");
//        cardNo = getIntent().getStringExtra("cardNo");
        bind.setOnClickListener(this);
        title.setText(getString(R.string.text_hxt_bind));
        bind.setText(getString(R.string.text_bind));
        if (cardNo != null) {
            et_cardno.setText(cardNo);
            title.setText(getString(R.string.text_hxt_input_pwd));
            bind.setText(getString(R.string.text_pay));
        }
    }

    @Override
    public void showError(String msg) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind:
                String et_pwdText = et_pwd.getText().toString();
                if (cardNo == null) {
                    String et_cardnoText = et_cardno.getText().toString();
                    HxtBindTask task = new HxtBindTask();
                    task.setBindFace(new HxtBindTask.BindFace() {

                        @Override
                        public void bind(Boolean flag) {
                            if (flag) {
                                showToast(getString(R.string.bind_success));
                                start(FifthMyHxtFragment.newInstance());
                            } else {
                                showToast(getString(R.string.bind_fail));
                            }
                        }
                    });
                    task.execute(et_cardnoText, et_pwdText);
                } else {
                    BuilderTools.showSurePayDialog(getContext(), orderInfo, cardNo, et_pwdText);
                }
                break;
        }
    }

    @Override
    public void onStop() {
        pop();
        super.onStop();
    }
}
