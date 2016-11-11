package com.honliv.honlivhospital.engine.impl;

import com.honliv.honlivhospital.ConstantValue;
import com.honliv.honlivhospital.application.MyApplication;
import com.honliv.honlivhospital.engine.MainEngine;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Rodin on 2016/11/10.
 */
public class MainEngineImpl implements MainEngine {
    @Override
    public List<String> getViewPaperDate() {
        final List<String> result = new ArrayList<>();
        MyApplication.client.post(ConstantValue.URL, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                result.add(responseString);
            }
        });
        return result;
    }
}
