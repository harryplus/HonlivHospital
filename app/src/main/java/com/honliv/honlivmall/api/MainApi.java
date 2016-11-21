package com.honliv.honlivmall.api;

import java.util.HashMap;

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
}
