package com.honliv.honlivmall.presenter.fifth.child;

import android.content.Context;

import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthEditPersonInfoPresenter extends FifthContract.FifthEditPersonInfoPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServicePersonal(Context context) {
        mRxManager.add(mModel.getServicePersonal( context).subscribe(result->{
            mView.updateServicePersonal(result);
        }));
    }

    @Override
    public void updateUserInfo(UserInfo info) {
        mRxManager.add(mModel.updateUserInfo( info).subscribe(result->{
            mView.updateUserInfoView(result);
        }));
    }
}
