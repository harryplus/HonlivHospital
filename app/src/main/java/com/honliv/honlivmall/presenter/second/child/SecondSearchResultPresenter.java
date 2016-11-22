package com.honliv.honlivmall.presenter.second.child;

import com.honliv.honlivmall.contract.SecondContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class SecondSearchResultPresenter extends SecondContract.SecondSearchResultPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getSeaviceSearchProductList(String searchKey, String orderby, int start) {
        mRxManager.add(mModel
                .getSeaviceSearchProductList(searchKey,orderby,start)
                .subscribe(result -> {
                    if (result != null)
                        mView.updataSearchProductList(result);
                }));
    }
}
