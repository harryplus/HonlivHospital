package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthFeedBackPresenter extends FifthContract.FifthFeedBackPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceFeedBack(int userId, String telephone,
                                   String email, String content) {
        mRxManager.add(mModel.LiveMessage(userId,telephone,email,content).subscribe(result->{
            mView.updateView(result);
        }));
    }
}
