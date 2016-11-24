package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthMyOrderDetailPresenter extends FifthContract.FifthMyOrderDetailPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceOrderDetail(int userid, int orderId) {
        mRxManager.add(mModel.OrderDetail(userid,orderId).subscribe(result->{
            mView.updateServiceOrderDetail(result);
        },e->mView.showError(e.toString())));
    }

    @Override
    public void getServiceCancelOrder(int userid, int orderId) {
        mRxManager.add(mModel.CancelOrder(userid,orderId).subscribe(result->{
            mView.updateServiceCancelOrder(result);
        },e->mView.showError(e.toString())));
    }
}
