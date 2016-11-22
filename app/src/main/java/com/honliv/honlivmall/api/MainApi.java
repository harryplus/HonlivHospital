package com.honliv.honlivmall.api;

import com.honliv.honlivmall.bean.BaseBean;
import com.honliv.honlivmall.bean.BaseInfo;
import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.ProductListFilter;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Rodin on 2016/11/16.
 */
public interface MainApi {
    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<ResponseBody> post(@Body HashMap<String,Object> map);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<HomeInfo>>> HomeIndex(@Body HashMap<String,Object> map);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<ProductListFilter>>> CountDownList(@Body HashMap<String,Object> map);
}
