package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthSettingPresenter extends FifthContract.FifthSettingPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void checkVersion() {
        mRxManager.add(mModel.checkVersion().subscribe(result->{
            mView.updateVersion(result);
        }));
    }

    @Override
    public void getServiceLogOut(String s) {
        mRxManager.add(mModel.LogOut().subscribe(result->{
            mView.updateLogOut(result);
        }));
    }
}
