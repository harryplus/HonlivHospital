package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthHelpDetailPresenter extends FifthContract.FifthHelpDetailPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceHelpList(int helpId) {
        mRxManager.add(mModel.HelpDetail( helpId).subscribe(result->{
            mView.updateView(result);
        }));
    }
}
