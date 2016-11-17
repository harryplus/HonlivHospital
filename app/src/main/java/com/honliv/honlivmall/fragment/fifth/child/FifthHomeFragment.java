package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.fragment.second.child.SecondMainFragment;
import com.honliv.honlivmall.model.fifth.child.FifthHomeModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthHomePresenter;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthHomeFragment extends BaseFragment<FifthHomePresenter, FifthHomeModel> implements FifthContract.FifthHomeView {
    private static String TAG = "FifthHomeFragment";

    public static FifthHomeFragment newInstance() {
        Log.i(TAG, "newInstance");
        Bundle args = new Bundle();

        FifthHomeFragment fragment = new FifthHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_center;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void showError(String msg) {

    }
}
