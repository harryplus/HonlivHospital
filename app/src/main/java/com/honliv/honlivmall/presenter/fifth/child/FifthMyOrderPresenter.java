package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthMyOrderPresenter extends FifthContract.FifthMyOrderPresenter {
    @Override
    public void onStart() {
    }

    @Override
    public void getServiceOrderList(int userId, int page, int pageNum) {
        mRxManager.add(mModel.OrderList(userId,page,pageNum).subscribe(result->{
            mView.updateView(result);
        },e->mView.showError(e.toString())));
    }
}
