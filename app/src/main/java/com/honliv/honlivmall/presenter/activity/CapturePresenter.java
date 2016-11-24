package com.honliv.honlivmall.presenter.activity;

import com.honliv.honlivmall.contract.ActivityContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class CapturePresenter extends ActivityContract.CapturePresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getOrderId(String contentStr) {
        mRxManager.add(mModel.GetOrder( contentStr).subscribe(result->{
            mView.updateView(result);
        }));
    }
}
