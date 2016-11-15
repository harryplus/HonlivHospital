package com.honliv.honlivhospital.api;

import com.honliv.honlivhospital.domain.BaseResult;
import com.honliv.honlivhospital.domain.UserBean;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Rodin on 2016/11/14.
 */
public interface FouthApi {
    //    @FormUrlEncoded
//    @POST("api/user/verifycode")
//    Observable<BaseResult<VerfyVodeBean>> getVerfyVode(@Field("PhoneNumber") String PhoneNumber);
//    @Streaming
    //    @POST("api/user/verifycode")
//    @Headers("Content-Type: application/json")
//    @POST("api/user/posttest")
//    Observable<ResponseBody> getVerfyVode(@Body HashMap<String, String> body);

    @Headers("Content-Type: application/json")
    @POST("api/user/posttest")
    Observable<BaseResult<List<UserBean>>> getVerfyVode(@Body HashMap<String, String> body);

    @Headers("Content-Type: application/json")
    @POST("api/user/posttest")
    Observable<BaseResult<List<UserBean>>> login(@Body HashMap<String, String> body);

    @Headers("Content-Type: application/json")
    @POST("api/user/posttest")
    Observable<BaseResult<UserBean>> register(@Body HashMap<String, String> body);
}
