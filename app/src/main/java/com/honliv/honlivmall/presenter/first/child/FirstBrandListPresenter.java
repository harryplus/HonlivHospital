package com.honliv.honlivmall.presenter.first.child;

import com.honliv.honlivmall.contract.FirstContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FirstBrandListPresenter extends  FirstContract.FirstBrandListPresenter{
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceProductLS(int brandId) {
        mRxManager.add(mModel.ProductList(brandId).subscribe(result->{
            mView.updateProductLS(result);
        }));
    }
}
