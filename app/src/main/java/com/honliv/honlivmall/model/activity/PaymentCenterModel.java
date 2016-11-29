package com.honliv.honlivmall.model.activity;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.bean.DefaultParas;
import com.honliv.honlivmall.bean.GifInfo;
import com.honliv.honlivmall.bean.PayShip;
import com.honliv.honlivmall.contract.ActivityContract;
import com.honliv.honlivmall.engine.AddressPostUtils;
import com.honliv.honlivmall.engine.CategoryPostUtils;
import com.honliv.honlivmall.engine.HomePostUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Rodin on 2016/11/21.
 */
public class PaymentCenterModel implements ActivityContract.PaymentCenterModel {
    @Override
    public Observable<Float> getServiceTotalPrice(int proSaleId, int groupBuyId, List<HashMap<String,Object>> productList) {
        Observable<Float> result = CategoryPostUtils.GetTotalPrice(GloableParams.USERID, proSaleId, groupBuyId,
                productList);
        return result;
    }

    @Override
    public Observable<Float> getServiceFreight(int shipId, int shipTypeId, List<HashMap<String,Object>> productJsobList, float allCouponMoney) {
        Observable<Float> result = CategoryPostUtils.GetFreight(GloableParams.USERID, shipId, shipTypeId,
                productJsobList, allCouponMoney);
        return result;
    }

    @Override
    public Observable<GifInfo> getServiceGifInfo(List<HashMap<String,Object>> productList, float couponPrice) {
        Observable<GifInfo> result = CategoryPostUtils.GetGiftInfo(GloableParams.USERID, productList, couponPrice);
        return result;
    }

    @Override
    public Observable<List<AddressInfo>> getServiceAddressList(int pageIndex, int pageNum) {
        Observable<List<AddressInfo>> result = AddressPostUtils.addresslist(GloableParams.USERID,  pageIndex,  pageNum);
        return result;
    }

    @Override
    public Observable<List<PayShip>> getServicePayList() {
        Observable<List<PayShip>> result = AddressPostUtils.GetPayList();
        return result;
    }

    @Override
    public Observable<CouponInfo> GetCunpons(int userid, List<HashMap<String, Object>> productJsobList) {
        Observable<CouponInfo> result = CategoryPostUtils.GetCunpons(userid,productJsobList);
        return result;
    }
}
