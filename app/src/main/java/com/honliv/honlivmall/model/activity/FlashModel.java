package com.honliv.honlivmall.model.activity;

import com.honliv.honlivmall.bean.DefaultParas;
import com.honliv.honlivmall.bean.GifInfo;
import com.honliv.honlivmall.bean.PayShip;
import com.honliv.honlivmall.contract.ActivityContract;
import com.honliv.honlivmall.engine.AddressPostUtils;
import com.honliv.honlivmall.engine.HomePostUtils;

import org.json.JSONObject;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/21.
 */
public class FlashModel implements ActivityContract.FlashModel {
    @Override
    public Observable<DefaultParas> getServiceDafault() {
        Observable<DefaultParas> result = HomePostUtils.GetPhone();
        return result;
    }
}
