package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthEditPwdPresenter extends FifthContract.FifthEditPwdPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void updateServicePassword(UserInfo userInfo) {
        mRxManager.add(mModel.updateServicePassword(userInfo).subscribe(result->{
            mView.updateView(result);
        }));
    }
}
