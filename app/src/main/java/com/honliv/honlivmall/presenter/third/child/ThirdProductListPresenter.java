package com.honliv.honlivmall.presenter.third.child;

import com.honliv.honlivmall.contract.ThirdContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class ThirdProductListPresenter extends   ThirdContract. ThirdProductListPresenter  {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceProductList(int categoryId, String orderBy, int page, int pageNum) {
        mRxManager.add(mModel.ProductList(categoryId,orderBy,page,pageNum).subscribe(result -> {
            mView.updateView(result);
        }));
    }
}
