package com.honliv.honlivhospital.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.honliv.honlivhospital.ConstantValue;
import com.honliv.honlivhospital.api.FouthApi;
import com.honliv.honlivhospital.utils.RxManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpRequest;
import com.loopj.android.http.ResponseHandlerInterface;

import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.HttpContext;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rodin on 2016/10/28.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    public static AsyncHttpClient client = new AsyncHttpClient() {//全局网络请求器

        @Override
        protected AsyncHttpRequest newAsyncHttpRequest(DefaultHttpClient client, HttpContext httpContext, HttpUriRequest uriRequest, String contentType, ResponseHandlerInterface responseHandler, Context context) {
            AsyncHttpRequest httpRequest = getHttpRequest(client, httpContext, uriRequest, contentType, responseHandler, context);
            return httpRequest == null
                    ? super.newAsyncHttpRequest(client, httpContext, uriRequest, contentType, responseHandler, context)
                    : httpRequest;
        }
    };
    public static RxManager mRxManager = new RxManager();
    public static Retrofit retrofit = null;
    public static FouthApi fouthApi = null;

    public static AsyncHttpRequest getHttpRequest(DefaultHttpClient client, HttpContext httpContext, HttpUriRequest uriRequest, String contentType, ResponseHandlerInterface responseHandler, Context context) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ConstantValue.HOST_URL).build();
        fouthApi = retrofit.create(FouthApi.class);
    }
}
