package com.honliv.honlivmall.presenter.global;

import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.GlobalContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class GlobalLoginPresenter extends  GlobalContract.GlobalOfficeSelectPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceLoginInfo(UserInfo userInfo) {
        mRxManager.add(mModel.getServiceLoginInfo(userInfo).subscribe(result -> {
            mView.updateView(result);
        }));
    }
}
