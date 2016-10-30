package com.honliv.honlivhospital.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.base.BaseFragment;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthPersonFragment extends BaseFragment implements View.OnClickListener {
    private View setting_base_info;
    private View setting_change_pwd;
    private View setting_card;
    private View setting_book;
    private View setting_about;
    private View setting_recommend;
    private View setting_logout;

    public static FourthPersonFragment newInstance() {

        Bundle args = new Bundle();

        FourthPersonFragment fragment = new FourthPersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth_person, container, false);
        initView(view);
        initMotion();
        return view;
    }

    private void initMotion() {
        setting_base_info.setOnClickListener(this);
        setting_change_pwd.setOnClickListener(this);
        setting_card.setOnClickListener(this);
        setting_book.setOnClickListener(this);
        setting_about.setOnClickListener(this);
        setting_recommend.setOnClickListener(this);
        setting_logout.setOnClickListener(this);
    }

    private void initView(View view) {
        setting_base_info = view.findViewById(R.id.setting_base_info);
        setting_change_pwd = view.findViewById(R.id.setting_change_pwd);
        setting_card = view.findViewById(R.id.setting_card);
        setting_book = view.findViewById(R.id.setting_book);
        setting_about = view.findViewById(R.id.setting_about);
        setting_recommend = view.findViewById(R.id.setting_recommend);
        setting_logout = view.findViewById(R.id.setting_logout);
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
}
