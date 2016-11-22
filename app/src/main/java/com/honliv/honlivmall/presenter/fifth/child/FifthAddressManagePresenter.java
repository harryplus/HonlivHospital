package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthAddressManagePresenter extends FifthContract.FifthAddressManagePresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceProductList() {
        mRxManager.add(mModel.getServiceProductList().subscribe(result->{
            mView.updateProductList(result);
        }));
    }
}
