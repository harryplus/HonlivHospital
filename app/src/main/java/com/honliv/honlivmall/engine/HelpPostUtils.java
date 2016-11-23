package com.honliv.honlivmall.engine;

import com.honliv.honlivmall.api.MainApi;
import com.honliv.honlivmall.bean.Help;
import com.honliv.honlivmall.bean.VersionInfo;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.Utils;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/23.
 */
public class HelpPostUtils {
    public static Observable<VersionInfo> checkVersion() {
        HashMap<String, Object> postMap = Utils.getBaseMap("checkVersion");
        HashMap<String, Object> parames = new HashMap<>();
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).VersionInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());

    }

    public static Observable<List<Help>> HelpList(int helpId) {
        HashMap<String, Object> postMap = Utils.getBaseMap("HelpList");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("classid", helpId);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).ListHelp(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());

    }

    public static Observable<Help> HelpDetail(int id) {
        HashMap<String, Object> postMap = Utils.getBaseMap("HelpDetail");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("contentid", id);
        return RxService.createApi(MainApi.class).Help(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());

    }
}
