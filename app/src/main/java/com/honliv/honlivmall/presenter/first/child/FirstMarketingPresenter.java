package com.honliv.honlivmall.presenter.first.child;

import com.honliv.honlivmall.contract.FirstContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FirstMarketingPresenter extends FirstContract.FirstMarketingPresenter {
    private static final String TAG = "FirstMarketingPresenter";

    @Override
    public void onStart() {

    }

    @Override
    public void getServiceProductLS() {
        mRxManager.add(mModel.CountDownList().subscribe(result -> {
            mView.updateProductLS(result);
        },e->mView.showError(e.toString())));
    }
}
