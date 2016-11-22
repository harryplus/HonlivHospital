package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.AddressPostUtils;
import com.honliv.honlivmall.engine.UserPostUtils;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthAddressManageModel implements FifthContract.FifthAddressManageModel {
    @Override
    public Observable<List<AddressInfo>> getServiceProductList() {
        Observable<List<AddressInfo>> result = AddressPostUtils.getServiceProductList();
        return result;
    }
}
