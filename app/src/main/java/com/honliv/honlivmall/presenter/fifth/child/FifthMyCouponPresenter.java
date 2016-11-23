package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthMyCouponPresenter extends FifthContract.FifthMyCouponPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceCoupon2(int userId, int start, int i, int i1) {
        mRxManager.add(mModel.getServiceCouponInfo( userId,start,i,i1).subscribe(result->{
            mView.updateView2(result);
        }));
    }

    @Override
    public void getServiceCoupon1(int userId, int start, int i, int i1) {
        mRxManager.add(mModel.getServiceCouponInfo( userId,start,i,i1).subscribe(result->{
            mView.updateView1(result);
        }));
    }
}
