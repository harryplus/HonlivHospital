package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.contract.GlobalContract;
import com.honliv.honlivmall.engine.AddressPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class GlobalAddAddressModel implements GlobalContract.GlobalAddAddressModel {
    @Override
    public Observable<List<AddressInfo>> initSpinner1(int i) {
        Observable<List<AddressInfo>> result = AddressPostUtils.GetRegionList(i);
        return result;
    }

    @Override
    public Observable<List<AddressInfo>> initSpinner2(int pcode) {
        Observable<List<AddressInfo>> result = AddressPostUtils.GetRegionList(pcode);
        return result;
    }

    @Override
    public Observable<List<AddressInfo>> getServiceProductList(AddressInfo addressPreInfo, AddressInfo addressInfo, boolean b) {
        Observable<List<AddressInfo>> result;
        if(addressPreInfo!=null){
            //保存地址
           result = AddressPostUtils.SaveAddress(addressInfo);
        }else{
            //新增加地址
            result = AddressPostUtils.getServiceAddAddress(addressInfo);
        }
        return result;
    }

    @Override
    public Observable<String> getServiceAddressName(int regionId) {
        Observable<String> result = AddressPostUtils.GetRegionName(regionId);
        return result;
    }

    @Override
    public Observable<Boolean> SetAddressDefault(int userid, int id) {
        Observable<Boolean> result = AddressPostUtils.SetAddressDefault(userid,id);
        return result;
    }
}
