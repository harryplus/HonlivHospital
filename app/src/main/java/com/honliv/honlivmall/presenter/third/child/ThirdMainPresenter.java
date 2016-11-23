package com.honliv.honlivmall.presenter.third.child;

import android.util.Log;

import com.honliv.honlivmall.contract.ThirdContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class ThirdMainPresenter extends   ThirdContract. ThirdMainPresenter {
    private static final String TAG = "ThirdMainPresenter";

    @Override
    public void onStart() {

    }

    @Override
    public void getServiceCategoryList() {
        try {
            mRxManager.add(mModel.getServiceCategoryList().subscribe(result->{
                mView.updateView(result);
            }));
        } catch (Exception e) {
            Log.i(TAG,e.toString());
        }
    }
}
