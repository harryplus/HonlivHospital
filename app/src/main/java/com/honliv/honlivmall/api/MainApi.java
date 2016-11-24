package com.honliv.honlivmall.api;

import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.bean.BaseBean;
import com.honliv.honlivmall.bean.BaseInfo;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.bean.DefaultParas;
import com.honliv.honlivmall.bean.GifInfo;
import com.honliv.honlivmall.bean.Help;
import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.HxtBean;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.bean.PayShip;
import com.honliv.honlivmall.bean.PaymentInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.ProductListFilter;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.bean.VersionInfo;

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
    Observable<ResponseBody> postBody(@Body HashMap<String, Object> map);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<String>>> post(@Body HashMap<String, Object> map);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<HomeInfo>>> HomeIndex(@Body HashMap<String, Object> map);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<Integer>>> Integer(@Body HashMap<String, Object> map);

//    @Headers("Content-Type: application/json")
//    @POST("shop.aspx")
//    Observable<BaseBean<BaseInfo<ProductListFilter>>> ProductListFilter(@Body HashMap<String, Object> map);

//    @Headers("Content-Type: application/json")
//    @POST("shop.aspx")
//    Observable<BaseBean<BaseInfo<ProductListFilter>>> ProductListFilter(@Body HashMap<String, Object> map);

//    @Headers("Content-Type: application/json")
//    @POST("shop.aspx")
//    Observable<BaseBean<BaseInfo<UserInfo>>> UserInfo(@Body HashMap<String, Object> postMap);

//    @Headers("Content-Type: application/json")
//    @POST("shop.aspx")
//    Observable<BaseBean<BaseInfo<UserInfo>>> UserInfo(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<UserInfo>>> UserInfo(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<ProductListFilter>>> ProductListFilter(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<List<AddressInfo>>>> ListAddressInfo(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<List<OrderInfo>>>> ListOrderInfo(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<List<String>>>> ListString(@Body HashMap<String, Object> postMap);

//    @Headers("Content-Type: application/json")
//    @POST("shop.aspx")
//    Observable<BaseBean<BaseInfo<List<AddressInfo>>>> ListAddressInfo(@Body HashMap<String, Object> postMap);

//    @Headers("Content-Type: application/json")
//    @POST("shop.aspx")
//    Observable<BaseBean<BaseInfo<List<AddressInfo>>>> ListAddressInfo(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<List<Category>>>> ListCategory(@Body HashMap<String, Object> postMap);

//    @Headers("Content-Type: application/json")
//    @POST("shop.aspx")
//    Observable<BaseBean<BaseInfo<String>>> String(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<DefaultParas>>> DefaultParas(@Body HashMap<String, Object> postMap);

//    @Headers("Content-Type: application/json")
//    @POST("shop.aspx")
//    Observable<BaseBean<BaseInfo<PaymentInfo>>> PaymentInfo(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<PaymentInfo>>> PaymentInfo(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<GifInfo>>> GifInfo(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<Product>>> Product(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<String>>> String(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<VersionInfo>>> VersionInfo(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<List<PayShip>>>> ListPayShip(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<List<HxtBean>>>> ListHxtBean(@Body HashMap<String, Object> postMap);

    //    @Headers("Content-Type: application/json")
//    @POST("shop.aspx")
//    Observable<ResponseBody> ListCouponInfo(@Body HashMap<String, Object> postMap);
    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<List<CouponInfo>>>> ListCouponInfo(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<List<Product>>>> ListProduct(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<List<Help>>>> ListHelp(@Body HashMap<String, Object> postMap);

    @Headers("Content-Type: application/json")
    @POST("shop.aspx")
    Observable<BaseBean<BaseInfo<Help>>> Help(@Body HashMap<String, Object> postMap);


}
