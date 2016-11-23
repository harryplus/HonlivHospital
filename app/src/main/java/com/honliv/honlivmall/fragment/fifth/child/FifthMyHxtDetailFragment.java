package com.honliv.honlivmall.fragment.fifth.child;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.Button;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.HxtBean;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthMyHxtDetailModel;
import com.honliv.honlivmall.model.fifth.child.FifthMyHxtPwdModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthMyHxtDetailPresenter;
import com.honliv.honlivmall.presenter.fifth.child.FifthMyHxtPwdPresenter;
import com.honliv.honlivmall.task.HxtUnBindTask;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthMyHxtDetailFragment extends BaseFragment<FifthMyHxtDetailPresenter, FifthMyHxtDetailModel> implements FifthContract.FifthMyHxtDetailView, View.OnClickListener {
    @BindView(R.id.cardType)
    TextView cardType;
    @BindView(R.id.cardUserName)
    TextView cardUserName;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.cardNo)
    TextView cardNo;
    @BindView(R.id.bindDateTime)
    TextView bindDateTime;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.unBind)
    Button unBind;

    HxtBean hxtBean;

    public static FifthMyHxtDetailFragment newInstance() {
        Bundle args = new Bundle();

        FifthMyHxtDetailFragment fragment = new FifthMyHxtDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_myhxt_detail;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
//        hxtBean = (HxtBean) getIntent().getSerializableExtra("hxtBean");
        cardType.setText(hxtBean.getCardType());
        cardUserName.setText(hxtBean.getCardUserName());
        phone.setText(hxtBean.getPhone());
        cardNo.setText(hxtBean.getCardNo());
        bindDateTime.setText(hxtBean.getBindDateTime());
        sex.setText(hxtBean.getSex());
        unBind.setOnClickListener(this);
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
            case R.id.unBind:
                HxtUnBindTask task = new HxtUnBindTask();
                task.setListener(new HxtUnBindTask.ResultListener() {

                    @Override
                    public void result(Boolean aBoolean) {
                        if (aBoolean)
                            start(FifthMyHxtFragment.newInstance());
                        else
                            showToast("解除绑定失败");
                    }
                });
                task.execute(hxtBean.getCardNo());
                break;
        }
    }


    @Override
    public void onStop() {
        pop();
        super.onStop();
    }
}
