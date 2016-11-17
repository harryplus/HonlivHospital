package com.honliv.honlivmall.model.fourth.child;

import com.google.gson.Gson;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.api.FouthApi;
import com.honliv.honlivmall.contract.FourthContract;
import com.honliv.honlivmall.bean.BaseResult;
import com.honliv.honlivmall.bean.UserBean;
import com.honliv.honlivmall.util.Base64Utils;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FourthRegisterModel implements FourthContract.FourthRegisterModel {
    @Override
    public Observable<BaseResult<UserBean>> register(UserBean bean, String VcodeData) {
        HashMap<String, String> map = new HashMap<>();
        map.put("UserData", new Gson().toJson(bean));
        map.put("VcodeData", VcodeData);
        return RxService.createApi(FouthApi.class).register(map).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<BaseResult<List<UserBean>>> sendNote(String phoneNum) {
        HashMap<String, String> map = new HashMap<>();
        try {
            map.put("phoneNumber", Base64Utils.EncryptAsDoNet(phoneNum, ConstantValue.SecretKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RxService.createApi(FouthApi.class).getVerfyVode(map).compose(RxUtil.rxSchedulerHelper());
    }
}
