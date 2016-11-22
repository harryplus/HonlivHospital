package com.honliv.honlivmall.engine;

import com.honliv.honlivmall.api.MainApi;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Rodin on 2016/11/21.
 */
public class CategoryPostUtils {
    private static HashMap<String, Object> getBaseMap(String method) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID().hashCode());
        map.put("method", method);
        map.put("jsonrpc", "2.0");
        return map;
    }
    public static Observable<List<Category>> CategoryList(HashMap<String, Object> map) {
        HashMap<String, Object> postMap = getBaseMap("CategoryList");
        postMap.put("params", map);
        return RxService.createApi(MainApi.class).post(postMap).map(new Func1<ResponseBody, List<Category>>() {
            @Override
            public List<Category> call(ResponseBody responseBody) {

                return null;
            }
        }).compose(RxUtil.rxSchedulerHelper());
    }
}
