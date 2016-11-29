package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthFavoritePresenter extends FifthContract.FifthFavoritePresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceCancelFav(int userid, int favId, int position) {
        mRxManager.add(mModel.CancelFav(userid, favId).subscribe(result -> {
            mView.updateServiceCancelFav(result,position);
        }, e -> mView.showError(e.toString())));
    }

    @Override
    public void getServiceFavInfo(int userid, int start, int i) {
        mRxManager.add(mModel.updateServiceFavInfo(userid, start, i).subscribe(result -> {
            mView.updateServiceFavInfo(result);
        }, e -> mView.showError(e.toString())));
    }
}
