package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.AddressPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthAddressListModel implements FifthContract.FifthAddressListModel  {
    @Override
    public Observable<List<AddressInfo>> addresslist(int userId, int pageIndex, int pageNum) {
        Observable<List<AddressInfo>> result = AddressPostUtils.addresslist(userId,pageIndex,pageNum);
        return result;
    }
}
