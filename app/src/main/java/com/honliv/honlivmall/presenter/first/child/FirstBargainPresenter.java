package com.honliv.honlivmall.presenter.first.child;

import com.honliv.honlivmall.contract.FirstContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FirstBargainPresenter extends FirstContract.FirstBargainPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceProductLS() {
        mRxManager.add(mModel.getServiceProductLS().subscribe(result->{
            mView.updateProductLS(result);
        },e->mView.showError(e.toString())));
    }
}
