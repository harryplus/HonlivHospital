package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthHomePresenter extends FifthContract.FifthHomePresenter  {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceUserInfo(int userId) {
        mRxManager.add(mModel.getServiceUserInfo( userId).subscribe(result->{
            mView.updateView(result);
        }));
    }

    @Override
    public void getServiceLogOut(String s) {
        mRxManager.add(mModel.getServiceLogOut(s).subscribe(result->{
            mView.updateLogOutView(result);
        }));
    }
}
