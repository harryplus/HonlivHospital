package com.honliv.honlivmall.model.fourth.child;

import com.honliv.honlivmall.contract.FourthContract;
import com.honliv.honlivmall.bean.BaseResult;
import com.honliv.honlivmall.bean.UserBean;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;

import java.util.HashMap;
import java.util.List;

import rx.Observable;


/**
 * Created by Rodin on 2016/11/15.
 */
public class FourthLoginModel implements FourthContract.FourthLoginModel {
    @Override
    public Observable<BaseResult<List<UserBean>>> login(UserBean bean) {
        HashMap<String,String> map=new HashMap<>();
//        return RxService.createApi(FouthApi.class).login(map).compose(RxUtil.rxSchedulerHelper());
        return null;
    }
}
