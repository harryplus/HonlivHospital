package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.bean.Help;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;

import java.util.List;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthHelpListPresenter extends FifthContract.FifthHelpListPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceHelpList(int i) {
        mRxManager.add(mModel.HelpList(i).subscribe(result->{
            mView.updateView(result);
        }));
    }
}
