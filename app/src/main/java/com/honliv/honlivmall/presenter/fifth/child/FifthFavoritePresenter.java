package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthFavoritePresenter  extends FifthContract.FifthFavoritePresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceCancelFav(int userid, int favId) {
        mRxManager.add(mModel.CancelFav(userid,favId).subscribe(result->{
            mView.updateServiceCancelFav(result);
        }));
    }

    @Override
    public void getServiceFavInfo(int userid, int start, int i) {
        mRxManager.add(mModel.updateServicePassword(userid,start,i).subscribe(result->{
            mView.updateServiceFavInfo(result);
        }));
    }
}
