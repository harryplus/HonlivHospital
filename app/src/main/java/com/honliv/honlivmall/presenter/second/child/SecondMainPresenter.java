package com.honliv.honlivmall.presenter.second.child;

import com.honliv.honlivmall.contract.SecondContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class SecondMainPresenter extends SecondContract.SecondMainPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getSeaviceSearchKeyList() {
        mRxManager.add(mModel
                .getSeaviceSearchKeyList()
                .subscribe(result -> {
                    if (result != null)
                        mView.updataSearchKeyList(result);
                }));
    }
}
