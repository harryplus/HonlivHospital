package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthMyHxtPresenter extends FifthContract.FifthMyHxtPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getData() {
        mRxManager.add(mModel.getData().subscribe(result->{
            mView.updateView(result);
        },e->mView.showError(e.toString())));
    }
}
