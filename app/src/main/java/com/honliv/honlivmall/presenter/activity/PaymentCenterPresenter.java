package com.honliv.honlivmall.presenter.activity;

import com.honliv.honlivmall.contract.ActivityContract;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Rodin on 2016/11/21.
 */
public class PaymentCenterPresenter extends ActivityContract.PaymentCenterPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceTotalPrice(int proSaleId, int groupBuyId, List<JSONObject> productList) {
        mRxManager.add(mModel.getServiceTotalPrice( proSaleId,  groupBuyId,  productList).subscribe(result->{
            mView.updateServiceTotalPrice(result);
        }));
    }

    @Override
    public void getServiceFreight(int id, int shipId, List<JSONObject> productJsobList, int i) {
        mRxManager.add(mModel.getServiceFreight(id,  shipId,  productJsobList,  i).subscribe(result->{
            mView.updateServiceFreight(result);
        }));
    }

    @Override
    public void getServiceGifInfo( List<JSONObject> productList, float couponPrice) {
        mRxManager.add(mModel.getServiceGifInfo(productList,couponPrice).subscribe(result->{
            mView.updateServiceGifInfo(result);
        }));
    }

    @Override
    public void getServiceAddressList(int arg, int arg2) {
        mRxManager.add(mModel.getServiceAddressList(arg,arg2).subscribe(result->{
            mView.updateServiceAddressList(result);
        }));
    }

    @Override
    public void getServicePayList() {
        mRxManager.add(mModel.getServicePayList().subscribe(result->{
            mView.updateServicePayList(result);
        }));
    }
}
