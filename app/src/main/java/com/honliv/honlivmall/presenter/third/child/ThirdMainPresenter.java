package com.honliv.honlivmall.presenter.third.child;

import com.honliv.honlivmall.contract.ThirdContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class ThirdMainPresenter extends   ThirdContract. ThirdMainPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceCategoryList() {
        mRxManager.add(mModel.getServiceCategoryList().subscribe(result->{
            mView.updateView(result);
        }));
    }
}
