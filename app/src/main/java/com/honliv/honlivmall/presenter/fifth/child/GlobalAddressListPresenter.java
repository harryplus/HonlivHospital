package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.contract.GlobalContract;

/**
 * Created by Rodin on 2016/11/16.
 */
public class GlobalAddressListPresenter extends GlobalContract.GlobalAddressListPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceProductList(int userId, int pageIndex, int pageNum) {
        mRxManager.add(mModel.addresslist(userId,pageIndex,pageNum).subscribe(result -> {
            mView.updateServiceProductList(result);
        }, e -> mView.showError(e.toString())));
    }
}
