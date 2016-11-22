package com.honliv.honlivmall.presenter.activity;

import com.honliv.honlivmall.contract.ActivityContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FlashPresenter  extends ActivityContract.FlashPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceDafault() {
        mRxManager.add(mModel.getServiceDafault( ).subscribe(result->{
            mView.updateView(result);
        }));
    }
}
