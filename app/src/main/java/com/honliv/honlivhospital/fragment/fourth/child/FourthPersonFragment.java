package com.honliv.honlivhospital.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.contract.FourthContract;
import com.honliv.honlivhospital.model.fourth.child.FourthPersonModel;
import com.honliv.honlivhospital.presenter.fourth.child.FourthPersonPresenter;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthPersonFragment extends BaseFragment<FourthPersonPresenter,FourthPersonModel> implements View.OnClickListener,FourthContract.FourthPersonView {
   @BindView(R.id.setting_base_info)
     View setting_base_info;
    @BindView(R.id.setting_change_pwd)
     View setting_change_pwd;
    @BindView(R.id.setting_card)
     View setting_card;
    @BindView(R.id.setting_book)
     View setting_book;
    @BindView(R.id.setting_about)
     View setting_about;
    @BindView(R.id.setting_recommend)
     View setting_recommend;
    @BindView(R.id.setting_logout)
     View setting_logout;

    public static FourthPersonFragment newInstance() {

        Bundle args = new Bundle();

        FourthPersonFragment fragment = new FourthPersonFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_fourth_person;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        setting_base_info.setOnClickListener(this);
        setting_change_pwd.setOnClickListener(this);
        setting_card.setOnClickListener(this);
        setting_book.setOnClickListener(this);
        setting_about.setOnClickListener(this);
        setting_recommend.setOnClickListener(this);
        setting_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_base_info:
                FourthDetailFragment detailFragment = FourthDetailFragment.newInstance();
                start(detailFragment);
                break;
            case R.id.setting_change_pwd:
                FourthChangePwdFragment changePwdFragment = FourthChangePwdFragment.newInstance();
                start(changePwdFragment);
                break;
            case R.id.setting_card:
                FourthCardFragment cardFragment = FourthCardFragment.newInstance();
                start(cardFragment);
                break;
            case R.id.setting_book:
                FourthBookFragment bookFragment = FourthBookFragment.newInstance();
                start(bookFragment);
                break;
            case R.id.setting_about:
                break;
            case R.id.setting_recommend:
                break;
            case R.id.setting_logout:
                break;
        }
    }

    @Override
    public void showError(String msg) {

    }
}
