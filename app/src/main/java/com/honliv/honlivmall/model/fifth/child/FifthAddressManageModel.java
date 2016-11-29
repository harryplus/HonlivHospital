package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.AddressPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthAddressManageModel implements FifthContract.FifthAddressManageModel {
    @Override
    public Observable<List<AddressInfo>> getServiceAddressList() {
        Observable<List<AddressInfo>> result = AddressPostUtils.addresslist();
        return result;
    }
}
