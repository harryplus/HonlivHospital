package com.honliv.honlivmall.presenter.fifth.child;

import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.contract.FifthContract;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthAddAddressPresenter extends FifthContract.FifthAddAddressPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void initSpinner1(int i) {
        mRxManager.add(mModel.initSpinner1(i).subscribe(result -> {
            mView.updateView(result);
        }, e -> mView.showError(e.toString())));
    }

    @Override
    public void initSpinner2(int pcode) {
        mRxManager.add(mModel.initSpinner2(pcode).subscribe(result -> {
            mView.updateView2(result);
        }, e -> mView.showError(e.toString())));
    }

    @Override
    public void getServiceProductList(AddressInfo addressPreInfo, AddressInfo addressInfo, boolean b) {
        mRxManager.add(mModel.getServiceProductList(addressPreInfo, addressInfo, b).subscribe(result -> {
            mView.updateServiceProductList(result, b);
        }, e -> mView.showError(e.toString())));
    }

    @Override
    public void getServiceAddressName(int regionId) {
        mRxManager.add(mModel.getServiceAddressName(regionId).subscribe(result -> {
            mView.updateServiceAddressName(result);
        }, e -> mView.showError(e.toString())));
    }

    @Override
    public void addressDefault(int userid, int id) {
        mRxManager.add(mModel.SetAddressDefault(userid,id).subscribe(result -> {
            mView.updateAddressDefault(result);
        }, e -> mView.showError(e.toString())));
    }
}
