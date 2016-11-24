package com.honliv.honlivmall.presenter.first.child;

import com.honliv.honlivmall.contract.FirstContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FirstProductDetailPresenter extends FirstContract.FirstProductDetailPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceProductDetail(int pId) {
        mRxManager.add(mModel.getServiceProductDetail(pId).subscribe(result->{
            mView.updateView(result);
        }));
    }

    @Override
    public void addProductFav(int pid) {
        mRxManager.add(mModel.addProductFav(pid).subscribe(result->{
            mView.updateAddProductFavView(result);
        }));
    }
}
