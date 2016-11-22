package com.honliv.honlivmall.presenter.global;

import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.GlobalContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class GlobalRegisterPresenter extends GlobalContract.GlobalRegisterPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceRegist(UserInfo userInfo) {
        mRxManager.add(mModel.getServiceRegist(userInfo).subscribe(result -> {
            mView.updateView(result);
        }));
    }
}
