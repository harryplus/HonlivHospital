package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.Help;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.HelpPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthHelpDetailModel implements FifthContract.FifthHelpDetailModel  {
    @Override
    public Observable<Help> HelpDetail(int helpId) {
        Observable<Help> result = HelpPostUtils.HelpDetail(helpId);
        return result;
    }
}
