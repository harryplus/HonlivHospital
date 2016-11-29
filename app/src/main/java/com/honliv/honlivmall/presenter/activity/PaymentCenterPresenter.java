package com.honliv.honlivmall.presenter.activity;

import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.ActivityContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rodin on 2016/11/21.
 */
public class PaymentCenterPresenter extends ActivityContract.PaymentCenterPresenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getServiceTotalPrice(int proSaleId, int groupBuyId, List<HashMap<String, Object>> productList) {
        mRxManager.add(mModel.getServiceTotalPrice(proSaleId, groupBuyId, productList).subscribe(result -> {
            mView.updateServiceTotalPrice(result);
        }, e -> mView.showError(e.toString())));
    }

    @Override
    public void getServiceFreight(int id, int shipId, List<HashMap<String, Object>> productJsobList, int i) {
        mRxManager.add(mModel.getServiceFreight(id, shipId, productJsobList, i).subscribe(result -> {
            mView.updateServiceFreight(result);
        }, e -> mView.showError(e.toString())));
    }

    @Override
    public void getServiceGifInfo(List<HashMap<String, Object>> productList, float couponPrice) {
        mRxManager.add(mModel.getServiceGifInfo(productList, couponPrice).subscribe(result -> {
            mView.updateServiceGifInfo(result);
        }, e -> mView.showError(e.toString())));
    }

    @Override
    public void getServiceAddressList(int arg, int arg2) {
        mRxManager.add(mModel.getServiceAddressList(arg, arg2).subscribe(result -> {
            mView.updateServiceAddressList(result);
        }, e -> mView.showError(e.toString())));
    }

    @Override
    public void getServicePayList() {
        mRxManager.add(mModel.getServicePayList().subscribe(result -> {
            mView.updateServicePayList(result);
        }, e -> mView.showError(e.toString())));
    }

    @Override
    public void getCunPonListInfos(int userid, List<HashMap<String, Object>> productJsobList, ArrayList<Product> currentProductList) {
        if (productJsobList == null) {
            productJsobList = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> jsobj;
            for (int i = 0; i < currentProductList.size(); i++) {
                Product product = currentProductList.get(i);
                jsobj = new HashMap<String, Object>();
                jsobj.put("SKU", product.getOpenSkuStr());
                jsobj.put("Count", product.getNumber());
                productJsobList.add(jsobj);
            }
        }
        mRxManager.add(mModel.GetCunpons(userid, productJsobList).subscribe(result -> {
            mView.updateCunPonListInfos(result);
        }, e -> mView.showError(e.toString())));
    }
}
