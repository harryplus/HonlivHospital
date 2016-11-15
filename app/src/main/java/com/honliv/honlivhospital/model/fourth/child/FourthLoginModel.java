package com.honliv.honlivhospital.model.fourth.child;

import com.honliv.honlivhospital.api.FouthApi;
import com.honliv.honlivhospital.contract.FourthContract;
import com.honliv.honlivhospital.domain.BaseResult;
import com.honliv.honlivhospital.domain.UserBean;
import com.honliv.honlivhospital.utils.RxService;
import com.honliv.honlivhospital.utils.RxUtil;

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
        return RxService.createApi(FouthApi.class).login(map).compose(RxUtil.rxSchedulerHelper());
    }
}
