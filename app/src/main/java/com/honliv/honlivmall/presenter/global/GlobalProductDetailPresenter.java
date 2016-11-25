package com.honliv.honlivmall.presenter.global;

import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.contract.GlobalContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class GlobalProductDetailPresenter extends GlobalContract.GlobalProductDetailPresenter {
    private static final String TAG = "GlobalProductDetailPresenter";

    @Override
    public void onStart() {
    }

    @Override
    public void getServiceProductDetail(int pId) {
        mRxManager.add(mModel.getServiceProductDetail(pId).subscribe(result -> {
            mView.updateView(result);
        }, e -> mView.showError(e.toString())));
    }

    @Override
    public void addProductFav(int pid) {
        mRxManager.add(mModel.addProductFav(pid).subscribe(result -> {
            mView.updateAddProductFavView(result);
        }, e -> mView.showError(e.toString())));
    }
}
