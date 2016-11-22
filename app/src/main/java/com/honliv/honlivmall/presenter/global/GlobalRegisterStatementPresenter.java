package com.honliv.honlivmall.presenter.global;

import com.honliv.honlivmall.contract.GlobalContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class GlobalRegisterStatementPresenter extends GlobalContract.GlobalRegisterStatementPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceStateMent() {
        mRxManager.add(mModel.getServiceStateMent().subscribe(result -> {
            mView.updateView(result);
        }));
    }
}
