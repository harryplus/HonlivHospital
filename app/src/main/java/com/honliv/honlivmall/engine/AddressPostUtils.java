package com.honliv.honlivmall.engine;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.api.MainApi;
import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.bean.PayShip;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.Utils;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class AddressPostUtils {
    public static Observable<List<AddressInfo>> addresslist() {
        HashMap<String, Object> postMap = Utils.getBaseMap("addresslist");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", GloableParams.USERID);
        parames.put("pageIndex", 1);
        parames.put("pageNum", 50);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).ListAddressInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<List<AddressInfo>>() {
//            }.getType();
//            List<AddressInfo> result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<List<AddressInfo>> GetRegionList(int addressId) {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetRegionList");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("regionId", addressId);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).ListAddressInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<List<AddressInfo>>() {
//            }.getType();
//            List<AddressInfo> result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<List<AddressInfo>> SaveAddress(AddressInfo info) {
        HashMap<String, Object> postMap = Utils.getBaseMap("SaveAddress");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("id", info.getId() + "");
        parames.put("UserId", info.getUserId());
        parames.put("name", info.getName());
        parames.put("areaid", info.getRegionId());
        parames.put("celphone", info.getPhone());
        parames.put("areadetail", info.getAreaDetail());
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).ListAddressInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<List<AddressInfo>>() {
//            }.getType();
//            List<AddressInfo> result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<List<AddressInfo>> getServiceAddAddress(AddressInfo info) {
        HashMap<String, Object> postMap = Utils.getBaseMap("SaveAddress");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("UserId", info.getUserId());
        parames.put("name", info.getName());
        parames.put("areaid", info.getRegionId());
        parames.put("celphone", info.getPhone());
        parames.put("areadetail", info.getAreaDetail());
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).ListAddressInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<List<AddressInfo>>() {
//            }.getType();
//            List<AddressInfo> result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<String> GetRegionName(int addressId) {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetRegionName");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("regionId", addressId);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).String(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            String result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = bean.getResult().getResult();
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<List<AddressInfo>> addresslist(int userid, int pageIndex, int pageNum) {
        HashMap<String, Object> postMap = Utils.getBaseMap("addresslist");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userid);
        parames.put("pageIndex", pageIndex);
        parames.put("pageNum", pageNum);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).ListAddressInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<List<AddressInfo>>() {
//            }.getType();
//            List<AddressInfo> result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<List<PayShip>> GetPayList() {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetPayList");
        HashMap<String, Object> parames = new HashMap<>();
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).ListPayShip(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<List<PayShip>>() {
//            }.getType();
//            List<PayShip> result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Boolean> SetAddressDefault(int userId, int addressId) {
        HashMap<String, Object> postMap = Utils.getBaseMap("SetAddressDefault");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userId);
        parames.put("shipId", addressId);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).String(postMap).map(bean -> {
            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
                return true;
            }
            return false;
        }).compose(RxUtil.rxSchedulerHelper());
    }
}
