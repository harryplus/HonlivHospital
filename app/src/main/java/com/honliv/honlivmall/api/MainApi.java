package com.honliv.honlivmall.api;

import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.bean.BaseBean;
import com.honliv.honlivmall.bean.BaseInfo;
import com.honliv.honlivmall.bean.DefaultParas;
import com.honliv.honlivmall.bean.GifInfo;
import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.PayShip;
import com.honliv.honlivmall.bean.PaymentInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.ProductListFilter;
import com.honliv.honlivmall.bean.UserInfo;

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
    Observable<ResponseBody> post(@Body HashMap<String, Object> map);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<ResponseBody> updateUserInfo(@Body HashMap<String, Object> map);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<HomeInfo>>> HomeIndex(@Body HashMap<String, Object> map);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<ProductListFilter>>> CountDownList(@Body HashMap<String, Object> map);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<UserInfo>>> Login(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<UserInfo>>> GetUserInfo(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<ProductListFilter>>> SearchProductList(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<List<AddressInfo>>>> addresslist(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<List<AddressInfo>>>> GetRegionList(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<String>>> GetRegionName(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<DefaultParas>>> GetPhone(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<PaymentInfo>>> GetFreight(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<PaymentInfo>>> GetTotalPrice(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<GifInfo>>> GetGiftInfo(@Body HashMap<String, Object> postMap);
    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<List<PayShip>>>> GetPayList(@Body HashMap<String, Object> postMap);


}
